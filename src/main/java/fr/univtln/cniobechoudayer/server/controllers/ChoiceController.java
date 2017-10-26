package fr.univtln.cniobechoudayer.server.controllers;

import fr.univtln.cniobechoudayer.model.EntityManager;
import fr.univtln.cniobechoudayer.model.entities.Choice;
import fr.univtln.cniobechoudayer.model.entities.Poll;
import fr.univtln.cniobechoudayer.server.exceptions.PersistanceException;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ChoiceController {

    public List<Choice> getAllChoicesFor(int idPoll) throws PersistanceException{
        EntityManager entityManager = EntityManager.getInstance();
        List<Choice> listReturnedChoices;
        try{
            listReturnedChoices = Choice.findAllByIdPoll(idPoll);
            if(listReturnedChoices != null){
                return listReturnedChoices;
            }else {
                return Collections.emptyList();
            }
        }catch (PersistanceException e){
            e.getException().printStackTrace();
            return null;
        }finally {
            entityManager.dispose();
        }
    }

    public static void createChoiceInDB(Date dateChoice, int startingTime, int endingTime,int idPoll) throws PersistanceException{
        EntityManager entityManager = EntityManager.getInstance();
        Choice newChoice = new Choice(dateChoice,startingTime,endingTime,idPoll);
        entityManager.persist(newChoice);
        entityManager.dispose();
    }

}
