/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shippingstore;
import java.util.Scanner;

/**
 * Main class 
 * @author lauragodinez
 */
public class ShippingStore {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Scanner in = new Scanner(System.in);

        //ShippingStore shippingstore = new ShippingStore();

        String welcomeMessage = "\nWelcome to the Shipping Store database. Choose one of the following functions:\n\n"
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

        System.out.println(welcomeMessage);

        int selection = in.nextInt();
        //in.nextLine();

        while (selection != 10) {

            switch (selection) {
                case 1:
                    //shippingstore.showPackageOrders();
                    break;
                case 2:
//                    System.out.println("\nPlease type description of package with the following pattern:\n"
//                            + "\n TRACKING #  TYPE   SPECIFICATION   CLASS   WEIGHT   VOLUME\n"
//                            + "example:\nGFR23 Box Books Retail 9500.00 45\n");
//                    String inTemp = in.nextLine();
//
//                    String temp[] = inTemp.split(" ");
//
//                    if (temp.length != 6) {
//                        System.out.println("Not correct number of fields to process.");
//                        break;
//                    }

//                    shippingstore.addOrder(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5]);
                    break;
                case 3:
//                    shippingstore.showPackageOrders();
//
//                    System.out.println("\nPlease enter the tracking # of the package order to delete from the database.\n");
//                    String orderToDelete = in.nextLine();
//                    shippingstore.removeOrder(orderToDelete);
                    break;
                case 4:
//                    System.out.println("\nEnter the Tracking # of the order you wish to see.\n");
//                    String trackingNum = in.next();
//                    in.nextLine();
//                    shippingstore.searchPackageOrder(trackingNum);
                    break;
                case 5:
//                    float high = 0;
//                    float low = 0;
//                    
//                    System.out.println("\nEnter lower-bound weight.\n");
//                    low = in.nextFloat();
//                    System.out.println("\nEnter upper-bound weight.\n");
//                    high = in.nextFloat();
//                    in.nextLine();
//                    
//                    shippingstore.showPackageOrdersRange(low, high);
                    break;
                case 6:
                    break;
                case 7:
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
