/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import entities.Question;
import entities.QuestionVote;
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

    public int countQuestionVote(Question q) {
        TypedQuery<QuestionVote> query = em.createNamedQuery("question.vote.count", QuestionVote.class);

        query.setParameter("question", q);

        try {
            return query.getResultList().size();
        } catch (Exception e) {
            return 0;
        }

    }
}
