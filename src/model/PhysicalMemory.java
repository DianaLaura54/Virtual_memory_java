package model;

import model.PhysicalAddress;

import java.util.List;

public class PhysicalMemory {
    private List<PhysicalAddress> physicalAddressList;

    public PhysicalMemory(List<PhysicalAddress> physicalAddressList) {
        this.physicalAddressList = physicalAddressList;
    }

    public static String toString(List<PhysicalAddress> physicalAddressList) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < physicalAddressList.size(); i++) {

            result.append(physicalAddressList.get(i).getFrame()).append(" ").append(physicalAddressList.get(i).getContent()).append("\n");
        }

        return result.toString();
    }

    public List<PhysicalAddress> getPhysicalAddressList() {
        return physicalAddressList;
    }
}
