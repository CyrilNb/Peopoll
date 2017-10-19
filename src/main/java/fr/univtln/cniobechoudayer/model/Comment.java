package fr.univtln.cniobechoudayer.model;

import java.util.Date;

/**
 * Class which represents a comment
 * Created by Cyril on 16/10/2017.
 */
public class Comment {

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

    //TODO equals haschode compare
}
