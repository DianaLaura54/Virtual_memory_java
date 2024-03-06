import java.util.*;
import java.util.HashMap;
import java.util.Map;
public class tlb {

     private int process;
    private int frame;
    private int page;
    private int frame2;

    public tlb(int process,int frame,int page,int frame2)
    {
        this.process=process;
        this.frame=frame;
        this.page=page;
        this.frame2=frame2;
    }

    public static void initialize(int tlb1, List<tlb> bufferList)
    {
        for(int i=0;i<tlb1;i++)
        {
            bufferList.add(new tlb(-1,i,-1,-1));
        }

    }

    public static void addTo(int index, int frame,int page,List<tlb>buffer, int tlb1,int framex) {
        int ok2=0;

        for(int i=1;i<=buffer.size();i++)
        {
            int page2 = buffer.get(i - 1).getPage(); // if the page already exists so change only the process
            if(page2==page)
            {
                ok2=1;
                buffer.get(i-1).setProcess(index);
            }
        }


   if(ok2==0) {if (frame < tlb1) { //if it doesn't find a hit,it checks if places are available,if yes,add to a new empty frame
int ok=0;
        for (int i = 1; i <= buffer.size(); i++) {
            int frame2 = buffer.get(i - 1).getFrame();
            int page2 = buffer.get(i - 1).getPage();
            if (page2 == -1 && ok==0) {
                for(int j=1;j<=buffer.size();j++){
                buffer.get(i - 1).setPage(page);
                buffer.get(i - 1).setProcess(index);
                buffer.get(i-1).setFrame2(framex);
                ok=1;}

            }
        }
    } else {
        frame = (int) (frame % tlb1);   //if not,add it to the mod % tlb1 frame,left everything else the same

        for (int i = 1; i <= buffer.size(); i++) {
            int index2 = buffer.get(i - 1).getProcess();
            int frame2 = buffer.get(i - 1).getFrame();
            int page2 = buffer.get(i - 1).getPage();
            if (frame == frame2 && page2 != page) {

                buffer.get(i - 1).setPage(page);
                buffer.get(i-1).setFrame2(framex);
                buffer.get(i - 1).setProcess(index);
            }
        }
    }

}}

    public static String toString(List <tlb> buffer)
    {  StringBuilder result = new StringBuilder();

        for (int i=0;i<buffer.size();i++) {
            int process=buffer.get(i).getProcess();
           int page=buffer.get(i).getPage();
            int frame=buffer.get(i).getFrame();
            int framex=buffer.get(i).getFrame2();
            result.append(page).append(":").append(framex).append("\n");
        }
        return result.toString();
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getProcess() {
        return process;
    }

    public void setProcess(int process) {
        this.process = process;
    }

    public int getFrame2() {
        return frame2;
    }

    public void setFrame2(int frame2) {
        this.frame2 = frame2;
    }

    public int getFrame() {
        return frame;
    }
//}

    public void setFrame(int frame) {
        this.frame = frame;
    }

}
