/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.util.Map;
import javax.faces.context.FacesContext;


/**
 *
 * @author acer
 */


public  class Common {

    public Common(){
        
    }
    public String getRequestValue(String key){
        
        return(String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
      
    }
    public Object getSession(String key){
         return (Object) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(key);
      
     
    }
    
}
