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
class Drum extends Package {
    private final String material;
    private final float diam;
    public Drum(String tn, String s, String mc, String m, float d){
        super(tn, s, mc);
        this.material = m;
        this.diam = d; 
    }
    
    public String getM(){
        return this.material;
    }
    
    public float getD(){
        return this.diam;
    }
}
