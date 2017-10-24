package fr.univtln.cniobechoudayer.server.controllers;

import fr.univtln.cniobechoudayer.model.EntityManager;
import fr.univtln.cniobechoudayer.model.entities.Poll;
import fr.univtln.cniobechoudayer.server.exceptions.PersistanceException;

/**
 * Controller of Poll class
 * Created by Cyril on 16/10/2017.
 */
public class PollController {

    public Poll searchPollByCode(int codePoll) throws PersistanceException {
        Poll foundPoll;
        try {
            foundPoll = Poll.findById(codePoll);
            if(foundPoll != null){
                return foundPoll;
            }else{
                return null;
            }
        } catch (PersistanceException e) {
            return null;
        }
    }

    public int createPoll(String title, String nameCreator, String mailCreator) throws PersistanceException{
        EntityManager entityManager = EntityManager.getInstance();
        int returnedID = entityManager.persist(new Poll.PollBuilder(title,nameCreator,mailCreator).build());
        entityManager.dispose();
        return returnedID;
    }


}
