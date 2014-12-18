/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import entities.Answer;
import entities.AnswerVote;
import entities.Category;
import entities.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author acer
 */
@Stateless
public class AnswerVoteFacade extends AbstractFacade<AnswerVote> {
    @PersistenceContext(unitName = "GeekAnswersPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AnswerVoteFacade() {
        super(AnswerVote.class);
    }
    
    
     public Long getAnswerVoteId(User u,Answer a) {
        TypedQuery<AnswerVote> query = em.createNamedQuery("answer.vote.find.id", AnswerVote.class);

        query.setParameter("answer", a);
        query.setParameter("user", u);

        try {
             Object ob=query.getResultList().get(0);
             
           return Long.parseLong(ob.toString());
        } catch (Exception e) {
            return 0L;
        }

    }
    
    
      public int countAnswerVote(Answer a) {
        TypedQuery<AnswerVote> query = em.createNamedQuery("answer.vote.count", AnswerVote.class);

        query.setParameter("answer", a);

        try {
             List list=query.getResultList();
             
           return Integer.parseInt(list.get(0).toString());
        } catch (Exception e) {
            return 0;
        }

    }
      
      
            
       public int countAnswerVote(User u) {
       Query query = em.createNamedQuery("answer.vote.count.all.user");

        query.setParameter("user", u);
       

        try {
             List list=query.getResultList();
             
           return Integer.parseInt(list.get(0).toString());
        } catch (Exception e) {
            return 0;
        }

    }
      
       public int countAnswerVote(User u, Category c) {
       Query query = em.createNamedQuery("answer.category.vote.count");

        query.setParameter("user", u);
        query.setParameter("category", c);

        try {
             List list=query.getResultList();
             
           return Integer.parseInt(list.get(0).toString());
        } catch (Exception e) {
            return 0;
        }

    }
      
      
}
