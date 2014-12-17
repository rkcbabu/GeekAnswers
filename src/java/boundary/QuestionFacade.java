/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

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

public class QuestionFacade extends AbstractFacade<Question> {

    @PersistenceContext(unitName = "GeekAnswersPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public QuestionFacade() {
        super(Question.class);
    }

    public EntityManager getEM(){
        return em;
    }    
    public List<Question> search(String searchKey){
        TypedQuery<Question> query = em.createQuery("SELECT c FROM Question c WHERE c.title like :searchKey  OR c.Content LIKE :searchKey", Question.class); 
        query.setParameter("searchKey", "%"+searchKey+"%");
            return query.getResultList();
    }
    public List<Question> getQuestions(){    
        TypedQuery<Question> query = em.createQuery("SELECT q FROM Question q ORDER BY q.createdDate DESC",Question.class);
        return query.getResultList();
    }
    public List<Question> getPopular(){
        TypedQuery<Question> query = em.createQuery("SELECT q FROM Question q, QuestionVote v WHERE q = v.question ORDER BY v.vote DESC",Question.class);
        return query.getResultList();
    }
    public List<Question> getTopUnanswered(){
//        String q = "SELECT q FROM Question q, QuestionVote v ,Answer a "
//                +  "WHERE q NOT IN (a) "
//                +  "AND q = v.question "
//                +  "ORDER BY v.vote DESC "
//                + "";
        TypedQuery<Question> query = em.createQuery("SELECT q FROM Question q, QuestionVote v WHERE q NOT IN (SELECT a.question FROM Answer a) AND v.question = q ORDER by v.vote DESC ",Question.class);
        return query.setMaxResults(3).getResultList();
    }
//    @Override
//    public List<Question> findAll() {
//        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
//        cq.select(cq.from(entityClass));
//       // cq.where(getEntityManager().getCriteriaBuilder().equal(null, null));
//        
//        TypedQuery<Question> myquestions=getEntityManager().createNamedQuery("my.questions",Question.class);
//        
//        return getEntityManager().createQuery(cq).getResultList();
//    }
//
//    @Override
//    public List<Question> findRange(int[] range) {
//        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
//        cq.select(cq.from(entityClass));
//        javax.persistence.Query q = getEntityManager().createQuery(cq);
//        q.setMaxResults(range[1] - range[0] + 1);
//        q.setFirstResult(range[0]);
//        return q.getResultList();
//    }
//
//    @Override
//    public int count() {
//        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
//        javax.persistence.criteria.Root<Question> rt = cq.from(entityClass);
//        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
//        javax.persistence.Query q = getEntityManager().createQuery(cq);
//        return ((Long) q.getSingleResult()).intValue();
//    }

}
