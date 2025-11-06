package utils;

import model.*;
import java.util.List;
import java.util.logging.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DebugUtils {

    private static final Logger LOGGER = Logger.getLogger(DebugUtils.class.getName());
    private static FileHandler fileHandler;
    private static boolean isDebugMode = true;
    static {
        try {
            fileHandler = new FileHandler("virtual_memory_debug.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);
            LOGGER.setLevel(Level.ALL);
        } catch (IOException e) {
            System.err.println("Failed to initialize log file: " + e.getMessage());
        }
    }

    public static void validateSystemConfiguration(int numProcesses, int physicalSize, int offsetBits, int virtualSize, int tlbSize) {
        log(Level.INFO, "Validating system configuration...");
        StringBuilder errors = new StringBuilder();
        if (numProcesses <= 0) {
            errors.append("- Number of processes must be positive (got: ").append(numProcesses).append(")\n");
        }
        if (numProcesses > 100) {
            errors.append("- Number of processes seems unreasonably high (got: ").append(numProcesses).append(")\n");
        }
        if (physicalSize <= 0) {
            errors.append("- Physical memory size must be positive (got: ").append(physicalSize).append(")\n");
        }
        if (!isPowerOfTwo(physicalSize)) {
            errors.append("- Physical memory size must be a power of 2 (got: ").append(physicalSize).append(")\n");
        }
        if (offsetBits <= 0) {
            errors.append("- Offset bits must be positive (got: ").append(offsetBits).append(")\n");
        }
        if (offsetBits >= 32) {
            errors.append("- Offset bits must be less than 32 (got: ").append(offsetBits).append(")\n");
        }
        if (virtualSize <= 0) {
            errors.append("- Virtual memory size must be positive (got: ").append(virtualSize).append(")\n");
        }
        if (!isPowerOfTwo(virtualSize)) {
            errors.append("- Virtual memory size must be a power of 2 (got: ").append(virtualSize).append(")\n");
        }
        if (virtualSize < physicalSize) {
            errors.append("- Virtual memory size must be >= physical memory size (virtual: ")
                    .append(virtualSize).append(", physical: ").append(physicalSize).append(")\n");
        }
        if (tlbSize <= 0) {
            errors.append("- TLB size must be positive (got: ").append(tlbSize).append(")\n");
        }
        if (tlbSize > 1024) {
            errors.append("- TLB size seems unreasonably high (got: ").append(tlbSize).append(")\n");
        }
        int pageSize = (int) Math.pow(2, offsetBits);
        if (pageSize < 64) {
            errors.append("- Page size is very small (").append(pageSize).append(" bytes). Consider larger offset.\n");
        }
        if (pageSize > physicalSize / 4) {
            errors.append("- Page size (").append(pageSize).append(") is too large relative to physical memory (")
                    .append(physicalSize).append(")\n");
        }
        if (errors.length() > 0) {
            log(Level.SEVERE, "Configuration validation failed:\n" + errors.toString());
            throw new IllegalArgumentException("Invalid system configuration:\n" + errors.toString());
        }
        log(Level.INFO, "System configuration validated successfully");
        logConfigurationSummary(numProcesses, physicalSize, offsetBits, virtualSize, tlbSize);
    }


    public static void validatePageTableOperation(int processId, int pageNumber, int frame,
              List<PageTable> pageTableList, int maxProcesses, int maxPages) {
        if (processId <= 0 || processId > maxProcesses) {
            throw new IllegalArgumentException(
                    String.format("Invalid process ID: %d (must be between 1 and %d)",
                            processId, maxProcesses));
        }
        if (pageNumber < 0 || pageNumber >= maxPages) {
            throw new IllegalArgumentException(
                    String.format("Invalid page number: %d (must be between 0 and %d)",
                            pageNumber, maxPages - 1));
        }
        if (frame < -1) {
            throw new IllegalArgumentException(
                    String.format("Invalid frame number: %d (must be >= -1)", frame));
        }
        if (pageTableList == null || pageTableList.isEmpty()) {
            throw new IllegalStateException("Page table list is null or empty");
        }
        log(Level.FINE, String.format("Page table operation validated: Process=%d, Page=%d, Frame=%d",
                processId, pageNumber, frame));
    }


    public static void validateTlbOperation(int processId, int pageNumber, int frame, List<Tlb> buffer, int maxProcesses) {
        if (processId <= 0 || processId > maxProcesses) {
            throw new IllegalArgumentException(
                    String.format("Invalid process ID for TLB: %d", processId));
        }
        if (pageNumber < 0) {
            throw new IllegalArgumentException(
                    String.format("Invalid page number for TLB: %d", pageNumber));
        }
        if (buffer == null || buffer.isEmpty()) {
            throw new IllegalStateException("TLB buffer is null or empty");
        }
        log(Level.FINE, String.format("TLB operation validated: Process=%d, Page=%d",
                processId, pageNumber));
    }

    public static void verifyPageTableConsistency(List<PageTable> pageTableList, int physicalSize, int offsetBits) {
        log(Level.INFO, "Verifying page table consistency...");
        int maxFrames = (int) (physicalSize / Math.pow(2, offsetBits));
        int[] frameUsageCount = new int[maxFrames];
        for (PageTable entry : pageTableList) {
            if (entry.getValid() == 1) {
                int frame = entry.getFrame();
                if (frame < 0 || frame >= maxFrames) {
                    log(Level.WARNING, String.format(
                            "Invalid frame number in page table: Process=%d, Page=%d, Frame=%d (max=%d)",
                            entry.getIndex(), entry.getPage(), frame, maxFrames - 1));
                }
                if (frame >= 0 && frame < maxFrames) {
                    frameUsageCount[frame]++;
                }
            }
        }
        for (int i = 0; i < frameUsageCount.length; i++) {
            if (frameUsageCount[i] > 1) {
                log(Level.WARNING, String.format(
                        "Frame %d is mapped by %d different valid pages - potential consistency issue",
                        i, frameUsageCount[i]));
            }
        }
        log(Level.INFO, "Page table consistency check completed");
    }


    public static void verifyTlbConsistency(List<Tlb> buffer, List<PageTable> pageTableList) {
        log(Level.INFO, "Verifying TLB consistency...");
        for (Tlb tlbEntry : buffer) {
            int process = tlbEntry.getProcess();
            int page = tlbEntry.getPage();
            int tlbFrame = tlbEntry.getFrame2();
            if (page == -1 || process == -1) {
                continue;
            }
            boolean foundMatch = false;
            for (PageTable ptEntry : pageTableList) {
                if (ptEntry.getIndex() == process &&
                        ptEntry.getPage() == page &&
                        ptEntry.getValid() == 1) {
                    foundMatch = true;
                    if (ptEntry.getFrame() != tlbFrame) {
                        log(Level.WARNING, String.format("TLB/Page Table mismatch: Process=%d, Page=%d, TLB Frame=%d, PT Frame=%d",
                                process, page, tlbFrame, ptEntry.getFrame()));
                    }
                    break;
                }
            }
            if (!foundMatch) {
                log(Level.WARNING, String.format(
                        "TLB entry has no valid page table mapping: Process=%d, Page=%d",
                        process, page));
            }
        }
        log(Level.INFO, "TLB consistency check completed");
    }


    public static void log(Level level, String message) {
        if (!isDebugMode && level.intValue() < Level.WARNING.intValue()) {
            return;
        }
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String formattedMessage = String.format("[%s] %s", timestamp, message);
        LOGGER.log(level, formattedMessage);
    }


    private static void logConfigurationSummary(int numProcesses, int physicalSize,
                                                int offsetBits, int virtualSize, int tlbSize) {
        int pageSize = (int) Math.pow(2, offsetBits);
        int numPhysicalPages = physicalSize / pageSize;
        int numVirtualPages = virtualSize / pageSize;
        StringBuilder summary = new StringBuilder();
        summary.append("\n========== SYSTEM CONFIGURATION ==========\n");
        summary.append(String.format("Number of Processes:     %d\n", numProcesses));
        summary.append(String.format("Physical Memory Size:    %d bytes\n", physicalSize));
        summary.append(String.format("Virtual Memory Size:     %d bytes\n", virtualSize));
        summary.append(String.format("Offset Bits:             %d bits\n", offsetBits));
        summary.append(String.format("Page Size:               %d bytes\n", pageSize));
        summary.append(String.format("Physical Pages:          %d pages\n", numPhysicalPages));
        summary.append(String.format("Virtual Pages/Process:   %d pages\n", numVirtualPages));
        summary.append(String.format("TLB Size:                %d entries\n", tlbSize));
        summary.append("==========================================\n");
        log(Level.INFO, summary.toString());
    }


    public static void logMemoryAccess(int processId, int pageNumber, int offset,
                                       boolean hit, String location) {
        log(Level.FINE, String.format(
                "Memory Access: Process=%d, Page=%d, Offset=%d, %s in %s",
                processId, pageNumber, offset, hit ? "HIT" : "MISS", location));
    }

    public static void logPageReplacement(int processId, int oldPage, int newPage,
                                          int frame, String reason) {
        log(Level.INFO, String.format(
                "Page Replacement: Process=%d, Frame=%d, Old Page=%d, New Page=%d, Reason=%s",
                processId, frame, oldPage, newPage, reason));
    }


    public static void logStatistics(int hits, int misses, int totalAccesses, int time) {
        double hitRate = totalAccesses > 0 ? (double) hits / totalAccesses * 100 : 0;
        StringBuilder stats = new StringBuilder();
        stats.append("\n========== STATISTICS SUMMARY ==========\n");
        stats.append(String.format("Total Memory Accesses:   %d\n", totalAccesses));
        stats.append(String.format("TLB/Page Table Hits:     %d\n", hits));
        stats.append(String.format("TLB/Page Table Misses:   %d\n", misses));
        stats.append(String.format("Hit Rate:                %.2f%%\n", hitRate));
        stats.append(String.format("Total Time Units:        %d\n", time));
        stats.append("========================================\n");
        log(Level.INFO, stats.toString());
    }


    public static boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }


    public static int bitsNeeded(int value) {
        if (value <= 0) return 0;
        return (int) Math.ceil(Math.log(value) / Math.log(2));
    }


    public static String formatBinaryAddress(int address, int totalBits) {
        String binary = Integer.toBinaryString(address);
        while (binary.length() < totalBits) {
            binary = "0" + binary;
        }
        return binary;
    }


    public static String explainVirtualAddress(int pageNumber, int offset, int offsetBits) {
        int totalBits = offsetBits + bitsNeeded(pageNumber);
        String pageBinary = formatBinaryAddress(pageNumber, totalBits - offsetBits);
        String offsetBinary = formatBinaryAddress(offset, offsetBits);
        return String.format("Page: %d (%s), Offset: %d (%s)", pageNumber, pageBinary, offset, offsetBinary);
    }


    public static void setDebugMode(boolean enabled) {
        isDebugMode = enabled;
        log(Level.INFO, "Debug mode " + (enabled ? "enabled" : "disabled"));
    }


    public static void checkPerformanceWarnings(int tlbSize, int numPhysicalPages,
                                                int numVirtualPages) {
        if (tlbSize < numPhysicalPages / 4) {
            log(Level.WARNING, String.format(
                    "TLB size (%d) may be too small for physical memory (%d pages). Consider increasing.",
                    tlbSize, numPhysicalPages));
        }
        if (numVirtualPages > numPhysicalPages * 10) {
            log(Level.WARNING, String.format(
                    "Virtual memory (%d pages) is much larger than physical memory (%d pages). " +
                            "Expect high page fault rate.",
                    numVirtualPages, numPhysicalPages));
        }
    }


    public static void cleanup() {
        if (fileHandler != null) {
            fileHandler.close();
        }
    }


    public static void assertCondition(boolean condition, String errorMessage) {
        if (!condition) {
            log(Level.SEVERE, "Assertion failed: " + errorMessage);
            throw new AssertionError(errorMessage);
        }
    }


    public static <T> T requireNonNull(T obj, String paramName) {
        if (obj == null) {
            String message = String.format("Parameter '%s' cannot be null", paramName);
            log(Level.SEVERE, message);
            throw new NullPointerException(message);
        }
        return obj;
    }
}