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
    @NamedQuery(name = "answer.vote.count", query = "SELECT SUM(v.vote) FROM AnswerVote v WHERE v.answer=:answer"),
    @NamedQuery(name = "answer.vote.find.id", query = "SELECT v.id FROM AnswerVote v WHERE v.user=:user AND v.answer=:answer"),
    @NamedQuery(name = "answer.category.vote.count", query = "SELECT SUM(v.vote) FROM AnswerVote v WHERE v.user=:user AND v.answer.question.category=:category")

})
@Entity

@DiscriminatorValue("A")
public class AnswerVote extends Vote implements Serializable {

    public AnswerVote() {
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    @ManyToOne
    private Answer answer;

}
