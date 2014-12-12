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
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author acer
 */
@ManagedBean(name="questionUtility")

@RequestScoped
public class QuestionUtilityController {

    @EJB
    private AnswerFacade answerFacade;
    @EJB
    private QuestionFacade questionFacade;
    
    @Inject
    private UserController userController;
    

    private Common common;
    private User user;

    private Question question;
    private String footer;
    private Answer answer;
    private String questionVoteMessage = "";

    public Answer getAnswer() {
        return answer;
    }

    public String getQuestionVoteMessage() {
        return questionVoteMessage;
    }

    public void setQuestionVoteMessage(String questionVoteMessage) {
        this.questionVoteMessage = questionVoteMessage;
    }

    public void questionThumbsUpHandller() {
        try {
            if (Objects.equals(this.user.getId(), this.question.getUser().getId())) {
                this.questionVoteMessage = "You are not allowed to vote own question";
            } else {
                this.questionVoteMessage = "Voted up successfully";

            }

        } catch (NullPointerException e) {
            e.printStackTrace();
            //common.redirectLogin();
        }
    }

    public void questionThumbsDownHandller() {
        try {

            if (this.user.getId() == this.question.getUser().getId()) {
                this.questionVoteMessage = "You are not allowed to vote own question";
            } else {
                this.questionVoteMessage = "Voted down successfully";
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            // common.redirectLogin();
        }
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public void addAnswer() {
        answer.setCreatedDate(new Date());
        answer.setQuestion(question);
        User usr = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("logged_in_user");
       // User usr=(User)common.getSession("logged_in_user");
        //  System.out.println("user="+usr.getFirstName());
        answer.setUser(usr);
        answerFacade.create(answer);

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI() + "?id=" + question.getId());
        } catch (IOException ex) {
            Logger.getLogger(QuestionUtilityController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String getFooter() {
        String footer;
        footer = "By:" + this.question.getUser().getFirstName() + " " + this.question.getUser().getLastName();
        footer += " On " + question.getCreatedDate();

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

    public QuestionUtilityController() {
        common = new Common();
        question = new Question();
        answer = new Answer();
        user = new User();

    }

    @PostConstruct
    public void generateQuestion() {

        try {

            Long mid = Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id").toString());
            question = this.questionFacade.find(mid);
            System.out.println("question id=" + mid);
         //   System.out.println();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("exception");

        }

        try {
            User sessionUser;

       //  sessionUser=(User)common.getSession("logged_in_user");
            //System.out.println(FacesContext.getCurrentInstance().getExternalContext().getSessionMap());
          sessionUser = (User) (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("logged_in_user"));
            //sessionUser=userController.getLoggedInUser();
            System.out.println("Logged in uid="+sessionUser.getId());
            if (sessionUser != null) {
                this.user = sessionUser;
                //System.out.println("Logged in uid="+sessionUser.getId());
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("User is not logged in");
        }

      //  Long id=401L;
        // System.out.println("id="+id);
    }

}
