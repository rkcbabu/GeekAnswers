/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import javax.ejb.Stateless;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author acer
 */
@Named
@Stateless
@RequestScoped
public class LoginBean {
    
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
            
    
    
}
