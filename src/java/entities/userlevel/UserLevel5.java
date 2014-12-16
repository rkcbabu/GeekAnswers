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
@DiscriminatorValue("5")
public class UserLevel5 extends UserLevel implements Serializable {
   
    public UserLevel5(){
//             //super("Guru", 251, 2000000, new UserLevel4(), null);
//        
          this.setTitle("Guru");
          this.setMinPoint(251);
          this.setMaxPoint(100000000);
//      
//       this.setNextLevel(null);

    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserLevel5)) {
            return false;
        }
        UserLevel5 other = (UserLevel5) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.userlevel.UserLevel5[ id=" + id + " ]";
    }
    
}
