/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shippingstore;

/**
 * Drum class extends Package class
 */
class Drum extends Package {
    private final String material;
    private final float diam;
    
    /**
    * Constructor takes in the box package tracking number, specification,
    * mailing class, largest dimension, and volume
    * @param tn tracking number of package
    * @param s specification of package
    * @param mc mailing class of package
    * @param m material of package
    * @param d diameter of package
    */
    public Drum(String tn, String s, String mc, String m, float d){
        super(tn, s, mc);
        this.material = m;
        this.diam = d; 
    }
    
    /**
    * Getter function for Drum package's material 
    */
    @Override
    public String getM(){
        return this.material;
    }
    
    /**
    * Getter function for Drum package's diameter 
    */
    @Override
    public float getD(){
        return this.diam;
    }
}
