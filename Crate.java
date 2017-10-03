/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shippingstore;

/**
 * Crate class extends the Package class
 */
class Crate extends Package {
    private final float mLoad;
    private final String content;
    
    /**
    * Constructor takes in the box package tracking number, specification,
    * mailing class, largest dimension, and volume
    * @param tn tracking number of package
    * @param s specification of package
    * @param mc mailing class of package
    * @param m maximum load weight of package
    * @param c content of package
    */
    public Crate(String tn, String s, String mc, float m, String c){
        super(tn, s, mc);
        this.mLoad = m;
        this.content = c; 
    }
    
    /**
    * Getter function for Crate package's maximum load weight 
    */
    @Override
    public float getML(){
        return this.mLoad;
    }
    
    /**
    * Getter function for Crate package's content
    */
    @Override
    public String getC(){
        return this.content;
    }
}