package fr.univtln.cniobechoudayer.model.entities;

import fr.univtln.cniobechoudayer.model.Entity;
import fr.univtln.cniobechoudayer.server.exceptions.PersistanceException;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

/**
 * Class which represents a comment
 * Created by Cyril on 16/10/2017.
 */
public class Comment implements Entity{

    /* private fields */
    private int idComment;
    private String nameAuthor;
    private String content;
    private Date dateComment;


    /**
     * Constructor
     * @param idComment
     * @param nameAuthor
     * @param content
     * @param dateComment
     */
    public Comment(int idComment, String nameAuthor, String content, Date dateComment) {
        this.idComment = idComment;
        this.nameAuthor = nameAuthor;
        this.content = content;
        this.dateComment = dateComment;
    }

    /**
     * Default constructor
     */
    public Comment() {
    }

    /**
     * Gets idComment.
     *
     * @return Value of idComment.
     */
    public int getIdComment() {
        return idComment;
    }

    /**
     * Gets dateComment.
     *
     * @return Value of dateComment.
     */
    public Date getDateComment() {
        return dateComment;
    }


    /**
     * Gets nameAuthor.
     *
     * @return Value of nameAuthor.
     */
    public String getNameAuthor() {
        return nameAuthor;
    }

    /**
     * Gets content.
     *
     * @return Value of content.
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets new dateComment.
     *
     * @param dateComment New value of dateComment.
     */
    public void setDateComment(Date dateComment) {
        this.dateComment = dateComment;
    }

    /**
     * Sets new content.
     *
     * @param content New value of content.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Sets new nameAuthor.
     *
     * @param nameAuthor New value of nameAuthor.
     */
    public void setNameAuthor(String nameAuthor) {
        this.nameAuthor = nameAuthor;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("idComment: ").append(this.idComment)
                .append(" date: ").append(this.dateComment)
                .append(" nameAuthor: ").append(this.nameAuthor)
                .append(" content: ").append(this.content);
        return sb.toString();
    }

    public static List<Comment> findAll() {
        return null;
    }

    public static Comment findById() {
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

    //TODO equals haschode compare
}
