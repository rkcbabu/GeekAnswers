/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.userlevel;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author acer
 */
@Entity
@DiscriminatorValue("4")
public class UserLevel4 extends UserLevel implements Serializable {
   
    public UserLevel4(){
//            // super("Senior", 201, 250, new UserLevel3(), new UserLevel5());
               this.setTitle("Senior");
        this.setMinPoint(201);
        this.setMaxPoint(250);
//      
//       this.setNextLevel(new UserLevel5());

    }

   
    @Override
    public String toString() {
        return "entities.userlevel.UserLevel4[ id=" + id + " ]";
    }
    
}
