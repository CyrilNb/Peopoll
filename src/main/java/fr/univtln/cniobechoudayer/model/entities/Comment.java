package fr.univtln.cniobechoudayer.model.entities;

import fr.univtln.cniobechoudayer.model.interfaces.CommentDAO;

import java.util.Date;
import java.util.List;

/**
 * Class which represents a comment
 * Created by Cyril on 16/10/2017.
 */
public class Comment implements CommentDAO{

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

    @Override
    public List<Comment> findAll() {
        return null;
    }

    @Override
    public Comment findById() {
        return null;
    }

    @Override
    public boolean insertComment(Comment comment) {
        return false;
    }

    @Override
    public boolean updateComment(Comment comment) {
        return false;
    }

    @Override
    public boolean deleteComment(Comment comment) {
        return false;
    }

    //TODO equals haschode compare
}
