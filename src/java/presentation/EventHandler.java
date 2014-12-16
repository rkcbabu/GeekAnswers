/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import boundary.ExpertiseFacade;
import boundary.UserInteractionFacade;
import entities.Category;
import entities.User;
import entities.interaction.UserInteraction;
import java.util.Date;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author acer
 */
@ManagedBean(name = "eventHandler")
@Asynchronous
public class EventHandler {
    @EJB
    private ExpertiseFacade expertiseFacade;
   

    @EJB
    private UserInteractionFacade userInteractionFacade;
    
    @Inject
    UserLevelController userLevelController;
    

    public User user;
    public UserInteraction ui;
    public Category category;
    public User expertUser; // user whos expertise needs to be recalculated

    /**
     * Creates a new instance of EventHandler
     */
    public EventHandler() {
        user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("logged_in_user");
    }

    public void triggerEvent(UserInteraction uit) {
        if(user==null){
           user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("logged_in_user");
 
        }
        
        this.ui = uit;
        uit.setInteractionDate(new Date());
        uit.setUser(user);
        userInteractionFacade.create(uit);
        // user.updateUserlevel
        
        userLevelController.handleuserLevel(user);
        
        if(uit.isTriggerExpertise()){
            if(this.expertUser!=null){
                this.expertiseFacade.updateExpertise(user, category);
                 userLevelController.handleuserLevel(expertUser);
                //expertuser.updateUserlevel
            }
        }
        
        
       

    }

     public void triggerEvent(UserInteraction uit, User expertUser, Category c) {
        this.expertUser = expertUser;
        this.category=c;
        triggerEvent(uit);
    }
    

     
    
  

}
