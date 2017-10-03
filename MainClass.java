/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shippingstore;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;



/**
 * Main class, main loop for shipping store database
 */
public class MainClass {
    
    /**
     * Contains main loop to call functions for shipping store menu
     * @param args the command line arguments
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
       // System.err.println("ss");
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
                + "\t7. Update user info (given their id).\n"
                + "\t8. Complete a shipping transaction.\n"
                + "\t9. Show completed shipping transactions.\n"
                + "\t10.Exit program.";
        System.out.println(welcomeMessage);
        int selection = in.nextInt();

        while (selection != 10) {

            System.out.println(welcomeMessage);

            switch (selection) {
                case 1:
                    shipStore.showPackageOrders();
                    break;
                case 2:{
                    System.out.println("\nEnter the type of package (Envelope, "
                        + "Box, Crate, or Drum): ");
                    String inTemp = in.nextLine();
                    
                    while(!("envelope".equals(inTemp.toLowerCase()) 
                            ||"box".equals(inTemp.toLowerCase()) 
                            ||"crate".equals(inTemp.toLowerCase()) 
                            ||"drum".equals(inTemp.toLowerCase()))){
                        System.out.println("Enter correct package type."); 
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
                    shipStore.showUsers();
                    break;
                case 6:
                    System.out.println("Enter the type of user you wish to add (customer or employee): ");
                    in = new Scanner(System.in);
                    String userInput = in.nextLine();
                    
                    while(!("employee".equals(userInput.toLowerCase()) 
                            ||"customer".equals(userInput.toLowerCase()))){
                        System.out.println("Enter correct user type."); 
                        in = new Scanner(System.in);
                        userInput = in.nextLine();
                        
                    }
                    shipStore.addUser(userInput);
                    break;
                case 7:
                    System.out.println("\nEnter the user ID: ");
                    int inTemp = in.nextInt();
                    String stringTemp = Integer.toString(inTemp);
                    while (stringTemp.length() != 8){
                        System.out.println("That is not 8 digits, try again: ");
                        in = new Scanner(System.in);
                        inTemp = in.nextInt();
                        stringTemp = Integer.toString(inTemp);
                    }
                    shipStore.changeUser(inTemp);
                    break;
                case 8:
                    shipStore.completeTransaction();
                    break;
                case 9:
                    shipStore.showTransactions();
                    break;
                case 11:
                    System.out.println(welcomeMessage);
                    break;
                default:
                    System.out.println("That is not a recognized command. Please enter another command or 11 to list the commands.");
                    break;
            }

            System.out.println("Please enter another command or '11' to list the commands.\n");
            selection = in.nextInt();

            in.nextLine();
        }

        in.close();
        shipStore.flush();
        
        System.out.println("\nDone!");
    }
    
}
