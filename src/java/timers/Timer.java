/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timers;

import boundary.QuestionFacade;
import boundary.UserFacade;
import entities.Question;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.SessionContext;
import javax.ejb.Singleton;
import javax.ejb.Stateless;

@Singleton
public class Timer {
//    @Schedule (dayOfWeek="*")
    public void sendSubscriptionMail(){
        
    }
    
}
