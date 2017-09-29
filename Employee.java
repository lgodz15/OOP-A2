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
    private final int ssn;
    private final float monSal;
    private final int dDep;
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
}
