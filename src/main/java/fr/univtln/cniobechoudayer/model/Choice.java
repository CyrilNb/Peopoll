package fr.univtln.cniobechoudayer.model;

import java.util.Date;

/**
 * Class which represent a choice
 * Created by Cyril on 16/10/2017.
 */
public class Choice {

    /* private fields */
    private int idChoice;
    private Date dateChoice;
    private int heureDebut;
    private int heureFin;


    /**
     * Default constructor
     */
    public Choice(){
        super();
    }

    /**
     * Constructor
     * @param idChoice
     * @param dateChoice
     * @param heureDebut
     * @param heureFin
     */
    public Choice(int idChoice, Date dateChoice, int heureDebut, int heureFin) {
        this.idChoice = idChoice;
        this.dateChoice = dateChoice;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
    }

    /**
     * Constructor
     * @param idChoice
     * @param dateChoice
     */
    public Choice(int idChoice, Date dateChoice) {
        this.idChoice = idChoice;
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
     * Gets heureFin.
     *
     * @return Value of heureFin.
     */
    public int getHeureFin() {
        return heureFin;
    }

    /**
     * Gets heureDebut.
     *
     * @return Value of heureDebut.
     */
    public int getHeureDebut() {
        return heureDebut;
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
     * Sets new heureFin.
     *
     * @param heureFin New value of heureFin.
     */
    public void setHeureFin(int heureFin) {
        this.heureFin = heureFin;
    }

    /**
     * Sets new heureDebut.
     *
     * @param heureDebut New value of heureDebut.
     */
    public void setHeureDebut(int heureDebut) {
        this.heureDebut = heureDebut;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("idChoice: ").append(this.idChoice)
                .append(" dateChoice: ").append(this.dateChoice)
                .append(" heureDebut: ").append(this.getHeureDebut())
                .append(" heureFin: ").append(this.heureFin);
        return sb.toString();
    }
}


//TODO equals haschode compare
