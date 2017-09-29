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
class Crate extends Package {
    private final float mLoad;
    private final String content;
    public Crate(String tn, String s, String mc, float m, String c){
        super(tn, s, mc);
        this.mLoad = m;
        this.content = c; 
    }
    
    public float getML(){
        return this.mLoad;
    }
    
    public String getC(){
        return this.content;
    }
}