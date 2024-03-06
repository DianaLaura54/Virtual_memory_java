import java.awt.print.Pageable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.log;

public class pageTable {
    private int index;

    private int page;
    private int frame;
    private int valid;



    public pageTable(int index, int page, int frame, int valid) {
        this.index = index;
        this.page = page;
        this.frame = frame;
        this.valid = valid;

    }

    public int getIndex() {
        return index;
    }


    public void setIndex(int index) {
        this.index = index;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setFrame(int frame) {

        this.frame = frame;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }



    public int getPage() {
        return page;
    }

    public int getValid() {
        return valid;
    }

    public int getFrame() {
        return frame;
    }



    public static void addTo(int index, int page, int frame, List<pageTable> pageTableList, int physicalSize, int offset) {

        int ok2=0;
        int frame2=0;
        for(int i=1;i<=pageTableList.size();i++) // if the page exists in a different process,put the valid bit 0 for that process
        {
            int index2 = pageTableList.get(i - 1).getIndex();
            int page2 = pageTableList.get(i - 1).getPage();
            int valid = pageTableList.get(i - 1).getValid();

            if(valid==1 && index2!=index && page2==page)
            {
                pageTableList.get(i-1).setValid(0);
                frame2=pageTableList.get(i-1).getFrame();
                ok2=1; // found the page to be 'swapped'
            }
        }
        for(int i=1;i<=pageTableList.size();i++)
        {
            int index2 = pageTableList.get(i - 1).getIndex();
            int page2 = pageTableList.get(i - 1).getPage();
            int valid = pageTableList.get(i - 1).getValid();
            if(valid==0 && index2==index && page2==page && ok2==1)
            {
                pageTableList.get(i-1).setValid(1); //change the process of the page if it is the case
                pageTableList.get(i-1).setFrame(frame2);

            }
        }

        if(ok2==0) { /*hit==0*/
            if (frame < physicalSize / Math.pow(2, offset)) { // no hit found,add it to an empty free case

                for (int i = 1; i <= pageTableList.size(); i++) {
                    int index2 = pageTableList.get(i - 1).getIndex();
                    int page2 = pageTableList.get(i - 1).getPage();
                    int valid = pageTableList.get(i - 1).getValid();

                    if (index2 == index && page2 == page && valid == 0) {

                        pageTableList.get(i - 1).setFrame(frame);
                        pageTableList.get(i - 1).setValid(1);
                    }

                }
            } else { // add it to an already existing frame,delete it from it, use FIFO
int ok=0;
frame= (int) (frame%(physicalSize / Math.pow(2, offset)));

                for (int i = 1; i <= pageTableList.size(); i++) {

                    int index2 = pageTableList.get(i - 1).getIndex();
                    int frame3 = pageTableList.get(i - 1).getFrame();
                    int valid = pageTableList.get(i - 1).getValid();
                    if (frame3 == frame && valid == 1) { // the same method as before of replacing for the process
                        pageTableList.get(i - 1).setValid(0);
                        ok++;
                    }
                }
                for (int i = 1; i <= pageTableList.size(); i++) {
                    int index2 = pageTableList.get(i - 1).getIndex();
                    int page2 = pageTableList.get(i - 1).getPage();
                    int valid = pageTableList.get(i - 1).getValid();
                    if (index2 == index && page2 == page && valid == 0 && ok==1) {

                        pageTableList.get(i - 1).setFrame(frame);
                        pageTableList.get(i - 1).setValid(1);

                    }
                }
            }
        }
    }

    public static void initialize(int memorySize, int offset, int index, List<pageTable> pageTableList, int process) {
        int nr = (int) (memorySize / Math.pow(2, offset));
        for (int i = 1; i <= process; i++) {

            for (int j = 0; j < nr; j++) {
                pageTableList.add(new pageTable(i,j,-1,0));
            }

        }

    }
    public static String toString(List<pageTable> pageTableList, int j) {
        StringBuilder result = new StringBuilder();
        if (!pageTableList.isEmpty()) {
            for(int i=1;i<pageTableList.size();i++)
            {int index = pageTableList.get(i - 1).getIndex();
                if(index==j)
                {int page=pageTableList.get(i-1).getPage();
                    int frame=pageTableList.get(i-1).getFrame();
                    int valid=pageTableList.get(i-1).getValid();
                    result.append(page).append(", ").append(frame).append(", ").append(valid).append("\n");}}

        }
        return result.toString();
    }
    public static int findFrame(List<pageTable>pageTableList,int page)
    { int x=-1;
        for(int i=1;i<=pageTableList.size();i++)
        {
            if(pageTableList.get(i-1).getPage()==page && pageTableList.get(i-1).getValid()==1)
            {
                x=pageTableList.get(i-1).getFrame(); //search the frame number in the page table
            }
        }
        return x;
    }




}
