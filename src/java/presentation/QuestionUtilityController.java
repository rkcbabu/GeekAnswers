/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import boundary.AnswerFacade;
import boundary.AnswerVoteFacade;
import boundary.QuestionFacade;
import boundary.QuestionVoteFacade;
import common.Common;
import entities.Answer;
import entities.AnswerVote;
import entities.Question;
import entities.QuestionVote;
import entities.User;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author acer
 */
@ManagedBean(name = "questionUtility")
@Stateless

public class QuestionUtilityController {

    @EJB
    private AnswerVoteFacade answerVoteFacade;

    @EJB
    private QuestionVoteFacade questionVoteFacade;

    @EJB
    private AnswerFacade answerFacade;
    @EJB
    private QuestionFacade questionFacade;

    private Common common;
    private User user;

    private Question question;
    private String footer;
    private Answer answer;
    private String questionVoteMessage = "";

     private String answerVoteMessage = "";
    @PersistenceContext(unitName = "GeekAnswersPU")
    private EntityManager em;

    private QuestionVote questionVote;
    private AnswerVote answerVote;

    public QuestionUtilityController() {
        common = new Common();
        question = new Question();
        answer = new Answer();
        user = new User();
        questionVote = new QuestionVote();
        answerVote = new AnswerVote();

    }

    public String getAnswerVoteMessage() {
        return answerVoteMessage;
    }

    public void setAnswerVoteMessage(String answerVoteMessage) {
        this.answerVoteMessage = answerVoteMessage;
    }
    
    public int getQuestionVoteCount() {
        int vote = this.questionVoteFacade.countQuestionVote(question);
       // System.out.println("question vote=" + vote);
        return vote;
    }

