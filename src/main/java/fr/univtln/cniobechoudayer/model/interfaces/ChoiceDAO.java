package fr.univtln.cniobechoudayer.model.interfaces;

import fr.univtln.cniobechoudayer.model.entities.Choice;

import java.util.List;

/**
 * Interface to provide CRUD methods to deal with database
 * Created by Cyril on 24/10/2017.
 */
public interface ChoiceDAO {
    List<Choice> findAll();
    Choice findById();
    boolean insertChoice(Choice choice);
    boolean updateChoice(Choice choice);
    boolean deleteChoice(Choice choice);
}
