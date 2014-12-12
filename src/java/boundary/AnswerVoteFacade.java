/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import entities.Answer;
import entities.AnswerVote;
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
}
