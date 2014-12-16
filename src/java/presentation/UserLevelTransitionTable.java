/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import entities.userlevel.*;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author acer
 */
@ManagedBean
@Stateless
public class UserLevelTransitionTable {

    private Map<UserLevel, UserLevels> transition;

    public UserLevelTransitionTable() {
        transition = new HashMap<>();

        transition.put(new UserLevel1(), new UserLevels(null, new UserLevel2()));
        transition.put(new UserLevel2(), new UserLevels(new UserLevel1(), new UserLevel3()));
        transition.put(new UserLevel3(), new UserLevels(new UserLevel2(), new UserLevel4()));
        transition.put(new UserLevel4(), new UserLevels(new UserLevel3(), new UserLevel5()));
        transition.put(new UserLevel5(), new UserLevels(new UserLevel4(), null));

    }
    
    public UserLevel getNext(UserLevel current){
       try{
        return this.transition.get(current).getNext();
       }
       catch(Exception e){
           return null;
       }
    }
    
     public UserLevel getPrevious(UserLevel current){
        
         try{
        return this.transition.get(current).getPrevious();
         }
         catch(Exception e){
             return null;
         }
         
    }

    class UserLevels {

        private final UserLevel previous;
        private final UserLevel next;

        public UserLevels(UserLevel pre, UserLevel nxt) {
            this.previous = pre;
            this.next = nxt;
        }

        public UserLevel getPrevious() {
            return previous;
        }

        public UserLevel getNext() {
            return next;
        }
    }

}
