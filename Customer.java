/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shippingstore;

/**
 * Customer class which extends User class
 */
class Customer extends User{
    private String pNum;
    private String addr;
    
    /**
    * Constructor takes in the employee user's ID, first name, last name,
    * social security number, monthly salary, and direct deposit account
    * @param id ID assigned to that customer
    * @param fn first name of customer
    * @param ln last name of customer
    * @param p customer's phone number
    * @param a customer's address
    */
    Customer(int id, String fn, String ln, String p, String a){
        super(id, fn, ln);
        this.pNum = p;
        this.addr = a;
    }
    
    /**
    * Getter function for customer's phone number
    */
    @Override
    public String getPN(){
        return this.pNum;
    }
    
    /**
    * Setter function for customer's phone number
    */
    @Override
    public void setPhone(String pNum){
            this.pNum=pNum;
    }
    
    /**
    * Setter function for customer's address 
    */
    @Override
    public void setAddress(String addr){
            this.addr=addr;
    }
    
    @Override
    public String getAddress(){
          return this.addr;
    }
    
    
}
