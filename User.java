package shippingstore;

import java.util.ArrayList;
import java.io.Serializable;

abstract class User implements Serializable{
    private final int id; //GENERATE UNIQUE NUM FOR EACH USER
    private String fName;
    private String lName;
    private int ssn;
    private String pNum;
    private String addr;
    private float monSal;
    private int dDep;
        private ArrayList<User> userArray;

    /**
    * Constructor takes in the user's id, first name and last name
    * @param if id generated for user
    * @param fName first name of user
    * @param lName last name of user
    */
    public User(int id, String fName, String lName){
        this.id = id;
        this.fName = fName;
        this.lName = lName;
    }
    
    /**
    * Getter function for user's ID
    */
    public int getID(){
        return this.id;
    }
    
    /**
    * Getter function for user's first name
    */
    public String getFN(){
        return this.fName;
    }
    
    /**
    * Getter function for user's last name
    */
    public String getLN(){
        return this.lName;
    }
    
    /**
    * Setter function for user's first name
    */
    public void setfName(String t){
        this.fName = t;
    }
    
    /**
    * Setter function for user's last name
    */
    public void setlName(String t){
        this.lName = t;
    }
    
    /**
    * Setter function for user's first name
    */
    public void setSSN(int ssn){
            this.ssn=ssn;
    }
    
    /**
    * Setter function for employee's salary (overwritten)
    */
    public void setSalary(float monSal){
            this.monSal=monSal;
    }
    
    /**
    * Setter function for employee's direct deposit (overwritten)
    */
    public void setBank(int dDep){
            this.dDep=dDep;
    }
    
    /**
    * Setter function for customer's number (overwritten)
    */
    public void setPhone(String pNum){
            this.pNum=pNum;
    }
    
    /**
    * Setter function for customer's address (overwritten)
    */
    public void setAddress(String addr){
            this.addr=addr;
    }
    
    /**
    * Getter function for employee's Social security number (overwritten)
    */
    public int getSSN(){
          return this.ssn;
    }
    
    /**
    * Getter function for customer's phone number (overwritten)
    */
    public String getPN(){
        return this.pNum;
    }
    
    /**
    * Getter function for employee's salary (overwritten)
    */
    public float getSalary(){
        return this.monSal;
    }
    
    /**
    * Getter function for employee's direct deposit (overwritten)
    */
    public int getBank(){
         return this.dDep;
    }
    
    /**
    * Getter function for customer's address (overwritten)
    */
    public String getAddress(){
          return this.addr;
    }
}
