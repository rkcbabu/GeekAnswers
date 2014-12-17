/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import entities.User;
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
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "GeekAnswersPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }

    public EntityManager getEM() {
        return em;
    }

    public boolean userExists(String email) {
        String query = "SELECT a.id FROM User a WHERE a.email = :email";
        TypedQuery<User> tq = em.createQuery(query, User.class);
        tq.setParameter("email", email);
        try {
            tq.getSingleResult();
        } catch (javax.persistence.NoResultException e) {
            return false;
        }
        return true;
    }

    public List<User> subscribedUsers() {
        TypedQuery<User> users = (TypedQuery<User>) em.createQuery("SELECT e FROM User e WHERE e.subscribed=true");
        try {
            return users.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
