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
class Employee extends User{
    private int ssn;
    private float monSal;
    private int dDep;
    Employee(int id, String fn, String ln, int ssn, float m, int d){
        super(id, fn, ln);
        this.ssn = ssn;
        this.monSal = m;
        this.dDep = d;
    }
    
    public int getSSN(){
        return this.ssn;
    }
    
    public float getMS(){
        return this.monSal;
    }
    
    public int getDD(){
        return this.dDep;
    }
    
    public float getSalary(){
        return this.monSal;
    }
    
    public void setSSN(int t){
        this.ssn = t;
    }
    
    public void setSalary(float monSal){
            this.monSal=monSal;
    }
    
    public void setBank(int dDep){
            this.dDep=dDep;
    }
    
    public int getBank(){
         return this.dDep;
    }
}
