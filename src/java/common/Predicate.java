/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

/**
 *
 * @author acer
 */
public abstract class Predicate<T,R> {
    
    public abstract boolean execute(T elm1, R elm2);
     public  boolean execute(T elm1){
         return false;
     }
    
}
