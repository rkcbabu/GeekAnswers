/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author acer
 */
public class ListFilter<T,R> {
    
    
    public List<T> execute(List<T> list,R elm, Predicate pred){
      //  System.out.println(elm);
        System.out.println(list.size());
        List<T> templist=new ArrayList();
        
        
       for(T item:list){
          
          if(pred.execute(item,elm))
              templist.add(item);
        }
        
        
        return templist;
    }
    
    
}
