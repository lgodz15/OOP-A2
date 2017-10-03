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
    
    public void setSalary(float monSal){
            this.monSal=monSal;
    }
    
    public void setBank(int dDep){
            this.dDep=dDep;
    }
    
    public void setPhone(String pNum){
            this.pNum=pNum;
    }
    
    public void setAddress(String addr){
            this.addr=addr;
    }
    
    public int getSSN(){
          return this.ssn;
    }
    
    public String getPN(){
        return this.pNum;
    }
    
    public float getSalary(){
        return this.monSal;
    }
    
    public int getBank(){
         return this.dDep;
    }
    
    public String getAddress(){
          return this.addr;
    }
}
