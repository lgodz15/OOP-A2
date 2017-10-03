/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shippingstore;
import java.util.Date;
import java.io.Serializable;
/**
 *
 * @author lauragodinez
 */
abstract class Package implements Serializable{
    private final String trackNum;
    private final String spec;
    private final String mailClass;
    private int largeDim;
    private int vol;
    private float mLoad;
    private String content;
    private String material;
    private float diam;
    private int height;
    private int width;
    private int date;        
    Date now = new Date();

    
    public Package(String trackNum, String spec, String mailClass){
        this.trackNum = trackNum;
        this.spec = spec;
        this.mailClass = mailClass;
    }
    
    public String getDate(){
        return now.toString();
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
    
    public int getLD(){
        return this.largeDim;
    }
    
    public int getV(){
        return this.vol;
    }
    
    public float getML(){
        return this.mLoad;
    }
    
    public String getC(){
        return this.content;
    }
    
    public String getM(){
        return this.material;
    }
    
    public float getD(){
        return this.diam;
    }
    
    public int getH(){
        return this.height;
    }
    
    public int getW(){
        return this.width;
    }
    
    //@Override
    public int compareTo(Package o) {
        return o.getTN().compareTo(this.getTN());
    }
}
