import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.math.BigInteger;

public class physicalMemory {
    private List<physicalAddress> physicalAddressList;
    public physicalMemory(List<physicalAddress>physicalAddressList)
    {
        this.physicalAddressList=physicalAddressList;
    }

    public static String toString(List<physicalAddress> physicalAddressList) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < physicalAddressList.size(); i++) {

            result.append(physicalAddressList.get(i).getFrame()).append(" ").append(physicalAddressList.get(i).getContent()).append("\n");}

        return result.toString();
    }

    public List<physicalAddress> getPhysicalAddressList() {
        return physicalAddressList;
    }
}
