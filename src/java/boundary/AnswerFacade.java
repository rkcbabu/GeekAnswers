/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import entities.Answer;
import entities.AnswerVote;
import entities.Question;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Chaulagai
 */
@Stateless
public class AnswerFacade extends AbstractFacade<Answer> {

    @PersistenceContext(unitName = "GeekAnswersPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AnswerFacade() {
        super(Answer.class);
    }

    public List<Answer> getAll(Question q){
        TypedQuery<Answer> query = em.createNamedQuery("question.answer.list", Answer.class); 
        query.setParameter("question", q);
            return query.getResultList();
    }
  
   

}
