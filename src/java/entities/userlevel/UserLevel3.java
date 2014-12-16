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
@DiscriminatorValue("3")
public class UserLevel3 extends UserLevel implements Serializable {
    public UserLevel3(){
     //super("Mid Plus", 151, 200, new UserLevel2(), new UserLevel4());
      this.setTitle("Mid Plus");
       this.setMinPoint(151);
       this.setMaxPoint(200);
//      
//       this.setNextLevel(new UserLevel4());

    }

    
    @Override
    public String toString() {
        return "entities.userlevel.UserLevel3[ id=" + id + " ]";
    }
    
}
