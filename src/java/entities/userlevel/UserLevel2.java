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
@DiscriminatorValue("2")
public class UserLevel2 extends UserLevel implements Serializable {
   
    public UserLevel2(){
     //super("Mid level", 101, 150, new UserLevel1(), new UserLevel3());
      this.setTitle("Mid level");
       this.setMinPoint(51);
       this.setMaxPoint(150);
      

    }
    
   

    @Override
    public String toString() {
        return "entities.userlevel.UserLevel2[ id=" + id + " ]";
    }
    
}
