package shippingstore;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.util.Date;


/**
 * Will contain all function calls and checks for user input
 * @author lauragodinez
 */
public class ShippingStore {
    private ArrayList<Package> packages = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Transaction> trans = new ArrayList<>();
    
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
                    break;
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
    public void showPackageOrders(){
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
        System.out.println("| Tracking # | Type    | Specification | Class       | Other               |");
        System.out.println(" -------------------------------------------------------------------------- ");
        
        //have to sort by trackin number
        //find out how to output type of package
        for (int i = 0; i < orders.size(); i++) {
            System.out.print(String.format("| %-11s| %-8s| %-14s| %-12s| ",
                    orders.get(i).getTN(),
                    orders.get(i).getSpec(),
                    orders.get(i).getMC()));
            if(orders.get(i) instanceof Envelope){
                //System.out.println((orders.get(i).getH()));
                System.out.println("Envelope");
//                Envelope x = orders.get(i);
            }
            else if (orders.get(i) instanceof Box){
//                Box x = orders.get(i);
                System.out.println("Box");
            }
            else if (orders.get(i) instanceof Crate){
                System.out.println("Crate");
            }
            else if(orders.get(i) instanceof Drum){
                System.out.println("Drum");
            }
        }
        System.out.println(" --------------------------------------------------------------------------\n");

    }    
    
    public void showUsers(){
        System.out.println(" -------------------------------------------------------------------------- ");
        System.out.println("| User Type | ID    | First Name | Last Name | Other               |");
        System.out.println(" -------------------------------------------------------------------------- ");
        
        //have to sort by trackin number
        //find out how to output type of package
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i) instanceof Employee){
                System.out.print("| Employee  ");
            }
            else if (users.get(i) instanceof Customer){
                System.out.print("| Box       ");
            }
            System.out.print(String.format("| %-11s| %-14s| %-14s| ",
                    users.get(i).getID(),
                    users.get(i).getFN(),
                    users.get(i).getLN()));
            if(users.get(i) instanceof Employee){
                System.out.print("Employee  ");
                //int ssn = users.get(i).getSSN();
            }
            else if (users.get(i) instanceof Customer){
                System.out.print("Box       ");
            }

            
        }
        System.out.println(" --------------------------------------------------------------------------\n");
    }
    
    public void showTransactions() {

        System.out.println(" -------------------------------------------------------------------------- ");
        System.out.println("| User ID  | Tracking # | Ship Date | Delivery Date | Cost   | Employee ID  |");
        System.out.println(" -------------------------------------------------------------------------- ");
        
        //have to sort by trackin number
        //find out how to output type of package
        for (int i = 0; i < trans.size(); i++) {
            System.out.print(String.format("| %-11s| %-11s| %-14s| %-14s| %-14s| %-14s|",
                    trans.get(i).getCID(),
                    trans.get(i).getTN(),
                    trans.get(i).getShipD(),
                    trans.get(i).getDelivD(),
                    trans.get(i).getCost(),
                    trans.get(i).getEID()));
        }
        System.out.println(" --------------------------------------------------------------------------\n");

    }
    
    public void completeTransaction(){
        
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
                System.out.println("Please enter in an 8 digit social security number: ");
                Scanner SSN = new Scanner(System.in);
                int number = SSN.nextInt();
                String numberString = Integer.toString(number);
                while (numberString.length() != 5){
                    System.out.println("That number is not 8 digits, please try again: ");
                    SSN = new Scanner(System.in);
                    number = SSN.nextInt();
                    numberString = Integer.toString(number);
                }
                System.out.println("Please enter a monthly salary: ");
                SSN = new Scanner(System.in);
                float salary = SSN.nextFloat();

                System.out.println("Please enter in a 6-digit Bank Account Number: ");
                SSN = new Scanner(System.in);
                int account = SSN.nextInt();
                String accountString = Integer.toString(account);
                while (accountString.length() != 6){
                    System.out.println("Incorrect number of digits, try again: ");
                    SSN = new Scanner(System.in);
                    account = SSN.nextInt();
                    accountString = Integer.toString(account);
                }
                Employee employeeObject = new Employee(id, fName, lName, number, salary, account);
                users.add(employeeObject);
                break;
            case "customer":

                System.out.println("Please enter in an 8 digit phone number: ");
                Scanner phoneNumber = new Scanner(System.in);
                String phone = phoneNumber.nextLine();
                while (phone.length() != 5){
                    System.out.println("That number is not 8 digits, please try again: ");
                    phoneNumber = new Scanner(System.in);
                    phone = phoneNumber.nextLine();
                }
    
                System.out.println("Please enter an address for the customer: ");
                Scanner address = new Scanner(System.in);
                String location = address.nextLine();

                Customer customerObject = new Customer(id, fName, lName, phone, location);
                users.add(customerObject);
                break;
            default:
                break;
        }
    }
    
    private int searchUserAL(int ID){
        int exist = -1;
        for(int i = 0; i < users.size(); i++){
            if((users.get(i).getID()) == ID){
                exist = i;
                break;
            } 
        }
        
        if(exist == -1){
            System.out.println("User with ID " + ID
            + " not found.");
        }
        return exist;
    }
    
public void changeUser(int ID){
        int index = searchUserAL(ID);
        if(index != -1){
        //for (int i=0; i<users.size(); i++){
            if (ID == users.get(index).getID()){
                if (users.get(index) instanceof Employee){
                    Employee x = users.get(index);
                    
                    System.out.println("What information would you like to change? ");
                    System.out.println("1) First Name");
                    System.out.println("2) Last Name");
                    System.out.println("3) Social Security Number");
                    System.out.println("4) Monthly Salary");
                    System.out.println("5) Bank Account Number");
                    
                    Scanner selection = new Scanner(System.in);
                    int userSelection = selection.nextInt();
                    while (userSelection < 1 && userSelection >5){
                        System.out.println("That is an invalid selection, try again: ");
                        selection = new Scanner(System.in);
                        userSelection = selection.nextInt();
                    }
                    
                    switch (userSelection) {
                        case 1:
                            System.out.println("Enter the replacement first name: ");
                            Scanner newfName = new Scanner(System.in);
                            String userNewfName = newfName.nextLine();
                            users.get(i).setfName(userNewfName);
                            
                        case 2:
                            System.out.println("Enter the replacement last name: ");
                            Scanner newlName = new Scanner(System.in);
                            String userNewlName = newlName.nextLine();
                            users.get(i).setlName(userNewlName);
                            
                        case 3:
                            System.out.println("Enter the replacement last name: ");
                            Scanner newSSN = new Scanner(System.in);
                            int userNewSSN = newSSN.nextInt();
                            users.get(x).setSSN(userNewSSN);
                            
                        
                        
                          
                            
                    }
                } else {
                }
            }
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
