package poliographing;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author csstudent
 */
public class Dim {
    private String PUBLISHSTATE;
    private String REGION;
    private String GHO;
    private String WORLDBANKINCOMEGROUP;
    private int YEAR;
    private String COUNTRY;
    
    public Dim(){
        
    }
    
    public String getPublishState(){
        return PUBLISHSTATE;
    }
    
    public String getRegion(){
        return REGION;
    }
    
    public String getGHO(){
        return GHO;
    }
    
    public String getIncomeGroup(){
        return WORLDBANKINCOMEGROUP;
    }
    
    public String getCountry(){
        return COUNTRY;
    }
    
    public int getYear(){
        return YEAR;
    }
    
    public String getLabel(){
        String label = null;
        if (COUNTRY != null) {
            label = COUNTRY;
        }
        else if (REGION != null) {
            label = REGION;
        }
        else {
            label = WORLDBANKINCOMEGROUP;
        }
        
        return label;
    }
    
    @Override
    public String toString(){
        return "" + this.getLabel();
    }
}
