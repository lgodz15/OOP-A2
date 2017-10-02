package shippingstore;

import java.util.ArrayList;

abstract class User {
    private final int id; //GENERATE UNIQUE NUM FOR EACH USER
    private String fName;
    private String lName;
    private int ssn;
    private String pNum;
        private ArrayList<User> userArray;

    
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
    
    public void setfName(String t){
        this.fName = t;
    }
    
    public void setlName(String t){
        this.lName = t;
    }
    
    public void setSSN(int ssn){
            this.ssn=ssn;
    }
    
    public int getSSN(){
          return this.ssn;
    }
    
    public String getPN(){
        return this.pNum;
    }
}
