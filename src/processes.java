import java.util.*;

import static java.lang.Math.log;


public class processes {
    Map<Integer, List<virtualAddress>> processesMap;

    public processes()
    {
        this.processesMap = new HashMap<>();

    }
    public static String toString(List<virtualAddress> virtualAddressList) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < virtualAddressList.size(); i++) {

            result.append(virtualAddressList.get(i).getNumberPage()).append(" ").append(virtualAddressList.get(i).getOffset()).append("\n");
        }
        return result.toString();
    }
    public void addProcess(int index, List<virtualAddress> virtualAddressList) {
        processesMap.put(index, virtualAddressList);
    }

    public List<virtualAddress> getVirtualAddressList(int index) {
        return processesMap.get(index);
    }

    public static int findInformation(List <virtualAddress> virtualAddressList,int page)
    { int ok=0;
        for(int i=0;i<virtualAddressList.size();i++)
        {
            if( virtualAddressList.get(i).getNumberPage()==page)
            {
                ok=1;  //find if the page exists in the virtual address list
            }
        }
        return ok;
    }

}
