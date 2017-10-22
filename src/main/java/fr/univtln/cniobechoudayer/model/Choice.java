package fr.univtln.cniobechoudayer.model;

import java.util.Date;

/**
 * Class which represent a choice
 * Created by Cyril on 16/10/2017.
 */
public class Choice {

    /* private fields */
    private int idChoice;
    private final int idPoll;
    private Date dateChoice;
    private int startingTime;
    private int endingTime;


    /**
     * Constructor with startingTime and endingTime
     * @param idChoice
     * @param dateChoice
     * @param startingTime
     * @param endingTime
     */
    public Choice(int idChoice,int idPoll, Date dateChoice, int startingTime, int endingTime) {
        this.idChoice = idChoice;
        this.idPoll = idPoll;
        this.dateChoice = dateChoice;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
    }

    /**
     * Constructor with if no time limits are set
     * @param idChoice
     * @param dateChoice
     */
    public Choice(int idChoice,int idPoll, Date dateChoice) {
        this.idChoice = idChoice;
        this.idPoll = idPoll;
        this.dateChoice = dateChoice;
    }

    /**
     * Gets idChoice.
     *
     * @return Value of idChoice.
     */
    public int getIdChoice() {
        return idChoice;
    }

    /**
     * Gets dateChoice.
     *
     * @return Value of dateChoice.
     */
    public Date getDateChoice() {
        return dateChoice;
    }

    /**
     * Gets endingTime.
     *
     * @return Value of endingTime.
     */
    public int getEndingTime() {
        return endingTime;
    }

    /**
     * Gets startingTime.
     *
     * @return Value of startingTime.
     */
    public int getStartingTime() {
        return startingTime;
    }

    /**
     * Sets new dateChoice.
     *
     * @param dateChoice New value of dateChoice.
     */
    public void setDateChoice(Date dateChoice) {
        this.dateChoice = dateChoice;
    }

    /**
     * Sets new endingTime.
     *
     * @param endingTime New value of endingTime.
     */
    public void setEndingTime(int endingTime) {
        this.endingTime = endingTime;
    }

    /**
     * Sets new startingTime.
     *
     * @param startingTime New value of startingTime.
     */
    public void setStartingTime(int startingTime) {
        this.startingTime = startingTime;
    }

    /**
     * Gets idPoll.
     *
     * @return Value of idPoll.
     */
    public int getIdPoll() {
        return idPoll;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("idChoice: ").append(this.idChoice)
                .append(" dateChoice: ").append(this.dateChoice)
                .append(" startingTime: ").append(this.startingTime)
                .append(" endingTime: ").append(this.endingTime);
        return sb.toString();
    }


}


//TODO equals haschode compare
