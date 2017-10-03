/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shippingstore;
import java.util.Date;

/**
 *
 * @author lauragodinez
 */
public class Transaction {

    private final int cID;
    private final String trackNum;
    private final String shipD;
    private final float cost;
    private final int eID;
    Date now = new Date();

    
    public Transaction(int cID, String trackNum, String shipD, float cost, int eID){
        this.cID = cID;
        this.trackNum = trackNum;
        this.shipD = shipD;
        this.cost = cost;
        this.eID = eID;
    }
    
    public String getDelivD(){
        return now.toString();
    }
    
    public int getCID(){
        return this.cID;
    }
    
    public String getTN(){
        return this.trackNum;
    }
    
    public String getShipD(){
        return this.shipD;
    }
    
    
    public float getCost(){
        return this.cost;
    }
    
    public int getEID(){
        return this.eID;
    }
}
