package fr.univtln.cniobechoudayer.model.entities;


import fr.univtln.cniobechoudayer.model.Entity;
import fr.univtln.cniobechoudayer.server.database.DatabaseManager;
import fr.univtln.cniobechoudayer.server.exceptions.PersistanceException;
import fr.univtln.cniobechoudayer.server.utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Class which represents a Poll
 * Created by Cyril on 16/10/2017.
 */
public class Poll implements Entity {

    /**private fields**/
    private int idPoll;
    private String managerCode;
    private String location;
    private String description;
    private String title;
    private String mailCreator;
    private String nameCreator;
    private boolean isLocked;
    private int nbMaxContributor;
    private int idFinalChoice;

    private List<Choice> choicesList;
    private List<Comment> commentsList;

    private static Logger logger = Logger.getLogger(Poll.class.getName());

    private static PreparedStatement findByID;
    private static PreparedStatement findAll;

    private static PreparedStatement setFinalChoice;

    //L'initialisation des preparedstatments.
    static {
        try {
            Connection connection = DatabaseManager.getConnection();
            findByID = connection.prepareStatement("select ID_POLL, TITLE, MANAGER_CODE, LOCATION, DESCRIPTION, MAIL_CREATOR, NAME_CREATOR, IS_LOCKED, NB_MAX_CONTRIBUTOR, ID_FINAL_CHOICE from PEOPOLL.POLLS where ID_POLL=?");
            findAll = connection.prepareStatement("select ID_POLL, TITLE from PEOPOLL.POLLS");
            setFinalChoice = connection.prepareStatement("UPDATE PEOPOLL.POLLS SET ID_FINAL_CHOICE = ? WHERE ID_POLL = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructor using PollBuilder to create a new instance
     * @param pb pollbuilder
     */
    private Poll(PollBuilder pb){
        this.idPoll = pb.idPoll;
        this.title = pb.title;
        this.location = pb.location;
        this.description = pb.description;
        this.nameCreator = pb.nameCreator;
        this.mailCreator = pb.mailCreator;
        this.choicesList = pb.choicesList;
        this.commentsList = pb.commentsList;
        this.nbMaxContributor = pb.nbMaxContributor;
        this.idFinalChoice = pb.idFinalChoice;
        this.managerCode = pb.managerCode;
        this.isLocked = pb.isLocked;
    }


    //Ce constructeur est utilisé en privé quand un poll est extrait de la BD
    private Poll(int ID, String title, String managerCode, String location, String description, String mailCreator, String nameCreator, boolean isLocked, int nbMaxContributor, int idFinalChoice) {
        this.idPoll = ID;
        this.title = title;
        this.managerCode = managerCode;
        this.location = location;
        this.description = description;
        this.mailCreator = mailCreator;
        this.nameCreator = nameCreator;
        this.isLocked = isLocked;
        this.nbMaxContributor = nbMaxContributor;
        this.idFinalChoice = idFinalChoice;
    }

    private Poll(int ID, String title){
        this.idPoll = ID;
        this.title = title;
    }

    /*
    Use of builder pattern to make poll creation clear and flexible
    as we have some optional parameters. Also to pass the poll object
    through the creation steps, and add param on the fly.
     */
    public static class PollBuilder {
        private int idPoll;
        private String managerCode;
        private String location;
        private String description;
        private String title;
        private String mailCreator;
        private String nameCreator;
        private boolean isLocked;
        private int nbMaxContributor;
        private int idFinalChoice;
        private List<Choice> choicesList;
        private List<Comment> commentsList;

        /*
        As a PollBuilder has two mandatory params at step1
        when creating a PollBuilder, we assure to have those params
         */
        public PollBuilder(String title,String nameCreator, String mailCreator){
            this.title = title;
            this.nameCreator = nameCreator;
            this.mailCreator = mailCreator;
        }

        /* We can also have a constructor params and add
        the mandatory params as if they were optionals
        using the two methods below */


        public PollBuilder setID(int idPoll){
            this.idPoll = idPoll;
            return this;
        }

        public PollBuilder setTitle(String title){
            this.title = title;
            return this;
        }


        public PollBuilder setLocation(String location){
            this.location = location;
            return this;
        }

        public PollBuilder setDescription(String description){
            this.description = description;
            return this;
        }

        public PollBuilder setNameCreator(String nameCreator){
            this.nameCreator = nameCreator;
            return this;
        }

        public PollBuilder setMailCreator(String mailCreator){
            this.mailCreator = mailCreator;
            return this;
        }

        public PollBuilder setChoicesList(List<Choice> choicesList){
            this.choicesList = choicesList;
            return this;
        }

        public PollBuilder setCommentsList(List<Comment> commentsList){
            this.commentsList = commentsList;
            return this;
        }

        public PollBuilder setManagerCode(String managerCode) {
            this.managerCode = managerCode;
            return this;
        }

        public PollBuilder setNbMaxContributor(int nbMaxContributor) {
            this.nbMaxContributor = nbMaxContributor;
            return this;
        }

        public PollBuilder setIdFinalChoice(int idFinalChoice){
            this.idFinalChoice = idFinalChoice;
            return this;
        }

        public PollBuilder setIsLocked(boolean isLocked){
            this.isLocked = isLocked;
            return this;
        }

        public Poll build(){
            return new Poll(this);
        }
    }


    /**
     * Gets managerCode.
     *
     * @return Value of managerCode.
     */
    public String getManagerCode() {
        return managerCode;
    }

    /**
     * Gets mailCreator.
     *
     * @return Value of mailCreator.
     */
    public String getMailCreator() {
        return mailCreator;
    }

    /**
     * Gets nbMaxContributor.
     *
     * @return Value of nbMaxContributor.
     */
    public int getNbMaxContributor() {
        return nbMaxContributor;
    }

    /**
     * Sets new isLocked.
     *
     * @param isLocked New value of isLocked.
     */
    public void setIsLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    /**
     * Gets location.
     *
     * @return Value of location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets new description.
     *
     * @param description New value of description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets new private fields.
     *
     * @param idPoll New value of private fields.
     */
    public void setIdPoll(int idPoll) {
        this.idPoll = idPoll;
    }

    /**
     * Gets title.
     *
     * @return Value of title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets description.
     *
     * @return Value of description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets new mailCreator.
     *
     * @param mailCreator New value of mailCreator.
     */
    public void setMailCreator(String mailCreator) {
        this.mailCreator = mailCreator;
    }

    /**
     * Sets new title.
     *
     * @param title New value of title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets isLocked.
     *
     * @return Value of isLocked.
     */
    public boolean isIsLocked() {
        return isLocked;
    }

    /**
     * Sets new managerCode.
     *
     * @param managerCode New value of managerCode.
     */
    public void setManagerCode(String managerCode) {
        this.managerCode = managerCode;
    }

    /**
     * Gets private fields.
     *
     * @return Value of private fields.
     */
    public int getIdPoll() {
        return idPoll;
    }

    /**
     * Sets new location.
     *
     * @param location New value of location.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    public int getIdFinalChoice() {
        return idFinalChoice;
    }

    public void setIdFinalChoice(int idFinalChoice) {
        this.idFinalChoice = idFinalChoice;
    }

    /**
     * Sets new nbMaxContributor.
     *
     * @param nbMaxContributor New value of nbMaxContributor.
     */
    public void setNbMaxContributor(int nbMaxContributor) {
        this.nbMaxContributor = nbMaxContributor;
    }

    /**
     * Gets nameCreator.
     *
     * @return Value of nameCreator.
     */
    public String getNameCreator() {
        return nameCreator;
    }

    /**
     * Sets new nameCreator.
     *
     * @param nameCreator New value of nameCreator.
     */
    public void setNameCreator(String nameCreator) {
        this.nameCreator = nameCreator;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("idPoll: ").append(this.idPoll)
                .append(" managerCode: ").append(this.managerCode)
                .append(" title: ").append(this.title)
                .append(" mailCreator: ").append(this.mailCreator)
                .append(" location: ").append(this.location)
                .append(" description: ").append(this.description)
                .append(" nameCreator: ").append(this.nameCreator)
                .append(" isLocked: ").append(this.isLocked)
                .append(" nbmax: ").append(this.nbMaxContributor)
                .append("id Final Choice: ").append(this.idFinalChoice);
        return sb.toString();
    }

    /**
     * Sets new choicesList.
     *
     * @param choicesList New value of choiceList.
     */
    public void setChoicesList(List<Choice> choicesList) {
        this.choicesList = choicesList;
    }

    /**
     * Gets choicesList.
     *
     * @return Value of choicesList.
     */
    public List<Choice> getChoicesList() {
        return choicesList;
    }

    /**
     * Gets commentsList.
     *
     * @return Value of commentsList.
     */
    public List<Comment> getCommentsList() {
        return commentsList;
    }

    /**
     * Sets new commentsList.
     *
     * @param commentsList New value of commentsList.
     */
    public void setCommentsList(List<Comment> commentsList) {
        this.commentsList = commentsList;
    }

    //Cette méthode doit être appelée à la fin de l'application pour libérer les connexions
    //des preparedstatments.
    public static void dispose() throws PersistanceException {
        try {
            findByID.close();
            findAll.close();
        } catch (SQLException e) {
            throw new PersistanceException(e);
        }
    }

    //method to create a poll object from the result of the database
    private static Poll createFromResultSet(ResultSet result) throws SQLException {
        return new Poll(result.getInt("ID_POLL"),result.getString("TITLE"),result.getString("MANAGER_CODE"),result.getString("LOCATION"),result.getString("DESCRIPTION"),result.getString("MAIL_CREATOR"),result.getString("NAME_CREATOR"),result.getBoolean("IS_LOCKED"),result.getInt("NB_MAX_CONTRIBUTOR"),result.getInt("ID_FINAL_CHOICE"));

    }

    /**
     * Method to retrieve all polls from the db
     * @return list of all polls from the db
     * @throws PersistanceException
     */
    public static List<Poll> findAll() throws PersistanceException {
        try {
            ResultSet result = findAll.executeQuery();
            List<Poll> resultPolls = new ArrayList<>();
            while (result.next()) {
                Poll poll = createFromResultSet(result);
                logger.info("find poll in the db: " + poll);
                resultPolls.add(poll);
            }
            return resultPolls;
        }catch (SQLException e) {
            throw new PersistanceException(e);
        }
    }

    /**
     * Method to retrieve a specific poll from the db
     * @param idPoll to retrieve
     * @return the matching poll containing the idPoll specified
     * @throws PersistanceException
     */
    public static Poll findById(int idPoll) throws PersistanceException{
        try{
            Poll poll;
            findByID.setInt(1,idPoll);
            ResultSet resultSet = findByID.executeQuery();
            if(resultSet.next()){
                poll = createFromResultSet(resultSet);
                logger.finest("find poll in the db: " + poll);
                return poll;
            }else {
                return null;
            }
        }catch (Exception e) {
            throw new PersistanceException(e);
        }
    }

    public void setFinalChoice() throws PersistanceException {
        try{
            setFinalChoice.setInt(1, idFinalChoice);
            System.out.println("date : " + idFinalChoice);
            setFinalChoice.setInt(2, idPoll);
            setFinalChoice.executeUpdate();
        }catch (Exception e) {
            System.out.println(e);
            throw new PersistanceException(e);
        }
    }

    /**
     * Persists in the database a new Poll
     * The key of the poll is auto-generated by the databse
     * After the insert, this key is retrieved and added to the poll as its idPoll
     */
    @Override
    public int persist(Connection connection) throws PersistanceException {
        int returnedID;

        try{
            Statement statement = connection.createStatement();
            System.out.println("INSERT INTO PEOPOLL.POLLS(MANAGER_CODE,LOCATION,DESCRIPTION,TITLE,MAIL_CREATOR,NAME_CREATOR,IS_LOCKED,NB_MAX_CONTRIBUTOR) VALUES ('" + managerCode + "','" + location + "','" + description + "','" + title + "','" + mailCreator + "','" + nameCreator + "','" + isLocked + "','" + nbMaxContributor + "')");
            statement.executeUpdate("INSERT INTO PEOPOLL.POLLS(MANAGER_CODE,LOCATION,DESCRIPTION,TITLE,MAIL_CREATOR,NAME_CREATOR,IS_LOCKED,NB_MAX_CONTRIBUTOR) VALUES ('" + managerCode + "','" + location + "','" + description + "','" + title + "','" + mailCreator + "','" + nameCreator + "','" + isLocked + "','" + nbMaxContributor + "')", Statement.RETURN_GENERATED_KEYS);
            //statement.executeUpdate("INSERT INTO PEOPOLL.POLLS(MANAGER_CODE,LOCATION,DESCRIPTION,TITLE,MAIL_CREATOR,NAME_CREATOR,IS_LOCKED,NB_MAX_CONTRIBUTOR,FINAL_DATE) VALUES ('MANAGE2','VICHY','DESCIRPTION','TITRE','cyril@gmail.com','cyril',0,4,'2017-10-23')", Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                returnedID = resultSet.getInt(1);
                setIdPoll(returnedID);
                logger.info("creation of the poll: " + this);
                return returnedID;
            } else {
                throw  new PersistanceException("The key of the poll could not be found.");
            }
        }catch (SQLException e){
            throw new PersistanceException(e);
        }
    }

    /**
     * Updates the database from the instance data
     * @param connection
     * @throws PersistanceException
     */
    @Override
    public void merge(Connection connection) throws PersistanceException {
        try {
            String query = "UPDATE PEOPOLL.POLLS SET TITLE='"+title+"', LOCATION='"+location+"', DESCRIPTION='"+description+"', IS_LOCKED='"+isLocked+"' WHERE ID_POLL='"+idPoll+"';";
            System.out.println(query);
            connection.createStatement().executeUpdate(query);
            logger.info("merge of the poll : " + this);
        } catch (SQLException e) {
            throw new PersistanceException(e);
        }
    }



    /**
     * Update the instance from the database data
     * @param connection
     * @throws PersistanceException
     */
    @Override
    public void update(Connection connection) throws PersistanceException {
        Poll poll = findById(this.idPoll);
        this.title = poll.title;
        this.mailCreator = poll.mailCreator;
        this.nameCreator = poll.nameCreator;
        logger.info("update of the poll: " + this);
    }

    @Override
    public void remove(Connection connection) throws PersistanceException {
        try {
            connection.createStatement().executeUpdate("DELETE FROM PEOPOLL.POLLS WHERE ID_POLL=\'" + idPoll + "\'");
            logger.info("deletion of the poll: " + this);
        } catch (SQLException e) {
            throw new PersistanceException(e);
        }
    }

}

//TODO equals haschode compare
