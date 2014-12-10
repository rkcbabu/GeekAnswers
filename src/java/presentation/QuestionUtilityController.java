/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import boundary.AnswerFacade;
import boundary.QuestionFacade;
import common.Common;
import entities.Answer;
import entities.Question;
import entities.User;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author acer
 */
@ManagedBean(name="questionUtility")
@Stateless

public class QuestionUtilityController {
    @EJB
    private AnswerFacade answerFacade;
    @EJB
    private QuestionFacade questionFacade;
    
       
    private Common common;
    
    private Question question;
    private String footer;
    private Answer answer;
    private String questionVoteMessage="";

    public Answer getAnswer() {
        return answer;
    }

    public String getQuestionVoteMessage() {
        return questionVoteMessage;
    }

    public void setQuestionVoteMessage(String questionVoteMessage) {
        this.questionVoteMessage = questionVoteMessage;
    }
    
    public void thumbsUpHandller(){
        this.questionVoteMessage="Voted successfully";
        
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
    public void addAnswer(){
        answer.setCreatedDate(new Date());
        answer.setQuestion(question);
      User usr=(User)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("logged_in_user");
       // User usr=(User)common.getSession("logged_in_user");
      //  System.out.println("user="+usr.getFirstName());
        answer.setUser(usr);
        answerFacade.create(answer);
        
         ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI()+"?id="+question.getId());
        } catch (IOException ex) {
            Logger.getLogger(QuestionUtilityController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        
        
    }

    public String getFooter() {
        String footer;
        footer="By:"+this.question.getUser().getFirstName()+ " "+this.question.getUser().getLastName();
        footer+=" On "+question.getCreatedDate();
        
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }
    
    

    public String getTitle() {
        return question.getTitle();
    }


    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
    
    public QuestionUtilityController(){
         common=new Common();
        question=new Question();
        answer=new Answer();
     
    }
    
    @PostConstruct
    public void generateQuestion(){
        
       // Long id=Long.parseLong(common.getRequestValue("id"));
        Long id=401L;
       // System.out.println("id="+id);
        question=this.questionFacade.find(id);
  
    }
   
   
}
