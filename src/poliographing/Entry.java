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
public class Entry implements Comparable<Entry>{
    private Dim dim;
    private int Value;
    public Entry(){}
    
    public int getValue(){
        return Value;
    }
    
    public Dim getDim(){
        return dim;
    }
    
    public String toString(){
        return "" + dim.getLabel() + ": " + Value + "% immunized";
    }
    
    @Override
    public int compareTo(Entry e){
        if(this.dim.getLabel() == null){
            return -1;
        }
        
        if(e.dim.getLabel() == null){
            return 1;
        }
        return dim.getLabel().compareTo(e.dim.getLabel());
    }
    
}