    public int getAnswerVoteCount() {
        return this.answerVoteFacade.countAnswerVote(answer);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public QuestionVote getQuestionVote() {
        return questionVote;
    }

    public void setQuestionVote(QuestionVote questionVote) {
        this.questionVote = questionVote;
    }

    public AnswerVote getAnswerVote() {
        return answerVote;
    }

    public void setAnswerVote(AnswerVote answerVote) {
        this.answerVote = answerVote;
    }

    public Answer getAnswer() {
        return answer;
    }

    public String getQuestionVoteMessage() {
        return questionVoteMessage;
    }

    public void setQuestionVoteMessage(String questionVoteMessage) {
        this.questionVoteMessage = questionVoteMessage;
    }

    public Long hasQuestionVoted() {
        String queryString = "SELECT COUNT(s) FROM QuestionVote s WHERE s.user=:user AND s.question=:question";
        TypedQuery<QuestionVote> query;
        query = em.createQuery(queryString, QuestionVote.class);
        query.setParameter("user", this.user);
        query.setParameter("question", this.question);
        List<QuestionVote> count;
        try {

            count = query.getResultList();
            if (count.isEmpty()) {
                return 0L;
            } else {
                return count.get(0).getId();
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("new vote gonna register now");
            return 0L;
        }

    }
    public Long hasAnswerVoted() {
        String queryString = "SELECT COUNT(s) FROM AnswerVote s WHERE s.user=:user AND s.answer=:answer";
        TypedQuery<AnswerVote> query;
        query = em.createQuery(queryString, AnswerVote.class);
        query.setParameter("user", this.user);
        query.setParameter("answer", this.answer);
        List<AnswerVote> count;
        try {

            count = query.getResultList();
            if (count.isEmpty()) {
                return 0L;
            } else {
                return count.get(0).getId();
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("new answer vote gonna register now");
            return 0L;
        }

    }
    public void questionThumbsUpHandller() {
        try {
            if (Objects.equals(this.user.getId(), this.question.getUser().getId())) {
                this.questionVoteMessage = "You are not allowed to vote own question";

            } else {

                questionVote.setUser(user);
                questionVote.setVote(1);
                questionVote.setQuestion(question);
                Long preVoteId;
                preVoteId = hasQuestionVoted();

                if (preVoteId > 0L) {
                    questionVote = questionVoteFacade.find(preVoteId);
                    questionVote.setVote(1);
                    em.merge(questionVote);
                    System.out.println("vote up added");
                    this.questionVoteMessage = "Vote changed to vote up";
                } else {
                    //  em.persist(questionVote);

                    questionVoteFacade.create(questionVote);

                    this.questionVoteMessage = "Vote added: Vote up";
                }

            }

        } catch (NullPointerException e) {
            e.printStackTrace();
            //common.redirectLogin();
        }
    }

    public void questionThumbsDownHandller() {
        try {
            if (Objects.equals(this.user.getId(), this.question.getUser().getId())) {
                this.questionVoteMessage = "You are not allowed to vote own question";

            } else {

                this.questionVote.setUser(user);
                this.questionVote.setVote(-1);
                this.questionVote.setQuestion(question);

                Long preVoteId;
                preVoteId = hasQuestionVoted();

                if (preVoteId > 0L) {
                    questionVote = questionVoteFacade.find(preVoteId);
                    questionVote.setVote(-1);

                    em.merge(questionVote);
                    System.out.println("vote down added");
                    this.questionVoteMessage = "Vote changed to vote down";
                } else {
                    //  em.persist(questionVote);

                    questionVoteFacade.create(questionVote);

                    this.questionVoteMessage = "Vote added: Vote down";
                }

            }

        } catch (NullPointerException e) {
            e.printStackTrace();
            //common.redirectLogin();
        }
    }

    
     public void answerThumbsUpHandller() {
        try {
            if (Objects.equals(this.user.getId(), this.answer.getUser().getId())) {
                this.answerVoteMessage = "You are not allowed to vote own answer";

            } else {

                this.answerVote.setUser(user);
                this.answerVote.setVote(1);
                this.answerVote.setAnswer(answer);

                Long preVoteId;
                preVoteId = hasAnswerVoted();

                if (preVoteId > 0L) {
                    answerVote = answerVoteFacade.find(preVoteId);
                    answerVote.setVote(1);

                    em.merge(answerVote);
                    System.out.println("vote down added");
                    this.answerVoteMessage = "Vote changed to vote down";
                } else {
                    //  em.persist(questionVote);

                    answerVoteFacade.create(answerVote);

                    this.answerVoteMessage = "Vote added: Vote down";
                }

            }

        } catch (NullPointerException e) {
            e.printStackTrace();
            //common.redirectLogin();
        }
    }

   
          public void answerThumbsDownHandller() {
        try {
            if (Objects.equals(this.user.getId(), this.answer.getUser().getId())) {
                this.answerVoteMessage = "You are not allowed to vote own answer";

            } else {

                this.answerVote.setUser(user);
                this.answerVote.setVote(-1);
                this.answerVote.setAnswer(answer);

                Long preVoteId;
                preVoteId = hasAnswerVoted();

                if (preVoteId > 0L) {
                    answerVote = answerVoteFacade.find(preVoteId);
                    answerVote.setVote(-1);

                    em.merge(answerVote);
                    System.out.println("vote down added");
                    this.answerVoteMessage = "Vote changed to vote down";
                } else {
                    //  em.persist(questionVote);

                    answerVoteFacade.create(answerVote);

                    this.answerVoteMessage = "Vote added: Vote down";
                }

            }

        } catch (NullPointerException e) {
            e.printStackTrace();
            //common.redirectLogin();
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
        return this.question;
    }

    public void setQuestion(Question question) {
        this.question = question;
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
            sessionUser = (User) (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("logged_in_user"));

            System.out.println("Logged in uid=" + sessionUser.getId());
            if (sessionUser != null) {
                this.user = sessionUser;
                //System.out.println("Logged in uid="+sessionUser.getId());
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("User is not logged in");
        }

         try {

            Long aid = Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("aid").toString());
            answer = this.answerFacade.find(aid);
            System.out.println("answer id=" + aid);
            //   System.out.println();

        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("No answer");

        }
    }

    public void persist(Object object) {
        em.persist(object);
    }

}
