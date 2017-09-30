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
    private final Date shipD;
    private final Date delivD;
    private final float cost;
    private final int eID;
    
    public Transaction(int cID, String trackNum, Date shipD, Date delivD, float cost, int eID){
        this.cID = cID;
        this.trackNum = trackNum;
        this.shipD = shipD;
        this.delivD = delivD;
        this.cost = cost;
        this.eID = eID;
    }
    
    public int getCID(){
        return this.cID;
    }
    
    public String getTN(){
        return this.trackNum;
    }
    
    public Date getShipD(){
        return this.shipD;
    }
    
    public Date getDelivD(){
        return this.delivD;
    }
    
    public float getCost(){
        return this.cost;
    }
    
    public int getEID(){
        return this.eID;
    }
}
