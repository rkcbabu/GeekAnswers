/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import entities.QuestionVote;
import entities.User;
import entities.interaction.UserInteraction;
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
public class UserInteractionFacade extends AbstractFacade<UserInteraction> {
    @PersistenceContext(unitName = "GeekAnswersPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserInteractionFacade() {
        super(UserInteraction.class);
    }
    
         public int countTotalInteractionPointOfUser(User u) {
       
        TypedQuery<UserInteraction> query = em.createNamedQuery("user.interaction.total.point", UserInteraction.class);
        query.setParameter("user", u);
      // vote_up_query.

        try {
          List list=query.getResultList();
          if(list.isEmpty()) return 0;
           return Integer.parseInt(list.get(0).toString());
          
            
        } catch (Exception e) {
            return 0;
        }

    }
    
}
