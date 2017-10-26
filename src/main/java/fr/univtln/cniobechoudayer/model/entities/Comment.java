package fr.univtln.cniobechoudayer.model.entities;

import fr.univtln.cniobechoudayer.model.Entity;
import fr.univtln.cniobechoudayer.server.database.DatabaseManager;
import fr.univtln.cniobechoudayer.server.exceptions.PersistanceException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Class which represents a comment
 * Created by Cyril on 16/10/2017.
 */
public class Comment implements Entity{

    /* private fields */
    private int idComment;
    private int idPoll; //foreign key
    private String nameAuthor;
    private String content;
    private Date dateComment;

    private static Logger logger = Logger.getLogger(Poll.class.getName());

    private static PreparedStatement findByID;
    private static PreparedStatement findAllByIdPoll;

    //L'initialisation des preparedstatments.
    static {
        try {
            Connection connection = DatabaseManager.getConnection();
            findByID = connection.prepareStatement("select ID_COMMENT,NAME_AUTHOR,CONTENT,DATE_COMMENT,ID_POLL from PEOPOLL.COMMENTS WHERE ID_COMMENT= ?");
            findAllByIdPoll = connection.prepareStatement("select ID_COMMENT,NAME_AUTHOR,CONTENT,DATE_COMMENT,ID_POLL from PEOPOLL.COMMENTS WHERE ID_POLL= ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructor
     * @param nameAuthor
     * @param content
     * @param dateComment
     * @param idPoll
     */
    public Comment(String nameAuthor, String content, Date dateComment,int idPoll) {
        this.nameAuthor = nameAuthor;
        this.content = content;
        this.dateComment = dateComment;
        this.idPoll = idPoll;
    }


    /**
     * Constructor when a choice is retrieved from the db
     * @param idComment
     * @param nameAuthor
     * @param content
     * @param dateComment
     * @param idPoll
     */
    public Comment(int idComment, String nameAuthor, String content, Date dateComment,int idPoll) {
        this.idComment = idComment;
        this.nameAuthor = nameAuthor;
        this.content = content;
        this.dateComment = dateComment;
        this.idPoll = idPoll;
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
                .append(" content: ").append(this.content)
                .append(" idPoll: ").append(this.idPoll);
        return sb.toString();
    }

    public static List<Comment> findAll(int idPoll) throws PersistanceException{
        try{
            findAllByIdPoll.setInt(1,idPoll);
            List<Comment> resultsComment = new ArrayList<>();
            ResultSet resultSet = findAllByIdPoll.executeQuery();
            while(resultSet.next()){
                Comment comment = createFromResultSet(resultSet);
                System.out.println("comment créée from db: " + comment);
                logger.finest("find comment in the db: " + comment);
                resultsComment.add(comment);
            }
            return resultsComment;
        }catch (SQLException e) {
            throw new PersistanceException(e);
        }
    }

    public static Comment findById(int idComment) throws PersistanceException {
        try{
            Comment comment;
            findByID.setInt(1,idComment);
            ResultSet resultSet = findByID.executeQuery();
            if(resultSet.next()){
                comment = createFromResultSet(resultSet);
                logger.finest("find comment in the db: " + comment);
                return comment;
            }else{
                throw new PersistanceException("Comment " + idComment + "not found in db");
            }
        }catch (SQLException e){
            throw new PersistanceException(e);
        }
    }

    private static Comment createFromResultSet(ResultSet resultSet) throws SQLException{
        return new Comment(resultSet.getInt("ID_COMMENT"),resultSet.getString("NAME_AUTHOR"),resultSet.getString("CONTENT"),resultSet.getDate("DATE_COMMENT"),resultSet.getInt("ID_POLL"));

    }

    //Cette méthode doit être appelée à la fin de l'application pour libérer les connexions
    //des preparedstatments.
    public static void dispose() throws PersistanceException {
        try {
            findAllByIdPoll.close();
            findByID.close();
        } catch (SQLException e) {
            throw new PersistanceException(e);
        }
    }


    @Override
    public int persist(Connection connection) throws PersistanceException {
        int returnedIDComment;
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO PEOPOLL.COMMENTS(NAME_AUTHOR, CONTENT, DATE_COMMENT, ID_POLL) VALUES ('" + nameAuthor + "','" + content + "','" + dateComment + "','" + idPoll +"')",Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys();
            if(resultSet.next()){
                returnedIDComment = resultSet.getInt(1);
                setIdComment(returnedIDComment);
                logger.info("creation of the comment: " + this);
                return returnedIDComment;
            }else {
                throw new PersistanceException("The key of the comment could not be found");
            }
        }catch (SQLException e){
            throw new PersistanceException(e);
        }
    }

    @Override
    public void merge(Connection connection) throws PersistanceException {

    }

    @Override
    public void update(Connection connection) throws PersistanceException {

    }

    @Override
    public void remove(Connection connection) throws PersistanceException {
        try {
            connection.createStatement().executeUpdate("DELETE FROM PEOPOLL.COMMENTS WHERE ID_COMMENT=\'" + idComment + "\'");
            logger.info("deletion of the comment: " + this);
        } catch (SQLException e) {
            throw new PersistanceException(e);
        }
    }

    //TODO equals haschode compare

    /**
     * Gets idPoll.
     *
     * @return Value of idPoll.
     */
    public int getIdPoll() {
        return idPoll;
    }

    /**
     * Sets new idPoll.
     *
     * @param idPoll New value of idPoll.
     */
    public void setIdPoll(int idPoll) {
        this.idPoll = idPoll;
    }

    /**
     * Sets new idComment.
     *
     * @param idComment New value of idComment.
     */
    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }
}
