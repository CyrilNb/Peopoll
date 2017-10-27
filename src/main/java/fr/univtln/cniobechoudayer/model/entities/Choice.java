package fr.univtln.cniobechoudayer.model.entities;


import fr.univtln.cniobechoudayer.model.Entity;
import fr.univtln.cniobechoudayer.server.database.DatabaseManager;
import fr.univtln.cniobechoudayer.server.exceptions.PersistanceException;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Class which represent a choice
 * Created by Cyril on 16/10/2017.
 */
public class Choice implements Entity{

    /* private fields */
    private int idChoice;
    private int idPoll; //foreign key
    private Date dateChoice;
    private int startingTime;
    private int endingTime;

    private static Logger logger = Logger.getLogger(Poll.class.getName());

    private static PreparedStatement findAllByIdPoll;
    private static PreparedStatement findById;

    //L'initialisation des preparedstatments.
    static {
        try {
            Connection connection = DatabaseManager.getConnection();
            findAllByIdPoll = connection.prepareStatement("select ID_CHOICE,DATE_CHOICE,STARTING_TIME,ENDING_TIME,ID_POLL from PEOPOLL.CHOICES where ID_POLL=?");
            findById = connection.prepareStatement("select ID_CHOICE,DATE_CHOICE,STARTING_TIME,ENDING_TIME,ID_POLL FROM PEOPOLL.CHOICES WHERE ID_CHOICE=?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructor with startingTime and endingTime
     * @param dateChoice
     * @param startingTime
     * @param endingTime
     */
    public Choice(Date dateChoice, int startingTime, int endingTime, int idPoll) {
        this.dateChoice = dateChoice;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.idPoll = idPoll;
    }

    /**
     * Constructor with startingTime and endingTime
     * @param dateChoice
     * @param startingTime
     * @param endingTime
     */
    public Choice(Date dateChoice, int startingTime, int endingTime) {
        this.dateChoice = dateChoice;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
    }

    /**
     * Constructor used when retrieve from the db with startingTime and endingTime
     * @param dateChoice
     * @param startingTime
     * @param endingTime
     */
    public Choice(int idChoice,Date dateChoice, int startingTime, int endingTime, int idPoll) {
        this.idChoice = idChoice;
        this.dateChoice = dateChoice;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.idPoll = idPoll;
    }

    /**
     * Constructor with if no time limits are set
     * @param dateChoice
     * @param idPoll
     * */
    public Choice(Date dateChoice,int idPoll) {
        this.dateChoice = dateChoice;
        this.idPoll = idPoll;
    }

    /**
     * Constructor used when retrieve from the db if no time limits are set
     * @param dateChoice
     * @param idPoll
     */
    public Choice(int idChoice, Date dateChoice,int idPoll) {
        this.idChoice = idChoice;
        this.dateChoice = dateChoice;
        this.idPoll = idPoll;
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
     * Gets endingTime.
     *
     * @return Value of endingTime.
     */
    public int getEndingTime() {
        return endingTime;
    }

    /**
     * Gets startingTime.
     *
     * @return Value of startingTime.
     */
    public int getStartingTime() {
        return startingTime;
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
     * Sets new endingTime.
     *
     * @param endingTime New value of endingTime.
     */
    public void setEndingTime(int endingTime) {
        this.endingTime = endingTime;
    }

    /**
     * Sets new startingTime.
     *
     * @param startingTime New value of startingTime.
     */
    public void setStartingTime(int startingTime) {
        this.startingTime = startingTime;
    }

    /**
     * Sets new idChoice
     * @param idChoice
     */
    public void setIdChoice(int idChoice) {
        this.idChoice = idChoice;
    }

    /**
     * Sets idPoll
     * @param idPoll
     */
    public void setIdPoll(int idPoll) {
        this.idPoll = idPoll;
    }

    /**
     * Gets idPoll.
     *
     * @return Value of idPoll.
     */
    public int getIdPoll() {
        return idPoll;
    }


    public String displayChoiceInIHM(){
        StringBuilder sb = new StringBuilder();
        sb.append(getFormattedDate(this.dateChoice) + " | ")
                .append(getFormattedDate(this.getStartingTime(), true))
                .append(" | " + getFormattedDate(this.getEndingTime(), false));
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("idChoice: ").append(this.idChoice)
                .append(" dateChoice: ").append(this.dateChoice)
                .append(" StartingTime: ").append(this.startingTime)
                .append(" EndingTime: ").append(this.endingTime)
                .append(" idPoll: ").append(this.idPoll);
        return sb.toString();
    }

    public static String getFormattedDate(int time, boolean isStart){
        String finalDate = "";
        System.out.println(time);
        if(time != -1 && time != 0) {
            String timeString = String.valueOf(time);
            System.out.println("timeString : " + timeString);
            if(timeString.toString().length() == 3){
                timeString = "0"+timeString;
                System.out.println("changed timeString " + timeString);
            }
            String part1 = timeString.substring(0, 2);
            System.out.println(part1);
            String part2 = timeString.substring(2,4);
            System.out.println(part2);
            if(isStart){
                finalDate = "Start : ";
            }else{
                finalDate = "End : ";
            }
            finalDate += part1+":"+part2;

        }else{
            finalDate = "";
        }

        return finalDate;
    }

    private String getFormattedDate(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = formatter.format(date);
        return formattedDate;
    }


    //method to create a choice object from the result of the database
    private static Choice createFromResultSet(ResultSet result) throws SQLException {

        int idChoice = result.getInt("ID_CHOICE");
        Date dateChoice= result.getDate("DATE_CHOICE");
        int startingTime = result.getInt("STARTING_TIME");

        if(result.wasNull()) {
            startingTime = -1;
        }

        int endingTime = result.getInt("ENDING_TIME");
        if(result.wasNull()){
            endingTime = -1;
        }
        int idPoll = result.getInt("ID_POLL");


        if(startingTime == -1){
            return new Choice(idChoice,dateChoice,idPoll);
        }else{
            return new Choice(idChoice,dateChoice,startingTime,endingTime,idPoll);
        }
    }

    //Cette méthode doit être appelée à la fin de l'application pour libérer les connexions
    //des preparedstatments.
    public static void dispose() throws PersistanceException {
        try {
            findAllByIdPoll.close();
            findById.close();
        } catch (SQLException e) {
            throw new PersistanceException(e);
        }
    }


    /**
     * Method to retrieve all choices contained in a specific poll
     * @return list of all choices from the db
     * @throws PersistanceException
     */
    public static List<Choice> findAllByIdPoll(int idPoll) throws PersistanceException {
        try{
            findAllByIdPoll.setInt(1,idPoll);
            List<Choice> resultsChoices = new ArrayList<>();
            ResultSet resultSet = findAllByIdPoll.executeQuery();
            while(resultSet.next()){
                Choice choice = createFromResultSet(resultSet);
                logger.finest("find choice in the db: " + choice);
                resultsChoices.add(choice);
            }
            return resultsChoices;
        }catch (SQLException e) {
            throw new PersistanceException(e);
        }
    }

    public static Choice findById(int idChoice) throws PersistanceException{
        try{
            Choice choice;
            findById.setInt(1,idChoice);
            ResultSet resultSet = findById.executeQuery();
            if(resultSet.next()){
                choice = createFromResultSet(resultSet);
                logger.finest("find choice in the db: " + choice);
                return choice;
            }else{
                throw new PersistanceException("Choice " + idChoice + "not found in db");
            }
        }catch (SQLException e){
            throw new PersistanceException(e);
        }
    }

    @Override
    public int persist(Connection connection) throws PersistanceException {
        int returnedID;
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO PEOPOLL.CHOICES(DATE_CHOICE,STARTING_TIME,ENDING_TIME,ID_POLL) VALUES ('" + dateChoice + "','" + startingTime + "','" + endingTime + "','" + idPoll + "')", Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                returnedID = resultSet.getInt(1);
                setIdChoice(returnedID);
                logger.info("creation of the choice: " + this);
                return returnedID;
            } else {
                throw  new PersistanceException("The key of the choice could not be found.");
            }
        }catch (SQLException e){
            throw new PersistanceException(e);
        }
    }

    @Override
    public void merge(Connection connection) throws PersistanceException {
        try {
            connection.createStatement().executeUpdate("UPDATE PEOPOLL.CHOICES SET STARTING_TIME=' "+startingTime+" ',ENDING_TIME='"+endingTime+" ' ;");
            logger.info("merge of the choice : " + this);
        } catch (SQLException e) {
            throw new PersistanceException(e);
        }
    }

    @Override
    public void update(Connection connection) throws PersistanceException {
        Choice choice = findById(this.idChoice);
        this.dateChoice = choice.dateChoice;
        this.startingTime = choice.startingTime;
        this.endingTime = choice.endingTime;
        this.idPoll = choice.idPoll;
        logger.info("update of the choice: " + this);
    }

    @Override
    public void remove(Connection connection) throws PersistanceException {
        try {
            connection.createStatement().executeUpdate("DELETE FROM PEOPOLL.CHOICES WHERE ID_CHOICE=\'" + idChoice + "\'");
            logger.info("deletion of the choice: " + this);
        } catch (SQLException e) {
            throw new PersistanceException(e);
        }
    }
}


//TODO equals haschode compare
