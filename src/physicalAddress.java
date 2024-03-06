import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.log;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.log;

public class physicalAddress {
    private int content;
    private int frameNumber;
    private int offset;


    public physicalAddress(int frameNumber,int content,int offset)
    {
        this.content=content;
        this.frameNumber=frameNumber;
        this.offset=offset;

    }

    public void setContent(int frame) {

        this.content=frame;
    }

    public void setFrameNumber(int frameNumber) {
        this.frameNumber = frameNumber;
    }

    public int getContent() {
        return content;
    }
    public int getFrame()
    {
        return frameNumber;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public static void initialize(int pagesize, int offset, List <physicalAddress> physicalAddressList)
    {
        int physicalPageSize= (int) (pagesize/Math.pow(2,offset));
        for(int i=0;i<physicalPageSize;i++)
        {
            physicalAddress physicalAddress1=new physicalAddress(i,-1,-1);
            physicalAddressList.add(physicalAddress1);
        }

    }
    public static void addAddress(List <physicalAddress> physicalAddressList,List <pageTable> pageTableList,int physicalSize,int offset,int offset2,int page)
    {
       for(int i=1;i<=pageTableList.size();i++)
       {
           int valid=pageTableList.get(i-1).getValid();
           int frame2=pageTableList.get(i-1).getFrame();
           int page2=pageTableList.get(i-1).getPage();
          if(valid==1 && frame2!=-1) //add the physical address, if it is valid in the page table
          {
         physicalAddressList.get(frame2).setFrameNumber(frame2);
         physicalAddressList.get(frame2).setContent(page2);
       }

    }}

    public static String findInformation(List <pageTable> pageTableList,List <tlb> buffer,int process,int page)
    {String builder=" ";
        int ok=0;
       for(int i=1;i<=buffer.size();i++)
       { if(buffer.get(i-1).getProcess()==process && buffer.get(i-1).getPage()==page)
       {
          builder="I found it in the TLB";
           ok=1;
       }

       }
       if(ok==0)
       {
           for(int i=1;i<=pageTableList.size();i++)
           if(pageTableList.get(i-1).getIndex()==process && pageTableList.get(i-1).getPage()==page && pageTableList.get(i-1).getFrame()!=-1 && pageTableList.get(i-1).getValid()==1)

           {
               builder="I found it in the page table";
               ok=1;
           }

       }
       if(ok==0)
       {
           builder="I couldn't find the page mapped";
       }
        return builder;
    }
    public static int hitMiss(List<tlb> buffer,List <pageTable> pageTableList,int process,int page)
    {
        int var=0;//0 for miss,1 and 2 for hit
        for(int i=1;i<=buffer.size();i++)
        {
            if(buffer.get(i-1).getProcess()==process && buffer.get(i-1).getPage()==page)
            {
               var=2; //it exists in the tlb
            }
        }
        if(var!=2)
        {
            for(int i=1;i<=pageTableList.size();i++)
            {
                if( pageTableList.get(i-1).getPage()==page && pageTableList.get(i-1).getFrame()!=-1 && pageTableList.get(i-1).getValid()==1)
                {

                    var=3; //the page exists but it is not that process
                }
                if(pageTableList.get(i-1).getIndex()==process && pageTableList.get(i-1).getPage()==page && pageTableList.get(i-1).getFrame()!=-1 && pageTableList.get(i-1).getValid()==1)
                {
                    var=1; //the page exists at that specific process
                }
            }
        }
        return  var;
    }

}
