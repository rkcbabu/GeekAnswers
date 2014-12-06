/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import entities.Question;
import entities.User;

/**
 *
 * @author acer
 */
public class QuestionAuthorCheckPredicate  extends Predicate<Question, User>{

    @Override
    public boolean execute(Question elm1, User elm2) {
      
        if(elm1.getUser().getId().equals(elm2.getId()))
            return true;
        else return false;
        
    }

     
        
    }
    

