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

    public int createPoll(String title, String location, String info, String nameCreator, String mailCreator, int nbMax) throws PersistanceException{
        EntityManager entityManager = EntityManager.getInstance();
        int returnedID = entityManager.persist(new Poll.PollBuilder(title,nameCreator,mailCreator).setLocation(location).setDescription(info).setNbMaxContributor(nbMax).build());
        entityManager.dispose();
        return returnedID;
    }



}
