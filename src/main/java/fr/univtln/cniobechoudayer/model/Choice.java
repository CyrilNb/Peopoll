package fr.univtln.cniobechoudayer.model;

import java.util.*;

/**
 * Created by Cyril on 16/10/2017.
 */
public class Choice {

    private int ID;

    private int IDpoll;

    private Date day;

    private String startingTime;

    private String endingTime;


    /*
    First contructor for choice, if no time limits are indicated
     */
    public Choice(int ID, int IDpoll, Date day) {
        this.ID = ID;
        this.IDpoll = IDpoll;
        this.day = day;
    }

    /*
    Second constructor with time limits
    starting and ending time are expected, they are working together
     */
    public Choice(int ID, int IDpoll, Date day, String startingTime, String endingTime) {
        this.ID = ID;
        this.IDpoll = IDpoll;
        this.day = day;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
    }
}
