/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import poliographing.Entry;

/**
 *
 * @author csstudent
 */
public class DataSet {
    private Entry[] fact;
    public DataSet() {}
    
    public Entry[] getEntries(){
        return fact;
    }
    
    @Override
    public String toString() {
        String ret = "";
        for(Entry e : fact){
            ret += e.toString() + "\n";
        }
        
        return ret;
    }
}
