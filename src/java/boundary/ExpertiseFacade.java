/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import entities.Category;
import entities.Expertise;
import entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author acer
 */
@Stateless
public class ExpertiseFacade extends AbstractFacade<Expertise> {

    @PersistenceContext(unitName = "GeekAnswersPU")
    private EntityManager em;

    @EJB
    AnswerVoteFacade answerVoteFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ExpertiseFacade() {
        super(Expertise.class);
    }
    
    
    
        public List<Expertise> getUserExpertiseExist(User u){
         TypedQuery<Expertise> query = em.createNamedQuery("user.expertise.list", Expertise.class);
        query.setParameter("user", u);
        
       
       
            try{
                return query.getResultList();
               
            }
            catch(Exception e){
                return new ArrayList<Expertise>();
            }
       
        
       
    }
    
    public Expertise expertiseExist(User u, Category c){
         TypedQuery<Expertise> query = em.createNamedQuery("user.expertise.category", Expertise.class);
        query.setParameter("user", u);
        query.setParameter("category", c);
       
       
            try{
                List<Expertise> list=query.getResultList();
                if(list.isEmpty()) return null;
                return list.get(0);
            }
            catch(Exception e){
                return null;
            }
       
        
       
    }
    
    
    public int getTotalExpertisePoint(User u){
        
         TypedQuery<Expertise> query = em.createNamedQuery("user.expertise.total.point", Expertise.class);
        query.setParameter("user", u);
       
       
       
            try{
                List  list=query.getResultList();
                if(list.isEmpty()) return 0;
                return Integer.parseInt(list.get(0).toString());
            }
            catch(Exception e){
                return 0;
            }
    }

    public void updateExpertise(User u, Category c) {
        
        Expertise existing=expertiseExist(u, c);
        int expertisePoint = answerVoteFacade.countAnswerVote(u, c);
      
        if(existing!=null){
            // update
            existing.setPoint(expertisePoint);
            this.edit(existing);
        }
        else{
            // create new;
            existing=new Expertise();
            existing.setCategory(c);
            existing.setUser(u);
            existing.setPoint(expertisePoint);
            this.create(existing);
        }
       

    }

}
