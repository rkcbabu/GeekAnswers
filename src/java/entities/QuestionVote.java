/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author acer
 */

@NamedQueries({
       @NamedQuery(name = "question.vote.count",query = "SELECT SUM(v.vote) FROM QuestionVote v WHERE  v.question=:question"),

    @NamedQuery(name = "question.vote.up.count",query = "SELECT SUM(v.vote) FROM QuestionVote v WHERE v.vote=1 AND v.question=:question"),
    @NamedQuery(name = "question.vote.down.count",query = "SELECT SUM(v.vote) FROM QuestionVote v WHERE v.vote=-1 AND v.question=:question"),

})

@Entity
@DiscriminatorValue("Q")
public class QuestionVote extends Vote implements Serializable {
   
    @ManyToOne
    private Question question;

    public QuestionVote() {
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
    
    
 
}
