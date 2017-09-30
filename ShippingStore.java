/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shippingstore;
import java.util.ArrayList;

/**
 * Will contain all function calls and checks for user input
 * @author lauragodinez
 */
public class ShippingStore {
    private ArrayList<Package> packages = new ArrayList<>();
    
    public ShippingStore(){
        //checks for input data coming in from serializable objects?
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
                    //orders.get(i).getType(),
                    orders.get(i).getSpec(),
                    orders.get(i).getMC()
                    //String.format("%.2f", orders.get(i).getWeight()),
                    //Integer.toString(orders.get(i).getVolume())
                    //the rest will be added according to the TYPE of package
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
            //order.add(getPackageOrder(index));
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
