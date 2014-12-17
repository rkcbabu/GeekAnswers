/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import entities.Subscription;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author s_paw_000
 */
@Stateless
public class SubscriptionFacade extends AbstractFacade<Subscription> {
    @PersistenceContext(unitName = "GeekAnswersPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SubscriptionFacade() {
        super(Subscription.class);
    }
    
    
}
