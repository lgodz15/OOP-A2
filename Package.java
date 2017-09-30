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
abstract class Package {
    private final String trackNum;
    private final String spec;
    private final String mailClass;
    
    public Package(String trackNum, String spec, String mailClass){
        this.trackNum = trackNum;
        this.spec = spec;
        this.mailClass = mailClass;
    }
    
    public String getTN(){
        return this.trackNum;
    }
    
    public String getSpec(){
        return this.spec;
    }
    
    public String getMC(){
        return this.mailClass;
    }
}
