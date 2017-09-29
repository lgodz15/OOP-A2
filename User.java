/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shippingstore;

/**
 *
 * @author lauragodinez
 */
abstract class User {
    private final int id; //GENERATE UNIQUE NUM FOR EACH USER
    private final String fName;
    private final String lName;
    
    public User(int id, String fName, String lName){
        this.id = id;
        this.fName = fName;
        this.lName = lName;
    }
    
    public int getID(){
        return this.id;
    }
    
    public String getFN(){
        return this.fName;
    }
    
    public String getLN(){
        return this.lName;
    }
}
