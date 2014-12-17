/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.interaction;

import entities.User;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author acer
 */
@NamedQueries({
    @NamedQuery(name = "user.interaction.total.point", query = "SELECT SUM(e.point) FROM UserInteraction e WHERE e.user=:user")}
    
)


@Entity
@Inheritance(strategy =InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "eventType",discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("default")
public class UserInteraction implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    
    @Transient()
    protected boolean triggerExpertise=false;
    
    @ManyToOne
    private User user;   

    public boolean isTriggerExpertise() {
        return triggerExpertise;
    }

    

    public UserInteraction(){
        
    }
      public UserInteraction(int point){
        this.point=point;
    }
       public UserInteraction(int point, boolean triggerExpertise){
        this.point=point;
        this.triggerExpertise=triggerExpertise;
    }
      
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Date getInteractionDate() {
        return interactionDate;
    }

    public void setInteractionDate(Date interactionDate) {
        this.interactionDate = interactionDate;
    }
    
    private int point;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date interactionDate;
   
    
  

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
        if (!(object instanceof UserInteraction)) {
            return false;
        }
        UserInteraction other = (UserInteraction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.UserInteraction[ id=" + id + " ]";
    }
    
}
