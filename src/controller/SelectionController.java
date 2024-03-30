package controller;

import model.*;
import view.SelectionView;
import view.UserView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class SelectionController {

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


    public SelectionController(UserView view2) {
        this.view2 = view2;
        this.view2.addCreateListener(new CreateListener());

    }

    class CreateListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                process = view2.gettextField();
                int physicalSize = view2.gettextField1();
                int offset = view2.gettextField2();
                int virtualSize = view2.gettextField3();
                int tlb1 = view2.gettextField4();

                PageTable.initialize(virtualSize, offset, process, pageTable1, process);
                PhysicalAddress.initialize(physicalSize, offset, physicalAddress1);
                Tlb.initialize(tlb1, buffer);

                if (process <= 0 || physicalSize <= 0 || offset <= 0 || virtualSize <= 0 || tlb1 <= 0) {
                    view2.showMessage("Something is wrong with the input!");
                } else if (virtualSize % 2 != 0) {
                    view2.showMessage("please give an even number for the virtual memory size");
                } else {
                    SelectionView view = new SelectionView();

                    String s = "";
                    String s2 = "";
                    String s3 = "";
                    String s4 = "";
                    String s5 = "";

                    for (int i = 1; i <= process; i++) {
                        List<VirtualAddress> virtualAddress1 = new ArrayList<>();
                        int j = (int) (Math.random() * (10 - 1 + 1)) + 4;
                        for (int u = 0; u < j; u++) {
                            VirtualAddress.generate(virtualSize, offset, virtualAddress1, lista);
                            lista.add(virtualAddress1.get(u).getNumberPage());
                            //for generating unique virtual addresses
                            PageTable.addTo(i, virtualAddress1.get(u).getNumberPage(), dex, pageTable1, physicalSize, offset);
                            int x = PageTable.findFrame(pageTable1, virtualAddress1.get(u).getNumberPage()); //find the frame number in the page table
                            Tlb.addTo(i, dex, virtualAddress1.get(u).getNumberPage(), buffer, tlb1, x);
                            PhysicalAddress.addAddress(physicalAddress1, pageTable1, physicalSize, offset, virtualAddress1.get(u).getOffset(), dex);
                            time++;
                            dex++; // everything misses at first since the virtual address is unique
                            //global counter used in the page table and tlb add methods, passed to Controller2
                            s3 = " ";
                            miss++;

                        }
                        processList.addProcess(i, virtualAddress1);

                        s = s + "Process " + i + "\n";
                        s = s + Processes.toString(virtualAddress1);

                    }
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
                    UserController controller2 = new UserController(view, processList, dex, process, pageTable1, physicalMemory1, time, buffer, tlb1, physicalSize, offset, hit, miss);
                }
            } catch (Exception ex) {
                view2.showMessage("Something went wrong!");
                ex.printStackTrace();
            }
        }
    }


}

