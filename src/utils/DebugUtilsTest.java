package utils;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class DebugUtilsTest {

    @BeforeEach
    public void setUp() {
        DebugUtils.setDebugMode(false);
    }



    @Test
    @DisplayName("Valid configuration should pass validation")
    public void testValidConfiguration() {
        assertDoesNotThrow(() -> {
            DebugUtils.validateSystemConfiguration(4, 1024, 4, 2048, 8);
        });
    }

    @Test
    @DisplayName("Negative number of processes should fail")
    public void testNegativeProcesses() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> DebugUtils.validateSystemConfiguration(-1, 1024, 4, 2048, 8)
        );
        assertTrue(exception.getMessage().contains("processes must be positive"));
    }

    @Test
    @DisplayName("Zero processes should fail")
    public void testZeroProcesses() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> DebugUtils.validateSystemConfiguration(0, 1024, 4, 2048, 8)
        );
        assertTrue(exception.getMessage().contains("processes must be positive"));
    }

    @Test
    @DisplayName("Non-power-of-2 physical memory should fail")
    public void testNonPowerOfTwoPhysicalMemory() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> DebugUtils.validateSystemConfiguration(4, 1000, 4, 2048, 8)
        );
        assertTrue(exception.getMessage().contains("power of 2"));
    }

    @Test
    @DisplayName("Physical memory larger than virtual memory should fail")
    public void testPhysicalLargerThanVirtual() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> DebugUtils.validateSystemConfiguration(4, 2048, 4, 1024, 8)
        );
        assertTrue(exception.getMessage().contains("Virtual memory size must be >= physical memory"));
    }

    @Test
    @DisplayName("Invalid offset bits should fail")
    public void testInvalidOffsetBits() {
        assertThrows(
                IllegalArgumentException.class,
                () -> DebugUtils.validateSystemConfiguration(4, 1024, 0, 2048, 8)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> DebugUtils.validateSystemConfiguration(4, 1024, -1, 2048, 8)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> DebugUtils.validateSystemConfiguration(4, 1024, 32, 2048, 8)
        );
    }

    @Test
    @DisplayName("Negative TLB size should fail")
    public void testNegativeTlbSize() {
        assertThrows(
                IllegalArgumentException.class,
                () -> DebugUtils.validateSystemConfiguration(4, 1024, 4, 2048, -1)
        );
    }

    @Test
    @DisplayName("Multiple invalid parameters should list all errors")
    public void testMultipleErrors() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> DebugUtils.validateSystemConfiguration(-1, 1000, 0, 512, -5)
        );

        String message = exception.getMessage();
        // Should contain multiple error messages
        assertTrue(message.contains("processes"));
        assertTrue(message.contains("power of 2"));
        assertTrue(message.contains("offset"));
    }

    @Test
    @DisplayName("Boundary values should be accepted")
    public void testBoundaryValues() {

        assertDoesNotThrow(() -> {
            DebugUtils.validateSystemConfiguration(1, 256, 2, 256, 1);
        });


        assertDoesNotThrow(() -> {
            DebugUtils.validateSystemConfiguration(10, 65536, 12, 131072, 64);
        });
    }



    @Test
    @DisplayName("isPowerOfTwo should correctly identify powers of 2")
    public void testIsPowerOfTwo() {

        assertTrue(DebugUtils.isPowerOfTwo(1));
        assertTrue(DebugUtils.isPowerOfTwo(2));
        assertTrue(DebugUtils.isPowerOfTwo(4));
        assertTrue(DebugUtils.isPowerOfTwo(8));
        assertTrue(DebugUtils.isPowerOfTwo(1024));
        assertTrue(DebugUtils.isPowerOfTwo(65536));


        assertFalse(DebugUtils.isPowerOfTwo(0));
        assertFalse(DebugUtils.isPowerOfTwo(-1));
        assertFalse(DebugUtils.isPowerOfTwo(3));
        assertFalse(DebugUtils.isPowerOfTwo(100));
        assertFalse(DebugUtils.isPowerOfTwo(1000));
    }

    @Test
    @DisplayName("bitsNeeded should calculate correct number of bits")
    public void testBitsNeeded() {
        assertEquals(0, DebugUtils.bitsNeeded(0));
        assertEquals(0, DebugUtils.bitsNeeded(-1));
        assertEquals(1, DebugUtils.bitsNeeded(1));
        assertEquals(2, DebugUtils.bitsNeeded(2));
        assertEquals(2, DebugUtils.bitsNeeded(3));
        assertEquals(3, DebugUtils.bitsNeeded(4));
        assertEquals(4, DebugUtils.bitsNeeded(15));
        assertEquals(4, DebugUtils.bitsNeeded(16));
        assertEquals(10, DebugUtils.bitsNeeded(1024));
    }

    @Test
    @DisplayName("formatBinaryAddress should format correctly with padding")
    public void testFormatBinaryAddress() {
        assertEquals("0000", DebugUtils.formatBinaryAddress(0, 4));
        assertEquals("0001", DebugUtils.formatBinaryAddress(1, 4));
        assertEquals("0101", DebugUtils.formatBinaryAddress(5, 4));
        assertEquals("1111", DebugUtils.formatBinaryAddress(15, 4));
        assertEquals("00001010", DebugUtils.formatBinaryAddress(10, 8));
    }

    @Test
    @DisplayName("explainVirtualAddress should provide readable explanation")
    public void testExplainVirtualAddress() {
        String explanation = DebugUtils.explainVirtualAddress(5, 12, 4);
        assertTrue(explanation.contains("Page: 5"));
        assertTrue(explanation.contains("Offset: 12"));
        assertTrue(explanation.contains("("));
    }



    @Test
    @DisplayName("assertCondition should pass for true condition")
    public void testAssertConditionPass() {
        assertDoesNotThrow(() -> {
            DebugUtils.assertCondition(true, "This should not throw");
        });
    }

    @Test
    @DisplayName("assertCondition should fail for false condition")
    public void testAssertConditionFail() {
        AssertionError error = assertThrows(
                AssertionError.class,
                () -> DebugUtils.assertCondition(false, "Expected failure")
        );
        assertTrue(error.getMessage().contains("Expected failure"));
    }

    @Test
    @DisplayName("requireNonNull should pass for non-null object")
    public void testRequireNonNullPass() {
        String result = DebugUtils.requireNonNull("test", "testParam");
        assertEquals("test", result);
    }

    @Test
    @DisplayName("requireNonNull should fail for null object")
    public void testRequireNonNullFail() {
        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> DebugUtils.requireNonNull(null, "testParam")
        );
        assertTrue(exception.getMessage().contains("testParam"));
    }



    @Test
    @DisplayName("Very large process count should trigger warning")
    public void testExcessiveProcessCount() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> DebugUtils.validateSystemConfiguration(1000, 1024, 4, 2048, 8)
        );
        assertTrue(exception.getMessage().contains("unreasonably high"));
    }

    @Test
    @DisplayName("Very large TLB should trigger warning")
    public void testExcessiveTlbSize() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> DebugUtils.validateSystemConfiguration(4, 1024, 4, 2048, 2000)
        );
        assertTrue(exception.getMessage().contains("unreasonably high"));
    }

    @Test
    @DisplayName("Very small page size should trigger warning")
    public void testVerySmallPageSize() {

        assertDoesNotThrow(() -> {
            DebugUtils.validateSystemConfiguration(4, 1024, 4, 2048, 8);
        });


        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> DebugUtils.validateSystemConfiguration(4, 1024, 2, 2048, 8)
        );
        assertTrue(exception.getMessage().contains("Page size is very small"));
    }

    @Test
    @DisplayName("Page size too large relative to physical memory should fail")
    public void testPageSizeTooLarge() {

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> DebugUtils.validateSystemConfiguration(2, 512, 8, 1024, 4)
        );
        assertTrue(exception.getMessage().contains("too large relative to physical memory"));
    }



    @Test
    @DisplayName("Realistic configuration scenarios should work")
    public void testRealisticScenarios() {

        assertDoesNotThrow(() -> {
            DebugUtils.validateSystemConfiguration(2, 1024, 4, 2048, 4);
        });


        assertDoesNotThrow(() -> {
            DebugUtils.validateSystemConfiguration(8, 16384, 8, 65536, 16);
        });


        assertDoesNotThrow(() -> {
            DebugUtils.validateSystemConfiguration(16, 65536, 12, 262144, 32);
        });
    }
}