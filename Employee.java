/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shippingstore;

/**
 * Employee class that extends the User class
 */
class Employee extends User{
    private int ssn;
    private float monSal;
    private int dDep;
    
    /**
    * Constructor takes in the employee user's ID, first name, last name,
    * social security number, monthly salary, and direct deposit account
    * @param id ID assigned to that employee
    * @param fn first name of employee
    * @param ln last name of employee
    * @param ssn social security number of employee 
    * @param m monthly salary of employee
    * @param d direct deposit account of employee
    */
    Employee(int id, String fn, String ln, int ssn, float m, int d){
        super(id, fn, ln);
        this.ssn = ssn;
        this.monSal = m;
        this.dDep = d;
    }
    
    /**
    * Getter function for employee's Social security number 
    */
    @Override
    public int getSSN(){
        return this.ssn;
    }
    
    /**
    * Getter function for employee's monthly salary 
    */
    public float getMS(){
        return this.monSal;
    }
    
    /**
    * Getter function for employee's salary
    */
    @Override
    public float getSalary(){
        return this.monSal;
    }
    
    /**
    * Setter function for employee's Social security number 
    */
    @Override
    public void setSSN(int t){
        this.ssn = t;
    }
    
    /**
    * Setter function for employee's monthly salary 
    */
    @Override
    public void setSalary(float monSal){
            this.monSal=monSal;
    }
    
    /**
    * Getter function for employee's direct deposit account
    */
    @Override
    public void setBank(int dDep){
            this.dDep=dDep;
    }
    
    /**
    * Setter function for employee's direct deposit account
    */
    @Override
    public int getBank(){
         return this.dDep;
    }
}
