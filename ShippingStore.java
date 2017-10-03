package shippingstore;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.util.Comparator;
import java.util.Collections;
import java.io.Serializable;
import java.io.*;
import java.util.InputMismatchException;


/**
 * ShippingStore contains all function calls and checks for user input
 */
public class ShippingStore implements Serializable {
    private ArrayList<Package> packages = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Transaction> trans = new ArrayList<>();
    
    /**
     * ShippingStore constructor which checks if there is previous input from 
     * .ser files coming into packages, users, or transactions array lists
     */
    public ShippingStore(){
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

    /**
     * addPackage add a packing into packages ArrayList according to type of package
     * entered
     * @param type takes in the type of package 
     * @throws IOException 
     */
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
    
    /**
     * checkPackIn checks that the tracking number, specification, and mailing class
     * entered were entered correctly
     * @param trackNum tracking number of package 
     * @param spec specification of package
     * @param mailClass mailing class of package
     * @return 
     */
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
     * Method showPackageOrer displays the current list of package orders in the Arraylist 
     * alphabetically according to tracking number
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
    
    /**
     * showUsers displays all the users in the users ArrayList
     */
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
    
    /**
     * showTransactions displays all transactions in trans ArrayList
     */
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
    
    /**
     * completeTransaction prompts user to complete a transaction, add it to 
     * trans ArrayList, and delete package from packages ArrayList
     */
    public void completeTransaction(){
       
        String packageNumber = ""; 
        float packageCost = 0;
        int employID = 0;
        Scanner inputID = new Scanner(System.in);
        int custID=0;
        String custIDString = ""; //= Integer.toString(custID);
        boolean goodint=false;
       
        while(goodint==false || !custIDString.matches("[0-9]+") || custIDString.length() != 8){
        try {
            System.out.println("Please enter the Customer ID who completed the transaction");
            
           custID=inputID.nextInt();
           custIDString = Integer.toString(custID);
           if(custIDString.length() != 8)
           {
                System.out.println("Error! Must be 8 digits! "); 
           }
         
           goodint=true;
            
        } catch (InputMismatchException exception) 
        { 
            System.out.println("Integers only, please."); 
        }
          inputID.nextLine();//HOW does this stop the loop?
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
                        }   
    
                    inputID = new Scanner(System.in);
                    boolean goodFloat = false;
                    
                    while (goodFloat == false){
                      try{
                        System.out.println("Please enter the cost of the package(use 2 decimal places): ");
   
                        inputID = new Scanner(System.in);
                        packageCost = inputID.nextFloat();
                        
                        goodFloat = true;
                       
                      }
                      catch (InputMismatchException exception) {
                          System.out.println("Error! Please punch in only numbers for cost! Try again: ");
                      }
                        inputID.nextLine();
                    } 
                    
                    inputID = new Scanner(System.in);
                   
                    String employIDString = ""; 
                    boolean goodEmploy = false;
                    
                    while (goodEmploy == false){
                        try{
                           System.out.println("Please enter in Employee ID who completed Sale: ");
                           inputID = new Scanner(System.in);
                           employID = inputID.nextInt();
                           employIDString = Integer.toString(employID); 
                           while(employIDString.length() != 8)
                             {
                                System.out.println("Error! Must be 8 digits! Try again: ");
                                inputID = new Scanner(System.in);
                                employID = inputID.nextInt();
                                employIDString = Integer.toString(employID);
                             }
                           goodEmploy = true;
                        } catch (InputMismatchException exception) {
                            System.out.println("Error! Please punch in an 8-digit number! Try again: ");
                          }
                        inputID.nextLine();
                    } 
                    for (int j = 0; j < users.size(); j++){
                        User userObject2 = users.get(j);
                        if (userObject2.getID() == employID){
                            System.out.println("Employee Found! Added to Transactions!");
                        }     
                    }
                      System.out.println("Employee not found!");
                      System.out.println("Press enter to continue");
                        try {
                              System.in.read();
                              return;
                        } catch (IOException ex) {
                   
                          }
                   }
                    System.out.println("Package not found!");
                    System.out.println("Press enter to continue");
                        try {
                              System.in.read();
                              return;
                        } catch (IOException ex) {
                   
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
    
    /**
     * searchPackAL searches through packages ArrayList to see if that tracking 
     * number package exists
     * @param trackNum tracking number entered by user
     * @return index if package exists
     */
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
    
    /**
     * searchPack is the public function that does the prompting if it does/doesn't exist
     * @param trackNum tracking number of package
     */
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
     * deletePack deleted
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
    
    /**
     * inputMSG is specific to the type of package type trying to be entered
     * @param type package type
     */
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
    
    /**
     * addUser adds a new user to the users ArrayList
     * @param userInput type of user
     */
    public void addUser(String userInput){
        int id;
        String fName, lName;
        Scanner input = new Scanner(System.in);
        //get random generated ID
        id = randUserId();
        System.out.println("Enter your first name: ");
        input = new Scanner(System.in);
        fName = input.nextLine();
        System.out.println("Enter your last name: ");
        input = new Scanner(System.in);
        lName = input.nextLine();

        switch(userInput.toLowerCase()){
            case "employee":
                 Scanner SSN = new Scanner(System.in);
                 int number = 0;//newSSN.nextInt();
                 String numberString = ""; //
                 boolean goodSSN = false;
                 while (goodSSN == false){
                   try{
                     System.out.println("Enter the SSN: ");
                     SSN = new Scanner(System.in);
                     number = SSN.nextInt();
                     numberString = Integer.toString(number);
                      while (numberString.length() != 9){
                        System.out.println("That number is not 9 digits, please try again: ");
                        SSN = new Scanner(System.in);
                        number = SSN.nextInt();
                        numberString = Integer.toString(number);
                      }
                      goodSSN = true;         
                   } catch (InputMismatchException exception){
                       System.out.println("Only numbers please, try again: ");
                     }
                    SSN.nextLine();
                 }
                float salary = 0; 
                boolean goodSalary = false;
                while (goodSalary == false){
                  try{
                    System.out.println("Please enter a monthly salary: "); 
                    SSN = new Scanner(System.in);
                    salary = SSN.nextFloat();
                                   
                    goodSalary = true;
                  }
                  catch(InputMismatchException exception){
                     System.out.println("Only numbers please, try again: ");
                  }
                 SSN.nextLine();
                } 
                int account = 0;
                String accountString = "";
                boolean goodBank = false;
                            
                while (goodBank == false){
                 try{
                   System.out.println("Please enter in a replacement 6-digit Bank Account Number: ");
                   SSN = new Scanner(System.in);
                   account = SSN.nextInt();
                   accountString = Integer.toString(account);
                   while (accountString.length() != 6){
                      System.out.println("Incorrect number of digits, try again: ");
                      SSN = new Scanner(System.in);
                      account = SSN.nextInt();
                      accountString = Integer.toString(account);
                   }
                   goodBank = true;
                 }
                 catch (InputMismatchException exception){
                    System.out.println("Numbers only please, try again: ");
                 }
                  SSN.nextLine();
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
    
    /**
     * searchUserAL searches through users ArrayList to find a user
     * @param ID ID of user
     * @return index if user exists
     */
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
    
    /**
     * changeUser allows changes to any of users fields
     * @param ID ID of user
     */
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
                            
                            Scanner newSSN = new Scanner(System.in);
                            int userNewSSN = 0;//newSSN.nextInt();
                            String newSSNString = ""; //
                            boolean goodnewSSN = false;
                            while (goodnewSSN == false){
                               try{
                                System.out.println("Enter the replacement SSN: ");
                                newSSN = new Scanner(System.in);
                                userNewSSN = newSSN.nextInt();
                                newSSNString = Integer.toString(userNewSSN);
                                while (newSSNString.length() != 9){
                                   System.out.println("That number is not 9 digits, please try again: ");
                                   newSSN = new Scanner(System.in);
                                   userNewSSN = newSSN.nextInt();
                                   newSSNString = Integer.toString(userNewSSN);
                                }
                                goodnewSSN = true;
                              
                               }
                               catch (InputMismatchException exception){
                                  System.out.println("Only numbers please, try again: ");
                               }
                               newSSN.nextLine();
                            }
                            users.get(index).setSSN(userNewSSN);//get index
                            break;
                            
                        case 4:
                            
                            Scanner newSal = new Scanner(System.in);
                            float userNewSalary = 0; 
                            boolean goodNewSalary = false;
                            while (goodNewSalary == false){
                                try{
                                   System.out.println("Please enter a monthly salary: "); 
                                   newSal = new Scanner(System.in);
                                   userNewSalary = newSal.nextFloat();
                                   
                                   goodNewSalary = true;
                                }
                                catch(InputMismatchException exception){
                                   System.out.println("Only numbers please, try again: ");
                                }
                                newSal.nextLine();
                            }
                            
                            users.get(index).setSalary(userNewSalary);
                            break;
                            
                        case 5:
                            
                            Scanner newBank = new Scanner(System.in);
                            int newUserBank = 0;
                            String bankString = "";
                            boolean goodUserBank = false;
                            
                            while (goodUserBank == false){
                                try{
                                    System.out.println("Please enter in a replacement 6-digit Bank Account Number: ");
                                    newBank = new Scanner(System.in);
                                    newUserBank = newBank.nextInt();
                                    bankString = Integer.toString(newUserBank);
                                    while (bankString.length() != 6){
                                       System.out.println("Incorrect number of digits, try again: "); 
                                       newBank = new Scanner(System.in);
                                       newUserBank = newBank.nextInt();
                                       bankString = Integer.toString(newUserBank);
                                    }
                                    goodUserBank = true;
                                }
                                catch (InputMismatchException exception){
                                    System.out.println("Numbers only please, try again: ");
                                }
                               newBank.nextLine();
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
    /**
     * randUserId generates a random ID
     * @return random ID 
     */
    public int randUserId(){
        int max = 99999999;
        int min = 10000000;
        int id; 
        Random rand = new Random();
        id = rand.nextInt(max - min + 1) + min;

        return id;
    }
    
    /**
     * write data from ArrayList packages, users, and trans into certain .ser files
     */
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
