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
class Customer extends User{
    private String pNum;
    private String addr;
    Customer(int id, String fn, String ln, String p, String a){
        super(id, fn, ln);
        this.pNum = p;
        this.addr = a;
    }
    
    public String getPN(){
        return this.pNum;
    }
    
    public String getAD(){
        return this.addr;
    }
}
