/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import boundary.QuestionFacade;
import common.Common;
import entities.Question;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author acer
 */
@ManagedBean(name="questionUtility")
@Stateless

public class QuestionUtilityController {
    @EJB
    private QuestionFacade questionFacade;
    
   
    private Common common;
    
    private Question question;
    private String title;

    public String getTitle() {
        return common.getRequestValue("id");
       // return null;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
    
    public QuestionUtilityController(){
       question=new Question();
       common=new Common();
    }
    
   
    private void makeQuestionFromURL(){
       // HttpServletRequest req=new HttpServletRequest();
        title="hello";
        System.out.println("making questions");
    }
    
}
