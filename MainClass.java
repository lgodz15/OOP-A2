/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shippingstore;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;


/**
 * Main class 
 * @author lauragodinez
 */
public class MainClass {
    //private ArrayList<Package> store = new ArrayList<Package>();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        Scanner in = new Scanner(System.in);

        ShippingStore shipStore = new ShippingStore();
        System.out.println("Welcome to the Shipping Store!");

        String welcomeMessage = "Choose one of the following functions:\n\n"
                + "\t1. Show all existing package orders in the database (sorted by tracking number).\n"
                + "\t2. Add a new package order to the database.\n"
                + "\t3. Delete a package order from a database.\n"
                + "\t4. Search for a package order (given its Tracking #).\n"
                + "\t5. Show a list of users in the database.\n"
                + "\t6. Add new user to the database.\n"
                + "\t7. Update user info (given their id)."
                + "\t8. Complete a shipping transaction."
                + "\t9. Show completed shipping transactions."
                + "\t10.Exit program.";

        int selection = in.nextInt();
        //in.nextLine();

        while (selection != 10) {

            System.out.println(welcomeMessage);

            switch (selection) {
                case 1:
                    //shippingstore.showPackageOrders();
                    break;
                case 2:{
                    System.out.println("\nEnter the type of package (Envelope, "
                        + "Box, Crate, or Drum): ");
                    in.nextLine();
                    String inTemp = in.nextLine();
                    
                    while(!("envelope".equals(inTemp.toLowerCase()) 
                            ||"box".equals(inTemp.toLowerCase()) 
                            ||"crate".equals(inTemp.toLowerCase()) 
                            ||"drum".equals(inTemp.toLowerCase()))){
                        System.out.println("Enter correct package type."); 
                        in.nextLine();
                        inTemp = in.nextLine();
                        
                    }
                    shipStore.addPackage(inTemp);

                    break;
                }
                case 3:
                    shipStore.showPackageOrders();

                    System.out.println("\nPlease enter the tracking # of the package order to delete from the database.\n");
                    String orderToDelete = in.nextLine();
                    shipStore.deletePack(orderToDelete);
                    break;
                case 4:
                    System.out.println("\nEnter the Tracking # of the order you wish to see.\n");
                    String trackingNum = in.next();
                    in.nextLine();
                    shipStore.searchPack(trackingNum);
                    break;
                case 5:
                    
                    break;
                case 6:
                    System.out.println("Enter the type of user you wish to add (customer or employee): ");
                    //in = new Scanner(System.in);
                    String userInput = in.nextLine();
                    
                    while(!("employee".equals(userInput.toLowerCase()) 
                            ||"customer".equals(userInput.toLowerCase()))){
                        System.out.println("Enter correct user type."); 
                        in.nextLine();
                        userInput = in.nextLine();
                        
                    }
                    shipStore.addUser(userInput);
                    break;
                case 7:
                    System.out.println("\nEnter the type of user (Employee or Customer): ");
                    in.nextLine();
                    String inTemp = in.nextLine();
                    
                    if("employee".equals(inTemp.toLowerCase())){
                        //send input to employee class
                        break;
                    }
                    else if("customer".equals(inTemp.toLowerCase())){
                        //send input to customer class
                        break;
                    }
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 11:
                    System.out.println(welcomeMessage);
                    break;
                default:
                    System.out.println("That is not a recognized command. Please enter another command or 11 to list the commands.");
                    break;

            }

            System.out.println("Please enter another command or 'h' to list the commands.\n");
            selection = in.next().charAt(0);

            in.nextLine();
        }

        in.close();
        //shippingstore.flush();
        
        System.out.println("Done!");
    }
    
}
