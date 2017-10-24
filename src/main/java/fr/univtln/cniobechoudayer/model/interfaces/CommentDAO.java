package fr.univtln.cniobechoudayer.model.interfaces;

import fr.univtln.cniobechoudayer.model.entities.Comment;

import java.util.List;

/**
 * Interface to provide CRUD methods to deal with database
 * Created by Cyril on 24/10/2017.
 */
public interface CommentDAO {
    List<Comment> findAll();
    Comment findById();
    boolean insertComment(Comment comment);
    boolean updateComment(Comment comment);
    boolean deleteComment(Comment comment);
}
