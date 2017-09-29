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
class Box extends Package {

    private final int largeDim;
    private final int vol;
    public Box(String tn, String s, String mc, int l, int v){
        super(tn, s, mc);
        this.largeDim = l;
        this.vol = v; 
    }
    
    public int getLD(){
        return this.largeDim;
    }
    
    public int getV(){
        return this.vol;
    }
}
