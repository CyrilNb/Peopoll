package fr.univtln.cniobechoudayer.server.controllers;

import fr.univtln.cniobechoudayer.model.EntityManager;
import fr.univtln.cniobechoudayer.model.entities.Comment;
import fr.univtln.cniobechoudayer.server.exceptions.PersistanceException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class CommentController {
    public static List<Comment> getAllCommentsByPoll(int idPoll) throws PersistanceException {
        EntityManager entityManager = EntityManager.getInstance();
        List<Comment> listReturnedComments;
        try{
            listReturnedComments = Comment.findAll(idPoll);
            if(listReturnedComments != null){
                return listReturnedComments;
            }else {
                return Collections.emptyList();
            }
        }catch (PersistanceException e){
            e.getException().printStackTrace();
            return null;
        }finally {
            entityManager.dispose();
        }
    }

    public static void createCommentInDB(String nameAuthor, String content, Date dateComment, int idPoll) throws PersistanceException{
        EntityManager entityManager = EntityManager.getInstance();
        Comment comment = new Comment(nameAuthor,content,dateComment,idPoll);
        entityManager.persist(comment);
        entityManager.dispose();
    }

    public static void removeCommentInDb(Comment comment) throws PersistanceException{
        EntityManager entityManager = EntityManager.getInstance();
        entityManager.remove(comment);
        entityManager.dispose();
    }
}
