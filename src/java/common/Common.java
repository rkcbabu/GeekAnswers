/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import entities.User;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author acer
 */

public class Common {

    public Common() {

    }
    
    

    public String getRequestValue(String key) {

        
        return (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);

    }

    public Object getSession(String key) {
        return (Object) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(key);

    }

    public int isUserLoggedIn() throws IOException {

        User u = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("logged_in_user");

        if (u != null) {
            return Integer.parseInt(u.getId().toString());
        } else {
            return 0;
        }
    }

    public void redirectLogin() {

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        try {
            context.redirect(context.getRequestContextPath() + "/faces/user/login.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(Common.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
        public void redirectHome() {

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        try {
            context.redirect(context.getRequestContextPath());
        } catch (IOException ex) {
            Logger.getLogger(Common.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
