package controller;

import model.*;
import view.SelectionView;
import view.UserView;
import utils.DebugUtils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.logging.Level;


public class SelectionControllerWithDebug {

    private UserView view2;
    private SelectionView view;
    private int hit = 0;
    private int miss = 0;

    private Processes processList = new Processes();
    private PhysicalMemory physicalMemory1;
    private List<PageTable> pageTable1 = new ArrayList<>();
    private List<PhysicalAddress> physicalAddress1 = new ArrayList<>();

    private List<Tlb> buffer = new ArrayList<>();
    private int dex = 0;
    private int process;

    private int time = 0;
    private List<Integer> lista = new ArrayList<>();


    public SelectionControllerWithDebug(UserView view2) {
        this.view2 = view2;
        this.view2.addCreateListener(new CreateListener());
        DebugUtils.log(Level.INFO, "SelectionController initialized");
    }

    class CreateListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {

                process = view2.gettextField();
                int physicalSize = view2.gettextField1();
                int offset = view2.gettextField2();
                int virtualSize = view2.gettextField3();
                int tlb1 = view2.gettextField4();

                DebugUtils.log(Level.INFO, "User submitted configuration");


                try {
                    DebugUtils.validateSystemConfiguration(process, physicalSize, offset, virtualSize, tlb1);
                } catch (IllegalArgumentException ex) {
                    view2.showMessage("Invalid configuration:\n" + ex.getMessage());
                    DebugUtils.log(Level.WARNING, "User provided invalid configuration: " + ex.getMessage());
                    return;
                }


                int numPhysicalPages = (int) (physicalSize / Math.pow(2, offset));
                int numVirtualPages = (int) (virtualSize / Math.pow(2, offset));
                DebugUtils.checkPerformanceWarnings(tlb1, numPhysicalPages, numVirtualPages);


                DebugUtils.log(Level.INFO, "Initializing data structures...");

                PageTable.initialize(virtualSize, offset, process, pageTable1, process);
                DebugUtils.assertCondition(!pageTable1.isEmpty(), "Page table initialization failed");

                PhysicalAddress.initialize(physicalSize, offset, physicalAddress1);
                DebugUtils.assertCondition(!physicalAddress1.isEmpty(), "Physical memory initialization failed");

                Tlb.initialize(tlb1, buffer);
                DebugUtils.assertCondition(!buffer.isEmpty(), "TLB initialization failed");

                DebugUtils.log(Level.INFO, "All data structures initialized successfully");


                SelectionView view = new SelectionView();

                String s = "";
                String s2 = "";
                String s3 = "";
                String s4 = "";
                String s5 = "";


                for (int i = 1; i <= process; i++) {
                    DebugUtils.log(Level.INFO, String.format("Generating virtual addresses for process %d", i));

                    List<VirtualAddress> virtualAddress1 = new ArrayList<>();
                    int j = (int) (Math.random() * (10 - 1 + 1)) + 4;

                    for (int u = 0; u < j; u++) {
                        VirtualAddress.generate(virtualSize, offset, virtualAddress1, lista);
                        int generatedPage = virtualAddress1.get(u).getNumberPage();
                        lista.add(generatedPage);


                        DebugUtils.log(Level.FINE, String.format(
                                "Generated virtual address for Process %d: Page %d, Offset %d",
                                i, generatedPage, virtualAddress1.get(u).getOffset()));


                        try {
                            DebugUtils.validatePageTableOperation(i, generatedPage, dex,
                                    pageTable1, process, numVirtualPages);
                            PageTable.addTo(i, generatedPage, dex, pageTable1, physicalSize, offset);
                        } catch (Exception ex) {
                            DebugUtils.log(Level.SEVERE, "Failed to add page to page table: " + ex.getMessage());
                            throw ex;
                        }

                        int x = PageTable.findFrame(pageTable1, generatedPage);


                        try {
                            DebugUtils.validateTlbOperation(i, generatedPage, dex, buffer, process);
                            Tlb.addTo(i, dex, generatedPage, buffer, tlb1, x);
                        } catch (Exception ex) {
                            DebugUtils.log(Level.SEVERE, "Failed to add to TLB: " + ex.getMessage());
                            throw ex;
                        }

                        PhysicalAddress.addAddress(physicalAddress1, pageTable1, physicalSize,
                                offset, virtualAddress1.get(u).getOffset(), dex);

                        time++;
                        dex++;
                        miss++;

                        DebugUtils.logMemoryAccess(i, generatedPage,
                                virtualAddress1.get(u).getOffset(),
                                false, "Initial Load");
                    }

                    processList.addProcess(i, virtualAddress1);

                    s = s + "Process " + i + "\n";
                    s = s + Processes.toString(virtualAddress1);
                }


                DebugUtils.verifyPageTableConsistency(pageTable1, physicalSize, offset);
                DebugUtils.verifyTlbConsistency(buffer, pageTable1);


                for (int t = 1; t <= process; t++) {
                    s2 = s2 + "Page table for process " + t + "\n";
                    s2 = s2 + PageTable.toString(pageTable1, t);
                }

                PhysicalMemory physicalMemory1 = new PhysicalMemory(physicalAddress1);
                s3 = s3 + physicalMemory1.toString(physicalAddress1);
                s4 = s4 + Tlb.toString(buffer);
                s5 = String.valueOf(time);


                view.setTextArea(s);
                view.setTextArea3(s2);
                view.setTextArea4(s3);
                view.setTextArea2(s4);
                view.setTextArea_3_2(s5);
                view.setTextArea_3(hit);
                view.setTextArea_3_1(miss);


                DebugUtils.logStatistics(hit, miss, hit + miss, time);

                DebugUtils.log(Level.INFO, "System initialization completed successfully");


                UserController controller2 = new UserController(view, processList, dex, process,
                        pageTable1, physicalMemory1, time,
                        buffer, tlb1, physicalSize, offset,
                        hit, miss);

            } catch (IllegalArgumentException ex) {

                view2.showMessage("Configuration Error:\n" + ex.getMessage());
                DebugUtils.log(Level.SEVERE, "Configuration error: " + ex.getMessage());
            } catch (Exception ex) {

                view2.showMessage("An unexpected error occurred. Check debug log for details.");
                DebugUtils.log(Level.SEVERE, "Unexpected error in initialization: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}