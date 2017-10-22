package fr.univtln.cniobechoudayer.model;

import java.util.*;

/**
 * Created by Cyril on 16/10/2017.
 */
public class Comment {

    private int ID;

    private String author;

    private Date time;

    private String content;

    /*
    Constructor for comment
     */
    public Comment(int ID, String author, Date time, String content) {
        this.ID = ID;
        this.author = author;
        this.time = time;
        this.content = content;
    }
}
