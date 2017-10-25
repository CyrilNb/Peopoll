package fr.univtln.cniobechoudayer.server;


import fr.univtln.cniobechoudayer.client.views.Step3ViewController;
import fr.univtln.cniobechoudayer.server.exceptions.PersistanceException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Test class
 * Created by Cyril on 25/10/2017.
 */
public class Test {
    public static void main(String[] args) {

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
            System.out.println("TEST CREATION POLL AND PERSISTS SUCCEED");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PersistanceException e) {
            e.printStackTrace();
        }

        //test here read from the db
        //test here update from the db

        System.out.println("test");

    }
}
