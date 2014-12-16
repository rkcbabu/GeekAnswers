/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import entities.AnswerVote;
import entities.Question;
import entities.QuestionVote;
import entities.User;
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
public class QuestionVoteFacade extends AbstractFacade<QuestionVote> {

    @PersistenceContext(unitName = "GeekAnswersPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public QuestionVoteFacade() {
        super(QuestionVote.class);
    }

    
    
         public Long getQuestionVoteId(User u,Question q) {
        TypedQuery<QuestionVote> query = em.createNamedQuery("question.vote.find.id", QuestionVote.class);

        query.setParameter("question", q);
        query.setParameter("user", u);

        try {
             Object ob=query.getResultList().get(0);
             
           return Long.parseLong(ob.toString());
        } catch (Exception e) {
            return 0L;
        }

    }
    
    public int countQuestionVote(Question q) {
       
        TypedQuery<QuestionVote> vote_up_query = em.createNamedQuery("question.vote.count", QuestionVote.class);
        vote_up_query.setParameter("question", q);
      // vote_up_query.

        try {
          List list=vote_up_query.getResultList();
           return Integer.parseInt(list.get(0).toString());
          
            
        } catch (Exception e) {
            return 0;
        }

    }
    
    
     public int countTotalQuestionVotesOfUser(User u) {
       
        TypedQuery<QuestionVote> query = em.createNamedQuery("question.vote.count.user", QuestionVote.class);
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
