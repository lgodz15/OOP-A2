/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shippingstore;

/**
 * Box class which extends the Package class
 */
class Box extends Package {

    private final int largeDim;
    private final int vol;
    
    /**
    * Constructor takes in the box package tracking number, specification,
    * mailing class, largest dimension, and volume
    * @param tn tracking number of package
    * @param s specification of package
    * @param mc mailing class of package
    * @param l largest dimension of package
    * @param v volume of package
    */
    public Box(String tn, String s, String mc, int l, int v){
        super(tn, s, mc);
        this.largeDim = l;
        this.vol = v; 
    }
    
    /**
    * Getter function for Box's largest dimension
    */
    @Override
    public int getLD(){
        return this.largeDim;
    }
    
    /**
    * Getter function for Box's volume
    */
    @Override
    public int getV(){
        return this.vol;
    }
}
