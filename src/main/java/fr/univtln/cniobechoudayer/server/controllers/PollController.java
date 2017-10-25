package fr.univtln.cniobechoudayer.server.controllers;

import fr.univtln.cniobechoudayer.model.EntityManager;
import fr.univtln.cniobechoudayer.model.entities.Poll;
import fr.univtln.cniobechoudayer.server.exceptions.PersistanceException;

/**
 * Controller of Poll class
 * Created by Cyril on 16/10/2017.
 */
public class PollController {

    public static Poll searchPollByCode(int codePoll) throws PersistanceException {
        EntityManager entityManager = EntityManager.getInstance();
        Poll foundPoll;
        try {
            foundPoll = Poll.findById(codePoll);
            if(foundPoll != null){
                return foundPoll;
            }else{
                return null;
            }
        } catch (PersistanceException e) {
            e.getException().printStackTrace();
            return null;
        }finally {
            entityManager.dispose();
        }
    }

    public static int createPoll(String title, String location, String info, String nameCreator, String mailCreator, int nbMax) throws PersistanceException{
        System.out.println("enterd createPoll from PollController");
        EntityManager entityManager = EntityManager.getInstance();
        int returnedID = entityManager.persist(new Poll.PollBuilder(title,nameCreator,mailCreator).setLocation(location).setDescription(info).setNbMaxContributor(nbMax).build());
        entityManager.dispose();
        System.out.println("returnedID= " +returnedID);
        System.out.println("exit createPoll from PollController");
        return returnedID;
    }

}
