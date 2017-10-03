package shippingstore;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.util.Comparator;
import java.util.Collections;
import java.io.Serializable;
import java.io.*;


/**
 * Will contain all function calls and checks for user input
 * @author lauragodinez
 */
public class ShippingStore implements Serializable {
    private ArrayList<Package> packages = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Transaction> trans = new ArrayList<>();
    
    public ShippingStore(){
        //checks for input data coming in from serializable objects?
        try {
         FileInputStream fileIn = new FileInputStream("packages.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         packages = (ArrayList<Package>) in.readObject();
         in.close();
         fileIn.close();
        }
        catch(IOException i) {
         System.out.println("packages.ser file not found");
        }
        catch(ClassNotFoundException c) {
         System.out.println("Package class not found");
        }
        
        try {
         FileInputStream fileIn = new FileInputStream("users.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         users = (ArrayList<User>) in.readObject();
         in.close();
         fileIn.close();
        }
        catch(IOException i) {
         System.out.println("users.ser file not found");
        }
        catch(ClassNotFoundException c) {
         System.out.println("User class not found");
        }
        
        try {
         FileInputStream fileIn = new FileInputStream("transactions.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         trans = (ArrayList<Transaction>) in.readObject();
         in.close();
         fileIn.close();
        }
        catch(IOException i) {
         System.out.println("transactions.ser file not found");
        }
        catch(ClassNotFoundException c) {
         System.out.println("Transaction class not found");
        }
    }


    public void addPackage(String type) throws IOException {
        String typeL = type.toLowerCase();
        System.out.println("\n Please type description of package with the following pattern:");

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
        
        switch(typeL){
            case "envelope":
                if(Integer.parseInt(temp[3]) <= 0 || Integer.parseInt(temp[4]) <= 0){
                    System.out.println("Invalid Height or Width. Both must be over 0.");
                    break;
                }
                Envelope envelopePackage = new Envelope(temp[0], temp[1], temp[2],   
                                                Integer.parseInt(temp[3]), Integer.parseInt(temp[4]));
                packages.add(envelopePackage);  
                break;
            case "box":
                if(Integer.parseInt(temp[3]) <= 0){
                    System.out.println("Invalid Dimension. Must be over 0.");
                    break;
                }
                //check it's not a ridiculous volume
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
                if (Float.parseFloat(temp[3]) <= 0) {
                    System.out.println("Invalid max load weight:\n"
                    + "The package's weight has to be an integer number between 0 and 999999. ");
                }
                Crate cratePackage = new Crate(temp[0], temp[1], temp[2],   
                                    Float.parseFloat(temp[3]), temp[4]);
                packages.add(cratePackage);
                break;
            case "drum":
                String matL = temp[3].toLowerCase();
                if(!("plastic".equals(matL) || "fiber".equals(matL))){
                    System.out.println("Incorrect material type. Must be Fiber or Plastic");
                    break;
                }
                if(Float.parseFloat(temp[4]) <= 0){
                    System.out.println("Invalid Diameter. Must be over 0.");
                    break;
                }
                Drum drumPackage = new Drum(temp[0], temp[1], temp[2],   
                                    temp[3], Float.parseFloat(temp[4]));
                packages.add(drumPackage);
                break;
            default:
                break;
        }
        

        
    }
    
    private boolean checkPackIn(String trackNum, String spec, String mailClass){
        if (searchPackAL(trackNum) != -1) {
            System.out.println("Package Order already exists in database. \n");
            return false;
        }

        if (!trackNum.matches("[A-Za-z0-9]{5}")) {
            System.out.println("Invalid Tracking Number: not proper format."
                + "Tracking Number must be at least 5 alphanumeric characters.");
            return false;
        }

        String specL = spec.toLowerCase();
        if (!(specL.equals("fragile") || specL.equals("books") || specL.equals("catalogs")
            || specL.equals("do-not-bend") || specL.toUpperCase().equals("n/a"))) {
            System.out.println("Invalid specification:\n"
                + "Specification must be one of following: "
                + "Fragile, Books, Catalogs, Do-not-Bend, N/A.");
            return false;
        }

        String mailL = mailClass.toLowerCase();
        if (!(mailL.equals("first-class") || mailL.equals("priority") || mailL.equals("retail")
            || mailL.equals("ground") || mailL.equals("metro")) ) {
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
        //sort by tracking number here
        Collections.sort(packages, new Comparator<Package>() {
            @Override
            public int compare(Package object1, Package object2) {
                return object1.getTN().compareTo(object2.getTN());
        }
        });
        showPackageOrders(packages);
    }
    
    /**
     * Private method used as an auxiliary method to display a given ArrayList
     * of package orders in a formatted manner.
     *
     * @param orders the package order list to be displayed.
     */
    private void showPackageOrders(ArrayList<Package> orders) {

        System.out.println(" ---------------------------------------------------------------------------------- ");
        System.out.println("| Tracking # | Specification | Class         | Type    | Other                       |");
        System.out.println(" ---------------------------------------------------------------------------------- ");
        
        //have to sort by trackin number
        //find out how to output type of package
        for (int i = 0; i < orders.size(); i++) {
            System.out.print(String.format("| %-11s| %-14s| %-14s| ",
                    orders.get(i).getTN(),
                    orders.get(i).getSpec(),
                    orders.get(i).getMC()));
            if(orders.get(i) instanceof Envelope){
                System.out.print("Envelope|");
                System.out.print(" Height: " + orders.get(i).getH());
                System.out.println(" Width: " + orders.get(i).getW());
            }
            else if (orders.get(i) instanceof Box){
                System.out.print("Box     |");
                System.out.print(" Largest Dimension: " + orders.get(i).getLD());
                System.out.println(" Volume: " + orders.get(i).getV());
            }
            else if (orders.get(i) instanceof Crate){
                System.out.print("Crate   |");
                System.out.print(" Max Load Weight: " + orders.get(i).getML());
                System.out.println(" Content: " + orders.get(i).getC());
            }
            else if(orders.get(i) instanceof Drum){
                System.out.print("Drum    |");
                System.out.print(" Material: " + orders.get(i).getM());
                System.out.println(" Diameter: " + orders.get(i).getD());
            }
        }
        System.out.println("\n --------------------------------------------------------------------------\n");

    }    
    
    public void showUsers(){
        System.out.println(" -------------------------------------------------------------------------- ");
        System.out.println("| User Type | ID          | First Name | Last Name       | Other           |");
        System.out.println(" -------------------------------------------------------------------------- ");
        
        //have to sort by trackin number
        //find out how to output type of package
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i) instanceof Employee){
                System.out.print("| Employee  ");
            }
            else if (users.get(i) instanceof Customer){
                System.out.print("| Customer  ");
            }
            System.out.print(String.format("| %-11s| %-14s| %-14s| ",
                    users.get(i).getID(),
                    users.get(i).getFN(),
                    users.get(i).getLN()));
            if(users.get(i) instanceof Employee){
                int tempSocial = users.get(i).getSSN();
                float tempMonthlySalary = users.get(i).getSalary();
                int tempBankAccount = users.get(i).getBank();
                System.out.print("SSN: " + tempSocial + ", Salary: $" + tempMonthlySalary + ", Bank Account: " + tempBankAccount + "\n");
                //int ssn = users.get(i).getSSN();
            }
            else if (users.get(i) instanceof Customer){
                String tempPhone = users.get(i).getPN();
                String addr = users.get(i).getAddress();
                System.out.print("Phone #: " + tempPhone + ", Address: " + addr + "\n");
                //System.out.print("Address: " + addr + "\n");
            }

            
        }
        System.out.println("--------------------------------------------------------------------------\n");
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
        
        System.out.println("Please enter the Customer ID who completed the transaction");
        Scanner inputID = new Scanner(System.in);
        String packageNumber = ""; 
        float packageCost = 0;
        int employID = 0;
        
        int custID = inputID.nextInt();
        String custIDString = Integer.toString(custID);
        while (custIDString.length() != 8 || !custIDString.matches("[0-9]+")){
            System.out.println("Error! Please punch in an 8-digit number! Try again: ");
            inputID = new Scanner(System.in);
            custID = inputID.nextInt();
            custIDString = Integer.toString(custID);
        } 
        
        int packIndex = 0;
        for (int i = 0; i < users.size(); i++){
                if (users.get(i).getID() == custID){
                    System.out.println("Please enter in a Package Tracking Number: ");
                    inputID = new Scanner(System.in);
                    packageNumber = inputID.nextLine();
                    while (packageNumber.length() != 5){
                        System.out.println("Error! Tracking is 5 characters ! Try again: ");
                        inputID = new Scanner(System.in);
                        packageNumber = inputID.nextLine();
                    }
                    for (int b = 0; b < packages.size(); b++) {
                        Package packageObject = packages.get(b);
                        if (packageNumber.equals(packageObject.getTN())){
                            packIndex = b;
                            System.out.println("Package found! Added to Transactions");
                        } else {
                            System.out.println("Package not found!");
                            return;
                        }
                    }
                    System.out.println("Please enter the cost of the package: ");
                    inputID = new Scanner(System.in);
                    packageCost = inputID.nextFloat();
                    String costString = Float.toString(packageCost);
                    //USE TRY & CATCH HERE
                    while (!costString.matches("[0-9]+")){
                        System.out.println("Error! Please punch in only numbers for cost! Try again: ");
                        inputID = new Scanner(System.in);
                        packageCost = inputID.nextFloat();
                        costString = Float.toString(packageCost);
                    }
                    System.out.println("Please enter in Employee ID who completed Sale: ");
                    inputID = new Scanner(System.in);
                    employID = inputID.nextInt();
                    String employIDString = Integer.toString(employID);
                    while (employIDString.length() != 8 || !employIDString.matches("[0-9]+")){
                        System.out.println("Error! Please punch in an 8-digit number! Try again: ");
                        inputID = new Scanner(System.in);
                        employID = inputID.nextInt();
                        employIDString = Integer.toString(employID); 
                        } 
                    for (int j = 0; j < users.size(); j++){
                        User userObject2 = users.get(j);
                        if (userObject2.getID() == employID){
                            System.out.println("Employee Found! Added to Transactions!");
                        } else {
                            System.out.println("Employee not found!");
                            return;
                        } 
                    }
                }    
        }
          System.out.println("Customer not found!");
                 System.out.println("Press enter to continue");
               try {
                   System.in.read();
                   return;
               } catch (IOException ex) {
                   
               } 
        Transaction transactionObject = new Transaction(custID, packageNumber, 
                packages.get(packIndex).getDate(), packageCost, employID);
        trans.add(transactionObject);
        //REMOVE packageNumber from package list
    }
    
    private int searchPackAL(String trackNum){
        int exist = -1;
        for(int i = 0; i < packages.size(); i++){
            if(trackNum.equals(packages.get(i).getTN())){
                exist = i;
                break;
            } 
        }
        
        return exist;
    }
    
    public void searchPack(String trackNum){
        int index = searchPackAL(trackNum);
        if (index != -1) {
            ArrayList<Package> order = new ArrayList<>(1);
            System.out.println("\nHere is the order that matched:\n");
            order.add(packages.get(index));
            showPackageOrders(order);
        }
        else{
            System.out.println("Package with Tracking Number " + trackNum
            + " not found.");
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
                        + " example: GFR23 Books Retail 24 45\n");
                break;
            case "box":
                System.out.println("\n TRACKING #    SPECIFICATION   CLASS   "
                        + "LARGEST DIMENSION   VOLUME\n"
                        + " example: GFR23 Books Retail 45 64\n");
                break;
            case "drum":
                System.out.println("\n TRACKING #    SPECIFICATION   CLASS   MATERIAL   DIAMETER\n"
                        + " example: GFR23 Books Retail PLASTIC 30\n");
                break;
            case "crate":
                System.out.println("\n TRACKING #    SPECIFICATION   CLASS   "
                        + "MAXIUMUM LOAD WEIGHT   CONTENT\n"
                        + " example: GFR23 Books Retail 100.5 CHAIR\n");
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
        fName = input.nextLine();
        System.out.println("Enter your last name: ");
        input = new Scanner(System.in);
        lName = input.nextLine();

        switch(userInput.toLowerCase()){
            case "employee":
                System.out.println("Please enter in an 8 digit social security number: ");
                Scanner SSN = new Scanner(System.in);
                int number = SSN.nextInt();
                String numberString = Integer.toString(number);
                while (numberString.length() != 8){
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

                System.out.println("Please enter in an 7 digit phone number: ");
                Scanner phoneNumber = new Scanner(System.in);
                String phone = phoneNumber.nextLine();
                while (phone.length() != 7){
                    System.out.println("That number is not 7 digits, please try again: ");
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
                    User x = users.get(index);
                    
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
                            users.get(index).setfName(userNewfName);
                            break;
                            
                        case 2:
                            System.out.println("Enter the replacement last name: ");
                            Scanner newlName = new Scanner(System.in);
                            String userNewlName = newlName.nextLine();
                            users.get(index).setlName(userNewlName);
                            break;
                            
                        case 3:
                            System.out.println("Enter the replacement SSN: ");
                            Scanner newSSN = new Scanner(System.in);
                            int userNewSSN = newSSN.nextInt();
                            String newSSNString = Integer.toString(userNewSSN);
                            while (newSSNString.length() != 8){
                                System.out.println("That number is not 8 digits, please try again: ");
                                newSSN = new Scanner(System.in);
                                userNewSSN = newSSN.nextInt();
                                newSSNString = Integer.toString(userNewSSN);
                            }
                            users.get(index).setSSN(userNewSSN);//get index
                            break;
                            
                        case 4:
                            System.out.println("Please enter a monthly salary: ");
                            Scanner newSal = new Scanner(System.in);
                            float userNewSalary = newSal.nextFloat();
                            
                            users.get(index).setSalary(userNewSalary);
                            break;
                            
                        case 5:
                            System.out.println("Please enter in a replacement 6-digit Bank Account Number: ");
                            Scanner newBank = new Scanner(System.in);
                            int newUserBank = newBank.nextInt();
                            String bankString = Integer.toString(newUserBank);
                            while (bankString.length() != 6){
                                System.out.println("Incorrect number of digits, try again: ");
                                newBank = new Scanner(System.in);
                                newUserBank = newBank.nextInt();
                                bankString = Integer.toString(newUserBank);
                            } 
                            users.get(index).setBank(newUserBank);
                        default:
                            break;     
                    }
                } else if (users.get(index) instanceof Customer) {
                    
                    System.out.println("What information would you like to change? ");
                    System.out.println("1) First Name");
                    System.out.println("2) Last Name");
                    System.out.println("3) Phone Number");
                    System.out.println("4) Address");
                    
                    Scanner selection = new Scanner(System.in);
                    int userSelection = selection.nextInt();
                    while (userSelection < 1 && userSelection >4){
                        System.out.println("That is an invalid selection, try again: ");
                        selection = new Scanner(System.in);
                        userSelection = selection.nextInt();
                    } 
                    switch(userSelection) {
                        case 1:
                            System.out.println("Enter the replacement first name: ");
                            Scanner newfName = new Scanner(System.in);
                            String userNewfName = newfName.nextLine();
                            users.get(index).setfName(userNewfName);
                            break;
                            
                        case 2:
                            System.out.println("Enter the replacement last name: ");
                            Scanner newlName = new Scanner(System.in);
                            String userNewlName = newlName.nextLine();
                            users.get(index).setlName(userNewlName);
                            break;
                            
                        case 3:
                            System.out.println("Please enter in a replacement 7 digit phone number: ");
                            Scanner phoneNumber = new Scanner(System.in);
                            String phone = phoneNumber.nextLine();
                            while (phone.length() != 7){
                                System.out.println("That number is not 7 digits, please try again: ");
                                phoneNumber = new Scanner(System.in);
                                phone = phoneNumber.nextLine();
                            }
                            users.get(index).setPhone(phone);
                            break;
                            
                        case 4:
                            System.out.println("Please enter a replacement address for the customer: ");
                            Scanner address = new Scanner(System.in);
                            String location = address.nextLine();
                            users.get(index).setAddress(location);
                        
                        default:
                            break;    
                    }
       
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
    
    public void flush(){
        //serializing ArrayLists
        try {
         FileOutputStream fileOut = new FileOutputStream("packages.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(packages);
         out.close();
         fileOut.close();
         System.out.printf("Serialized data is saved as packages.ser\n");
        }
        catch(IOException i) {
         i.printStackTrace();
        }
        
        try {
         FileOutputStream fileOut = new FileOutputStream("users.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(users);
         out.close();
         fileOut.close();
         System.out.printf("Serialized data is saved as users.ser\n");
        }
        catch(IOException i) {
         i.printStackTrace();
        }
        
        try {
         FileOutputStream fileOut = new FileOutputStream("transactions.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(trans);
         out.close();
         fileOut.close();
         System.out.printf("Serialized data is saved as transactions.ser\n");
        }
        catch(IOException i) {
         i.printStackTrace();
        }
    }

}
