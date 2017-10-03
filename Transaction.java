/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shippingstore;
import java.util.Date;
import java.io.Serializable;

/**
 * Transaction class, used for any completed transactions
 */
public class Transaction implements Serializable{

    private final int cID;
    private final String trackNum;
    private final String shipD;
    private final float cost;
    private final int eID;
    Date now = new Date();

    /**
     * Constructor assigns values given (customer IF, tracking number, shipping date,
     * delivery date, cost, and employee ID)
     * @param cID is the customer's id (owner of package)
     * @param trackNum is the tracking number of package entered
     * @param shipD is the date package is shipped
     * @param cost is the cost of the shipment
     * @param eID is the employee's ID who completed the transaction
     */
    public Transaction(int cID, String trackNum, String shipD, float cost, int eID){
        this.cID = cID;
        this.trackNum = trackNum;
        this.shipD = shipD;
        this.cost = cost;
        this.eID = eID;
    }
    
    /**
     * Getter function for package's delivery date
     */
    public String getDelivD(){
        return now.toString();
    }
    
    /**
     * Getter function for customer ID
     */
    public int getCID(){
        return this.cID;
    }
    
    /**
     * Getter function for package's tracking number
     */
    public String getTN(){
        return this.trackNum;
    }
    
    /**
     * Getter function for package's shipping date
     */
    public String getShipD(){
        return this.shipD;
    }
    
    /**
     * Getter function for package's cost
     */
    public float getCost(){
        return this.cost;
    }
    
    /**
     * Getter function for package's employee ID
     */
    public int getEID(){
        return this.eID;
    }
}
