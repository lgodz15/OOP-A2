/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shippingstore;

/**
 * Envelope class extends Package class
 */
class Envelope extends Package {
    private final int height;
    private final int width;
    
    /**
    * Constructor takes in the box package tracking number, specification,
    * mailing class, largest dimension, and volume
    * @param tn tracking number of package
    * @param s specification of package
    * @param mc mailing class of package
    * @param h height of package
    * @param w width of package
    */
    public Envelope(String tn, String s, String mc, int h, int w){
        super(tn, s, mc);
        this.height = h;
        this.width = w; 
    }
    
    /**
    * Getter function for Envelope package's height 
    */
    public int getH(){
        return this.height;
    }
    
    /**
    * Getter function for Envelope package's width 
    */
    public int getW(){
        return this.width;
    }
}
