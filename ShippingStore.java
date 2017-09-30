package shippingstore;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Will contain all function calls and checks for user input
 * @author lauragodinez
 */
public class ShippingStore {
    private ArrayList<Package> packages = new ArrayList<>();
    
    public ShippingStore(){
        //checks for input data coming in from serializable objects?
    }
    //"envelope".equals(inTemp.toLowerCase())
    public void addpackage() throws IOException {
        System.out.println("Please enter the package type you wish to add: ");
        Scanner input = new Scanner(System.in);
        String entry = input.nextLine();
        while (entry.toLowerCase() != "envelope" && entry.toLowerCase() != "box" && 
               entry.toLowerCase() != "crate" && entry.toLowerCase() != "drum"){
              System.out.println("That is not one of the 4 package types, try again: ");
              input = new Scanner(System.in);
              entry = input.nextLine();
        }
        
        if (entry.toLowerCase() == "envelope"){
            System.out.println("Please enter a 5-digit tracking number");
            String tracking = input.nextLine();
            while (tracking.length() != 5){
                System.out.println("Error! THat is not 5 digits, try again: ");
                input = new Scanner(System.in);
                tracking = input.nextLine();
            }
            
            System.out.println("Please enter specification: ");
            input = new Scanner(System.in);
            String spec = input.nextLine();
            //CHECK: will forward slash stay the same with toLowerCase?
            //CHECK: also do-not-bed
            while (spec.toLowerCase() != "fragile" &&spec.toLowerCase() != "books" &&
                  spec.toLowerCase() != "catalogs" && spec.toLowerCase() != "n/a" && 
                  spec.toLowerCase() != "do-not-bend"){
                System.out.println("That is not a valid specification, try again: ");
                input = new Scanner(System.in);
                spec = input.nextLine();
            }
            
            System.out.println("Enter Mailing class: ");
            input = new Scanner(System.in);
            String mailClass = input.nextLine();
            while (mailClass.toLowerCase() != "first-class" && mailClass.toLowerCase() != "priority" &&
                  mailClass.toLowerCase() != "retail" && mailClass.toLowerCase() != "ground"
                  && mailClass.toLowerCase() != "metro" && mailClass.toLowerCase() != "first"){
                  System.out.println("That is not a valid class, try again: ");
                  input = new Scanner(System.in);
                  mailClass = input.nextLine();  
            }
            
            System.out.println("Enter height (in inches): ");
            input = new Scanner(System.in);
            int height = input.nextFloat();
            
            System.out.println("Enter the width of your package (in inches):");
            input = new Scanner(System.in);
            int width = input.nextInt();
           
                
         Envelope envelopePackage = new Envelope(tracking, spec, mailClass,   
                                                height, width);
         packages.add(envelopePackage);  
        }
        
        if (entry.toLowerCase() == "box"){
            
        }
        
        
        
        
        
        
        
        
        
       
        
        
    }
    
    /**
     * Private method used as an auxiliary method to display a given ArrayList
     * of package orders in a formatted manner.
     *
     * @param orders the package order list to be displayed.
     */
    private void showPackageOrders(ArrayList<Package> orders) {

        System.out.println(" -------------------------------------------------------------------------- ");
        System.out.println("| Tracking # | Type    | Specification | Class       | Other |");
        System.out.println(" -------------------------------------------------------------------------- ");

        for (int i = 0; i < orders.size(); i++) {
            System.out.println(String.format("| %-11s| %-8s| %-14s| %-12s| %-18s|",
                    orders.get(i).getTN(),
                    orders.get(i).getSpec(),
                    orders.get(i).getMC()
                ));
        }
        System.out.println(" --------------------------------------------------------------------------\n");

    }    
    
    private int searchPackAL(String trackNum){
        int exist = -1;
        for(int i = 0; i < packages.size(); i++){
            if(trackNum.equals(packages.get(i).getTN())){
                exist = i;
                break;
            } 
        }
        
        if(exist == -1){
            System.out.println("Package with Tracking Number " + trackNum
            + " not found.");
        }
        return exist;
    }
    
    public void searchPack(String trackNum){
        int index = searchPackAL(trackNum);
        if (index != -1) {
            ArrayList<Package> order = new ArrayList<>(1);
            System.out.println("\nHere is the order that matched:\n");
            showPackageOrders(order);
        }
    }
    
    public void deletePack(String trackNum){
        int index = searchPackAL(trackNum);
        if(index != -1){
            packages.remove(index);
            System.out.println("Package with Tracking Number " + trackNum + " deleted.");
        }
    }
    
}
