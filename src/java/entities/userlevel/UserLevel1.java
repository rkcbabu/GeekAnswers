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
@DiscriminatorValue("1")
public class UserLevel1 extends UserLevel implements Serializable {

    public UserLevel1(){
         //super("Beginner plus", 51, 100, new UserLevel0(), new UserLevel2());
this.setTitle("Beginner plus");
this.setMinPoint(0);
this.setMaxPoint(100);

    }


    @Override
    public String toString() {
        return "entities.userlevel.UserLevel1[ id=" + id + " ]";
    }
    
}
