import javax.swing.text.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Controller {

    private view2 view2;
    private view view;
    private int hit=0;
    private int miss=0;

    private processes processList = new processes();
    private physicalMemory physicalMemory1;
    private List<pageTable> pageTable1 = new ArrayList<>();
    private List<physicalAddress> physicalAddress1 = new ArrayList<>();

    private  List<tlb> buffer=new ArrayList<>();
    private int dex = 0;
    private int process;

    private int time=0;
    private List<Integer> lista=new ArrayList<>();


    public Controller(view2 view2) {
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

                pageTable.initialize(virtualSize,offset,process, pageTable1,process);
                physicalAddress.initialize(physicalSize,offset,physicalAddress1);
                tlb.initialize(tlb1,buffer);

                if (process <= 0 || physicalSize <= 0 || offset <= 0 || virtualSize <= 0 || tlb1 <= 0) {
                    view2.showMessage("Something is wrong with the input!");
                } else if (virtualSize % 2 != 0) {
                    view2.showMessage("please give an even number for the virtual memory size");
                } else {
                    view view = new view();

                    String s = "";
                    String s2 = "";
                    String s3 = "";
                    String s4 = "";
                    String s5="";

                    for (int i = 1; i <= process; i++) {
                        List<virtualAddress> virtualAddress1 = new ArrayList<>();
                        int j = (int) (Math.random() * (10- 1 + 1)) + 4;
                        for (int u = 0; u < j; u++) {
                            virtualAddress.generate(virtualSize,offset, virtualAddress1,lista);
                            lista.add(virtualAddress1.get(u).getNumberPage());
                            //for generating unique virtual addresses
                            pageTable.addTo(i, virtualAddress1.get(u).getNumberPage(), dex, pageTable1,physicalSize,offset);
                            int x=pageTable.findFrame(pageTable1,virtualAddress1.get(u).getNumberPage()); //find the frame number in the page table
                            tlb.addTo(i,dex,virtualAddress1.get(u).getNumberPage(),buffer,tlb1,x);
                            physicalAddress.addAddress(physicalAddress1,pageTable1,physicalSize,offset,virtualAddress1.get(u).getOffset(),dex);
                            time++;
                            dex++; // everything misses at first since the virtual address is unique
                            //global counter used in the page table and tlb add methods, passed to Controller2
                            s3=" ";
                            miss++;

                        }
                        processList.addProcess(i, virtualAddress1);

                        s = s + "Process " + i + "\n";
                        s = s + processes.toString(virtualAddress1);

                    }
                for(int t=1;t<=process;t++)
              {s2 = s2 + "Page table for process " + t + "\n";
                    s2 = s2 + pageTable.toString(pageTable1, t);}

                    physicalMemory physicalMemory1 = new physicalMemory(physicalAddress1);
                    s3 = s3 + physicalMemory1.toString(physicalAddress1);



                    s4=s4+ tlb.toString(buffer);
                    s5=String.valueOf(time);

                    view.setTextArea(s);
                    view.setTextArea3(s2);
                    view.setTextArea4(s3);
                   view.setTextArea2(s4);
                    view.setTextArea_3_2(s5);
                    view.setTextArea_3(hit);
                    view.setTextArea_3_1(miss);
                    Controller2 controller2=new Controller2(view,processList,dex,process,pageTable1,physicalMemory1,time,buffer,tlb1,physicalSize,offset,hit,miss);
                }
            } catch (Exception ex) {
                view2.showMessage("Something went wrong!");
                ex.printStackTrace();
            }
        }
    }


    }

