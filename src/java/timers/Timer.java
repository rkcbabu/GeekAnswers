/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timers;

import boundary.QuestionFacade;
import boundary.UserFacade;
import entities.Question;
import entities.User;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import mailservice.MailServices;

@Singleton
public class Timer {
    @EJB
    private UserFacade userfacade;
    
    @EJB
    private QuestionFacade questionFacade;
    
    public static int count=0;
    
    @Schedule (dayOfWeek="*")
    public void sendSubscriptionMail(){
        for (User u:userfacade.subscribedUsers()){
            sendSubscription(u);
        }
        
    }
    
    @Asynchronous
    public void sendSubscription(User u){
        String subscriptionBody = "<p>Hello <br/>"
                + u.getFirstName() + " " + u.getLastName() + "<br/>"
                + "We hope you are enjoying our website. Please help us grow by "
                + "contributing some questions or answers. <br/>"
                + "You can build your reputation and earn points. <br/>"
                + "Meanwhile, could you answer these unanswered questions <br/><br/>";
        for (Question q:questionFacade.getTopUnanswered()){
            subscriptionBody += "<h1>"+q.getTitle()+"</h1><br/>";
            subscriptionBody += "<h3>"+q.getContentShrunk()+"</h3><hr/>";
        }
        subscriptionBody+="Thank you,<br/>";
        subscriptionBody+="Geek Answers Team";
        MailServices.sendEmail(u.getEmail(), "Subscription Test", subscriptionBody);     
    }
}
