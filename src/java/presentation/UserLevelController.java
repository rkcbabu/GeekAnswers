/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import boundary.ExpertiseFacade;
import boundary.QuestionVoteFacade;
import boundary.UserFacade;
import boundary.UserInteractionFacade;
import boundary.UserLevelFacade;
import entities.User;
import entities.userlevel.UserLevel;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

/**
 *
 * @author acer
 */
@ManagedBean
public class UserLevelController {
    @EJB
    private UserInteractionFacade userInteractionFacade;
    @EJB
    private QuestionVoteFacade questionVoteFacade;
    @EJB
    private ExpertiseFacade expertiseFacade;
    @EJB
    private UserFacade userFacade;
    
    @Inject
    UserLevelTransitionTable userLevelTransition;
    
    @EJB
    private UserLevelFacade userLevelFacade;
    
    private User user;
   
   
    public UserLevelController(){
        
    }
    public void handleuserLevel(User currentUser){
        user=currentUser;
        this.updateUserPoint();
        if(this.user.getUserlevel()==null)
            this.user.setUserlevel(this.userLevelFacade.getInitialUserLevel());
        
        
        UserLevel level;
        
        if(user.getTotalPoint()<user.getUserlevel().getMinPoint()){
            level=this.userLevelTransition.getPrevious(user.getUserlevel());
            if(level!=null){
                user.setUserlevel(level);
                userFacade.edit(user);
                
                // send mail for level down notification
                System.out.println("Sending mail for level down notification ");
                
                
            }
        }
        
        if(user.getTotalPoint()>user.getUserlevel().getMaxPoint()){
           level=this.userLevelTransition.getNext(user.getUserlevel());
            
            if(level!=null){
                user.setUserlevel(level);
                userFacade.edit(user);
                
                // send mail for level up notification
                
                System.out.println("Sending mail for level up notification ");

                
            }
            else{
                System.out.println("next level not found");
            }
        }
       
    }
    
    
    public void updateUserPoint(){
        
        int interactionPoint=0;
        int contextualPoint=0;
        int expertPoint=0;
        
        interactionPoint=this.userInteractionFacade.countTotalInteractionPointOfUser(user);
        expertPoint=this.expertiseFacade.getTotalExpertisePoint(user);
        contextualPoint=this.questionVoteFacade.countTotalQuestionVotesOfUser(user);
        int totalPoint=interactionPoint+contextualPoint+expertPoint;
        
        this.user.setTotalPoint(totalPoint);
        this.userFacade.edit(user);
        
        
        System.out.println("User total point="+totalPoint);
        
        
    }
    
}
