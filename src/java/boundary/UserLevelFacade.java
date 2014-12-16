/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import entities.interaction.UserInteraction;
import entities.userlevel.UserLevel;
import entities.userlevel.UserLevel1;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author acer
 */
@Stateless
public class UserLevelFacade extends AbstractFacade<UserLevel> {
    @PersistenceContext(unitName = "GeekAnswersPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserLevelFacade() {
        super(UserLevel.class);
    }
    
    
    public UserLevel getInitialUserLevel(){
               
        TypedQuery<UserLevel> query = em.createNamedQuery("userlevel.initial.level", UserLevel.class);
        query.setMaxResults(1);
      
      // vote_up_query.

        try {
          return query.getSingleResult();
          
          
            
        } catch (Exception e) {
           
            UserLevel u=new UserLevel1();
            this.create(u);
            return u;
            
        }

        
    }
    
    
}
