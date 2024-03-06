import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Controller2 {
    private view view;
    private int dex;
    private int process;
    private int time;
    private processes processList;
    private List<pageTable> pageTable1;
    private physicalMemory physicalMemory1;
    private List<tlb> buffer;
    private int tlb1;
    private int physicalSize;
    private int offset;
    private int miss;
    private int hit;


    public Controller2(view view, processes processList, int dex, int process, List<pageTable> pageTable1, physicalMemory physicalMemory1, int time,List<tlb> buffer, int tlb1, int physicalSize, int offset, int hit, int miss) {
        this.view = view;
        this.view.addCreateListener2(new CreateListener2());
        this.view.addCreateListener3(new CreateListener3());

        this.dex = dex;

        this.process = process;
        this.processList = processList;
        this.pageTable1 = pageTable1;
        this.physicalMemory1 = physicalMemory1;
        this.time = time;
        this.buffer = buffer;
        this.tlb1 = tlb1;
        this.physicalSize = physicalSize;
        this.offset = offset;
        this.hit = hit;
        this.miss = miss;


    }

    class CreateListener2 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int x = view.gettextField1();//process
                int y = view.gettextField2();//page
                int z = view.gettextField3(); //offset
                List<virtualAddress> virtualAddress1 = processList.getVirtualAddressList(x);
                virtualAddress address = new virtualAddress(y, z);
                List<physicalAddress> physicalAddress1 = physicalMemory1.getPhysicalAddressList();
                String s = "";
                String s2 = "";
                String s3 = "";
                String s4 = "";
                String s5 = " ";
                String s6= "";
                if (processes.findInformation(virtualAddress1,y) == 0) {//find if the page exists in that specific process,if it does leave it alone,if not add it
                    virtualAddress1.add(address);
                    processList.addProcess(x, virtualAddress1);}
                int var=physicalAddress.hitMiss(buffer,pageTable1,x,y); //search for that page,if it exists in the tlb or page table
                if(var==0)
                {
                    miss++; //it doesn't exist anywhere

                }
                else if(var==3)
                {
                    dex--; //the page exists but it is not that process,so it is a hit anyway
                    hit++;

                }
                else
                {
                 dex--; //the page exists and it is in that process,so it is a hit
                 hit++;

                }
                    pageTable.addTo(x,y,dex,pageTable1,physicalSize,offset); //add it to the page table or use FIFO in the page table
                int q=pageTable.findFrame(pageTable1,y); //find the frame number and modify the tlb, put the physical address in RAM
                    tlb.addTo(x,dex,y,buffer,tlb1,q);
                    physicalAddress.addAddress(physicalAddress1,pageTable1,physicalSize,offset,z,dex);
                    dex++;
                    time++;


                for (int i = 1; i <= process; i++) {
                    s = s + "Process " + i + "\n";
                    s = s + processes.toString(processList.getVirtualAddressList(i));
                }

                for(int t=1;t<=process;t++)
                {s2 = s2 + "Page table for process " + t + "\n";
                    s2 = s2 + pageTable.toString(pageTable1, t);}

                physicalMemory physicalMemory1 = new physicalMemory(physicalAddress1);

                s3 = s3 + physicalMemory1.toString(physicalAddress1);
                s5=s5+ tlb.toString(buffer);
                s6=String.valueOf(time);

                view.setTextArea(s);
                view.setTextArea3(s2);
                view.setTextArea4(s3);
                view.setTextArea_3_2(s4);
                view.setTextArea_3(hit);
                view.setTextArea_3_1(miss);
                view.setTextArea2(s5);
                view.setTextArea_3_2(s6);
            } catch (Exception ex) {
                view.showMessage("Something went wrong!");
                ex.printStackTrace();
            }
        }
    }

    class CreateListener3 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int x = view.gettextField1();//process
                int y = view.gettextField2();//page
                int z = view.gettextField3(); //offset

                List<physicalAddress> physicalAddress1 = physicalMemory1.getPhysicalAddressList();
                String builder= physicalAddress.findInformation(pageTable1,buffer,x,y); //find if the page is in the TLB,page table,or if it exists
                view.showMessage(builder);


            } catch (Exception ex) {
                view.showMessage("Something went wrong!");
                ex.printStackTrace();
            }
        }
    }
}





