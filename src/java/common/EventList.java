/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import entities.interaction.*;

/**
 *
 * @author acer
 */
public class EventList {

    public static final UserInteraction USER_REGISTRATION = new UserRegistration();
    public static final UserInteraction QUESTION_CREATION = new QuestionCreation();
    public static final UserInteraction QUESTION_ANSWERED = new QuestionAnswered();
    public static final UserInteraction QUESTION_LIKED = new QuestionLiked();
    public static final UserInteraction QUESTION_DISLIKED = new QuestionDisliked();
    public static final UserInteraction ANSWER_LIKED = new AnswerLiked();
    public static final UserInteraction ANSWER_DISLIKED = new AnswerDisliked();
    public static final UserInteraction USER_LOGIN = new UserLoggedIn();

}
