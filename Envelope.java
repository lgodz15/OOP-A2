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
class Envelope extends Package {
    private final int height;
    private final int width;
    public Envelope(String tn, String s, String mc, int h, int w){
        super(tn, s, mc);
        this.height = h;
        this.width = w; 
    }
    
    public int getH(){
        return this.height;
    }
    
    public int getW(){
        return this.width;
    }
}
