/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.util.Iterator;
import java.util.List;

/**
 *
 * @author acer
 */
public class ListFilter<T,R> {
    
    
    public List<T> getFilteredList(List<T> list,R elm, Predicate pred){
        
        List<T> templist=list;
        
        Iterator it=templist.iterator();
        
        while(it.hasNext()){
            
           if(pred.execute(it.hasNext(),elm))
               it.remove();
        }
        
        
        return templist;
    }
    
    
}
