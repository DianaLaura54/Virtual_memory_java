import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.log;

public class virtualAddress {
    private int numberPage;
    private int offset;
    public virtualAddress(int numberPage,int offset)
    {
        this.offset=offset;
        this.numberPage=numberPage;
    }


    public static void generate(int virtualmemorysize, int offset1,List <virtualAddress> virtualAddressList,List<Integer> lista) {

        int instructionLength= (int)(Math.log(virtualmemorysize) / Math.log(2));
        int page = instructionLength - offset1;

        int power = (int) Math.pow(offset1, 2); //generate a random virtual address
        int power2 = (int) Math.pow(page, 2);
        int offset2 = (int) (Math.random() * (power-0 + 1));

        int numberPage1;
        do {
            numberPage1 = (int) (Math.random() * (power2 - 0 + 1)) + 0;
    } while (isNumberPageInList(numberPage1, lista));


        virtualAddress virtualAddress=new virtualAddress(numberPage1,offset2);
        virtualAddressList.add(virtualAddress);
    }

    private static boolean isNumberPageInList(int numberPage, List<Integer> lista) {
        for (int i = 0; i < lista.size(); i++) {  //checks if the virtual address is in the list
            if (lista.get(i)==numberPage) {
                return true;
            }
        }
        return false;
    }
    public int getOffset() {
        return offset;
    }
   public int getNumberPage()
   {
       return numberPage;
   }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void setNumberPage(int numberPage) {
        this.numberPage = numberPage;
    }


}
