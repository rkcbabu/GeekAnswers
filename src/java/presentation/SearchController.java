/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import boundary.QuestionFacade;
import entities.Question;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Chaulagai
 */
@Named(value = "searchController")
@javax.enterprise.context.RequestScoped

public class SearchController {
    @EJB
    private QuestionFacade questionFacade;
     
    
    private String searchKey; 

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }
    
    public SearchController() {
        this.searchKey = ""; 
    }
    public String process(){
//        System.out.println(searchKey);
//        System.out.println(questionFacade.search(searchKey));
        return "index.xhtml?searchKey="+searchKey+"&faces-redirect=true&includeViewParams=true"; //finding
    }
    public List<Question> searchQuestions(){
        return questionFacade.search(searchKey);
    }
    @PostConstruct
    public void paramAdd(){
        this.searchKey = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("searchKey");
    }
    
}
