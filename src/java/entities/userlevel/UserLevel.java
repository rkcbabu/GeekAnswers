/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.userlevel;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 *
 * @author acer
 */

@NamedQueries({
        @NamedQuery(name = "userlevel.initial.level",query = "SELECT u from UserLevel u WHERE u.minPoint=0")
})

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "userlevel",discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue("0")
public class UserLevel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    
     private String title;
    
     private int minPoint;
     private int maxPoint;
     
     
     
     
    
     
  
     public UserLevel(){
         
     }
     public UserLevel(String title ){
         
     }
     
    
     
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMinPoint() {
        return minPoint;
    }

    public void setMinPoint(int minPoint) {
        this.minPoint = minPoint;
    }

    public int getMaxPoint() {
        return maxPoint;
    }

    public void setMaxPoint(int maxPoint) {
        this.maxPoint = maxPoint;
    }

 
 
    
  

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
          UserLevel other = (UserLevel) object;
         if( this.title!=null && other.title!=null && this.title.equals(other.title)) return true;
        if (!(object instanceof UserLevel)) {
            return false;
        }
      
        
        if( this.title!=null && other.title!=null && this.title.equals(other.title)) return true;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.userlevel.UserLevel[ id=" + id + " ]";
    }
    
}
