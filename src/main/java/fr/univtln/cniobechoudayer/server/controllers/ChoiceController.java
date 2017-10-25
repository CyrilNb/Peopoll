package fr.univtln.cniobechoudayer.server.controllers;

import fr.univtln.cniobechoudayer.model.entities.Choice;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ChoiceController {

    public List<Choice> getAllChoicesFor(int idPoll){
        //TODO
        return Collections.emptyList();
    }

    public Choice createChoice(Date dateChoice, int startingTime, int endingTime){
        return new Choice(dateChoice, startingTime, endingTime);
    }

}
