package fr.univtln.cniobechoudayer.model.entities;

import fr.univtln.cniobechoudayer.model.Entity;
import fr.univtln.cniobechoudayer.server.exceptions.PersistanceException;

import java.sql.Connection;
import java.util.List;

/**
 * Class which represent a contribution made by someone
 * Created by Cyril on 16/10/2017.
 */
public class Contribution implements Entity {
    /** private fields**/
    private int idContribution;
    private String nameContributor;
    private int idPoll;
    private int idChoice;

    /** Constructor **/
    public Contribution(String nameContributor) {
        this.nameContributor = nameContributor;
    }

    /** Constructor **/
    public Contribution(int idContribution, String nameContributor) {
        this.idContribution = idContribution;
        this.nameContributor = nameContributor;
    }

    /** Constructor **/
    public Contribution(int idContribution, String nameContributor, int idPoll, int idChoice) {
        this.idContribution = idContribution;
        this.nameContributor = nameContributor;
        this.idPoll = idPoll;
        this.idChoice = idChoice;
    }

    /** Default constructor **/
    public Contribution(){

    }

    /**
     * Gets nameContributor.
     *
     * @return Value of nameContributor.
     */
    public String getNameContributor() {
        return nameContributor;
    }

    /**
     * Sets new nameContributor.
     *
     * @param nameContributor New value of nameContributor.
     */
    public void setNameContributor(String nameContributor) {
        this.nameContributor = nameContributor;
    }

    /**
     * Sets new private fields.
     *
     * @param idContribution New value of private fields.
     */
    public void setIdContribution(int idContribution) {
        this.idContribution = idContribution;
    }

    /**
     * Gets private fields.
     *
     * @return Value of private fields.
     */
    public int getIdContribution() {
        return idContribution;
    }

    public int getIdPoll() {
        return idPoll;
    }

    public void setIdPoll(int idPoll) {
        this.idPoll = idPoll;
    }

    public int getIdChoice() {
        return idChoice;
    }

    public void setIdChoice(int idChoice) {
        this.idChoice = idChoice;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("idContribution: ").append(this.idContribution)
                .append(" nameContributor: ").append(this.nameContributor);
        return sb.toString();
    }

    public static List<Contribution> findAll() {
        return null;
    }

    public static Contribution findById() {
        return null;
    }


    @Override
    public int persist(Connection connection) throws PersistanceException {
        return 1;
    }

    @Override
    public void merge(Connection connection) throws PersistanceException {

    }

    @Override
    public void update(Connection connection) throws PersistanceException {

    }

    @Override
    public void remove(Connection connection) throws PersistanceException {

    }
}

//TODO equals haschode compare
