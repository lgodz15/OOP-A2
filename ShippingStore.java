package shippingstore;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

/**
 * Will contain all function calls and checks for user input
 * @author lauragodinez
 */
public class ShippingStore {
    private ArrayList<Package> packages = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    
    public ShippingStore(){
        //checks for input data coming in from serializable objects?
    }


    public void addPackage(String type) throws IOException {
        String typeL = type.toLowerCase();
        System.out.println("\nPlease type description of package with the following pattern:\n");

        inputMSG(typeL); //according to type of package
        
        Scanner input = new Scanner(System.in);
        String inTemp = input.nextLine();

        String temp[] = inTemp.split(" ");

        //all have five fields being entered
        if (temp.length != 5) {
            System.out.println("Not correct number of fields to process.");
            return;
            }
        
        if(!(checkPackIn(temp[0],temp[1],temp[2]))){
            return; //to main class if any values entered return false
        }
        
        boolean wrongI = false; //if wrong input for any package specific, return to main
        switch(typeL){
            case "envelope":
                
                Envelope envelopePackage = new Envelope(temp[0], temp[1], temp[2],   
                                                Integer.parseInt(temp[3]), Integer.parseInt(temp[4]));
                packages.add(envelopePackage);  
                break;
            case "box":
                
                if (!temp[4].matches("[0-9]{1,6}")) {
                    System.out.println("Invalid volume:\n"
                    + "The package's volume has to be an integer number between 0 and 999999. ");
                    wrongI = true;
                }
                Box boxPackage = new Box(temp[0], temp[1], temp[2],   
                                    Integer.parseInt(temp[3]), Integer.parseInt(temp[4]));
                packages.add(boxPackage);
                break;
            case "crate":
                
                Crate cratePackage = new Crate(temp[0], temp[1], temp[2],   
                                    Float.parseFloat(temp[3]), temp[4]);
                packages.add(cratePackage);
                break;
            case "drum":
                
                Drum drumPackage = new Drum(temp[0], temp[1], temp[2],   
                                    temp[3], Float.parseFloat(temp[4]));
                packages.add(drumPackage);
                break;
            default:
                break;
        }
        
        if(wrongI){
            return;
        }
        
//        if (type.toLowerCase() == "envelope"){
//            
//            System.out.println("Enter height (in inches): ");
//            input = new Scanner(System.in);
//            int height = input.nextInt();
//            
//            System.out.println("Enter the width of your package (in inches):");
//            input = new Scanner(System.in);
//            int width = input.nextInt();  
//        }
        
    }
    
    private boolean checkPackIn(String trackNum, String spec, String mailClass){
        if (this.searchPackAL(trackNum) != -1) {
            System.out.println("Package Order already exists in database. \n");
            return false;
        }

        if (!trackNum.matches("[A-Za-z0-9]{5}")) {
            System.out.println("Invalid Tracking Number: not proper format."
                + "Tracking Number must be at least 5 alphanumeric characters.");
            return false;
        }

        if (!(spec.equals("Fragile") || spec.equals("Books") || spec.equals("Catalogs")
            || spec.equals("Do-not-Bend") || spec.toUpperCase().equals("N/A"))) {
            System.out.println("Invalid specification:\n"
                + "Specification must be one of following: "
                + "Fragile, Books, Catalogs, Do-not-Bend, N/A.");
            return false;
        }

        if (!(mailClass.equals("First-Class") || mailClass.equals("Priority") || mailClass.equals("Retail")
            || mailClass.equals("Ground") || mailClass.equals("Metro")) ) {
            System.out.println("Invalid Mailing Class:\n"
                + "Mailing Class must be one of following: "
                + "First-Class, Priority, Retail, Ground, Metro.");
            return false;
        }
        
        return true; //passes all cases
    }
    
    /**
     * Method showPackageOrer displays the current list of package orders in the Arraylist in no
     * particular order.
     *
     */
    public void showPackageOrders() {
        showPackageOrders(packages);
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
        
        //have to sort by trackin number
        //find out how to output type of package
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
    
    /**
     * This method will remove an order from the <CODE>packageOrerList</CODE> ArrayList. It
     * will remove the instance of an order that matches tracking number that was
     * passed to this method. If no such order exists, it will produce an error message.
     *
     * @param trackNum of object to be removed.
     */
    public void deletePack(String trackNum){
        int index = searchPackAL(trackNum);
        if(index != -1){
            packages.remove(index);
            System.out.println("Package with Tracking Number " + trackNum + " deleted.");
        }
        else
            System.out.println("\nAction failed. No package order with the given tracking # exist in database.\n");
        
    }
    
    private void inputMSG(String type){
        switch (type) {
            case "envelope":
                System.out.println("\n TRACKING #    SPECIFICATION   CLASS   HEIGHT   WIDTH\n"
                        + "example:\nGFR23 Books Retail 24 45\n");
                break;
            case "box":
                System.out.println("\n TRACKING #    SPECIFICATION   CLASS   "
                        + "LARGEST DIMENSION   VOLUME\n"
                        + "example:\nGFR23 Books Retail 45 64\n");
                break;
            case "drum":
                System.out.println("\n TRACKING #    SPECIFICATION   CLASS   MATERIAL   DIAMETER\n"
                        + "example:\nGFR23 Books Retail PLASTIC 30\n");
                break;
            case "crate":
                System.out.println("\n TRACKING #    SPECIFICATION   CLASS   "
                        + "MAXIUMUM LOAD WEIGHT   CONTENT\n"
                        + "example:\nGFR23 Books Retail 100.5 CHAIR\n");
                break;
            default:
                break;
        }
        
    }
    
    public void addUser(String userInput){
        int id;
        String fName, lName;
        Scanner input = new Scanner(System.in);
        
        //get random generated ID
        id = randUserId();
        //get first name and last name from user
        System.out.println("Enter your first name: ");
        input.nextLine();
        fName = input.nextLine();
        System.out.println("Enter your last name: ");
        input.nextLine();
        lName = input.nextLine();
        
        switch(userInput){
            case "employee":
                
                break;
            case "customer":
                
                break;
            default:
                break;
        }
            
    }
    
    public int randUserId(){
        int max = 99999999;
        int min = 10000000;
        int id; 
        Random rand = new Random();
        id = rand.nextInt(max - min + 1) + min;

        return id;
    }
}
