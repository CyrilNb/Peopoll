package fr.univtln.cniobechoudayer.model.interfaces;

import fr.univtln.cniobechoudayer.model.entities.Poll;

import java.util.List;

/**
 * Interface to provide CRUD methods to deal with database
 * Created by Cyril on 24/10/2017.
 */
public interface PollDAO {
    List<Poll> findAll();
    Poll findById();
    boolean insertPoll(Poll poll);
    boolean updatePoll(Poll poll);
    boolean deletePoll(Poll poll);
}
