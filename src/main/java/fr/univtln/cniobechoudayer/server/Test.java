package fr.univtln.cniobechoudayer.server;


import fr.univtln.cniobechoudayer.client.views.HomeViewController;
import fr.univtln.cniobechoudayer.client.views.PollViewController;
import fr.univtln.cniobechoudayer.client.views.Step3ViewController;
import fr.univtln.cniobechoudayer.model.entities.Choice;
import fr.univtln.cniobechoudayer.model.entities.Comment;
import fr.univtln.cniobechoudayer.model.entities.Poll;
import fr.univtln.cniobechoudayer.server.controllers.ChoiceController;
import fr.univtln.cniobechoudayer.server.controllers.CommentController;
import fr.univtln.cniobechoudayer.server.exceptions.PersistanceException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Test class
 * Created by Cyril on 25/10/2017.
 */
public class Test {
    public static void main(String[] args) {

        //test insert
        /*
        HashMap<String,String> mapArgs = new HashMap<>();
        mapArgs.put("Title","TestTitre");
        mapArgs.put("Location","DTC");
        mapArgs.put("Info","test de poll");
        mapArgs.put("Creator","Cyril");
        mapArgs.put("Mail","cyroil.niobe@gmail.com");
        mapArgs.put("NBMAX","12");

        Step3ViewController step3ViewController = new Step3ViewController(mapArgs);
        try {
            int idpoll = step3ViewController.validatePollCreation();
            System.out.println("TEST CREATION POLL AND PERSIST SUCCEED");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PersistanceException e) {
            e.printStackTrace();
        }*/


        //test searchById for a poll and creation comment for this poll
        /*Poll polltest;
        try {
            Class.forName(org.h2.Driver.class.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        HomeViewController homeViewController = new HomeViewController();
        try {
            polltest = homeViewController.searchPoll(1);
            PollViewController pollViewController = new PollViewController(polltest);
            try {
                System.out.println("test create comment");
                pollViewController.validateCommentCreation();
            } catch (PersistanceException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        //test choice findall()
        /*try {
            ChoiceController choiceController = new ChoiceController();
            List<Choice> list = choiceController.getAllChoicesFor(11);
            for (Choice choice :list
                 ) {
                System.out.println(choice);
            }
        } catch (PersistanceException e) {
            e.printStackTrace();
        }*/

        //test getAllCommentByPoll
        try {
            List<Comment> lisAllComments = CommentController.getAllCommentsByPoll(1);
            for (Comment item:lisAllComments
                 ) {
                System.out.println(item.getIdComment());
                //test remove comment
                //CommentController.removeCommentInDb(item);
            }
        } catch (PersistanceException e) {
            e.printStackTrace();
        }

    }
}
