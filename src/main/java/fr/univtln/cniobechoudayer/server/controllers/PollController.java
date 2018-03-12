package fr.univtln.cniobechoudayer.server.controllers;

import fr.univtln.cniobechoudayer.model.Entity;
import fr.univtln.cniobechoudayer.model.EntityManager;
import fr.univtln.cniobechoudayer.model.entities.Choice;
import fr.univtln.cniobechoudayer.model.entities.Poll;
import fr.univtln.cniobechoudayer.server.exceptions.PersistanceException;

/**
 * Controller of Poll class
 * Created by Cyril on 16/10/2017.
 */
public class PollController {

    /**
     * Search a poll by using its ID
     * @param codePoll
     * @return
     * @throws PersistanceException
     */
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

    /**
     * Create a poll and persist it in the DB
     * @param title
     * @param location
     * @param info
     * @param nameCreator
     * @param mailCreator
     * @param nbMax
     * @param isLocked
     * @return
     * @throws PersistanceException
     */
    public static int createPoll(String title, String location, String info, String nameCreator, String mailCreator, int nbMax, boolean isLocked) throws PersistanceException{
        EntityManager entityManager = EntityManager.getInstance();
        String managerCode = Character.toString(title.charAt(0)) + Character.toString(nameCreator.charAt(0));
        int returnedID = entityManager.persist(new Poll.PollBuilder(title,nameCreator,mailCreator).setLocation(location).setDescription(info).setNbMaxContributor(nbMax).setIsLocked(isLocked).setManagerCode(managerCode).build());
        entityManager.dispose();
        return returnedID;
    }

    /**
     * Updates poll in db
     * @param poll
     * @throws PersistanceException
     */
    public static void updatePoll(Poll poll) throws PersistanceException {
        EntityManager entityManager = EntityManager.getInstance();
        entityManager.merge(poll);
        entityManager.dispose();
    }

    /**
     * Updates the lock of the poll
     * @param poll
     * @throws PersistanceException
     */
    public static void lockPoll(Poll poll) throws PersistanceException {
        EntityManager entityManager = EntityManager.getInstance();
        entityManager.merge(poll);
        entityManager.dispose();
    }

    /**
     * Updates final date of the poll
     * @param poll
     * @param choice
     * @throws PersistanceException
     */
    public static void setFinalDate(Poll poll, Choice choice) throws PersistanceException {
        EntityManager entityManager = EntityManager.getInstance();
        poll.setIdFinalChoice(choice.getIdChoice());
        poll.setFinalChoice();
        entityManager.dispose();
    }

}
