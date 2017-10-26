package fr.univtln.cniobechoudayer.model.entities;

import fr.univtln.cniobechoudayer.model.Entity;
import fr.univtln.cniobechoudayer.server.database.DatabaseManager;
import fr.univtln.cniobechoudayer.server.exceptions.PersistanceException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Class which represent a contribution made by someone
 * Created by Cyril on 16/10/2017.
 */
public class Contribution implements Entity {
    /** private fields**/
    private int idContribution;
    private String nameContributor;
    private int idPoll;
    private int idChoice;

    private static Logger logger = Logger.getLogger(Contribution.class.getName());

    private static PreparedStatement findById;
    private static PreparedStatement findAll;
    private static PreparedStatement findAllByIdPoll;

    //Init prepared statements
    static{
        try {
            Connection connection = DatabaseManager.getConnection();
            findById = connection.prepareStatement("SELECT * FROM PEOPOLL.CONTRIBUTIONS WHERE ID_CONTRIBUTION=?");
            findAll = connection.prepareStatement("SELECT * FROM PEOPOLL.CONTRIBUTIONS ");
            findAllByIdPoll = connection.prepareStatement("SELECT * FROM PEOPOLL.CONTRIBUTIONS WHERE IDP=?");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    /** Constructor **/
    public Contribution(String nameContributor) {
        this.nameContributor = nameContributor;
    }

    /** Constructor **/
    public Contribution(int idContribution, String nameContributor) {
        this.idContribution = idContribution;
        this.nameContributor = nameContributor;
    }

    /** Constructor **/
    public Contribution(String nameContributor, int idPoll, int idChoice) {
        this.nameContributor = nameContributor;
        this.idPoll = idPoll;
        this.idChoice = idChoice;
    }

    /** Constructor **/
    public Contribution(int idContribution, String nameContributor, int idPoll, int idChoice) {
        this.idContribution = idContribution;
        this.nameContributor = nameContributor;
        this.idPoll = idPoll;
        this.idChoice = idChoice;
    }

    /** Default constructor **/
    public Contribution(){

    }

    /**
     * Gets nameContributor.
     *
     * @return Value of nameContributor.
     */
    public String getNameContributor() {
        return nameContributor;
    }

    /**
     * Sets new nameContributor.
     *
     * @param nameContributor New value of nameContributor.
     */
    public void setNameContributor(String nameContributor) {
        this.nameContributor = nameContributor;
    }

    /**
     * Sets new private fields.
     *
     * @param idContribution New value of private fields.
     */
    public void setIdContribution(int idContribution) {
        this.idContribution = idContribution;
    }

    /**
     * Gets private fields.
     *
     * @return Value of private fields.
     */
    public int getIdContribution() {
        return idContribution;
    }

    public int getIdPoll() {
        return idPoll;
    }

    public void setIdPoll(int idPoll) {
        this.idPoll = idPoll;
    }

    public int getIdChoice() {
        return idChoice;
    }

    public void setIdChoice(int idChoice) {
        this.idChoice = idChoice;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("idContribution: ").append(this.idContribution)
                .append(" nameContributor: ").append(this.nameContributor);
        return sb.toString();
    }

    public static Contribution findById(int idContribution) throws PersistanceException {
        try{
            Contribution cont;
            findById.setInt(1, idContribution);
            ResultSet rs = findById.executeQuery();
            if(rs.next()){
                cont = createFromResultSet(rs);
                return cont;
            }else{
                throw new PersistanceException("contribution not found");
            }
        } catch (SQLException e) {
            throw new PersistanceException(e);
        }
    }

    public static List<Contribution> findAll() throws PersistanceException {
        try{
            List<Contribution> lc = new ArrayList<>();
            ResultSet rs = findAll.executeQuery();
            while(rs.next()){
                lc.add(createFromResultSet(rs));
            }
            return lc;
        } catch (SQLException e) {
            throw new PersistanceException(e);
        }
    }

    public static List<Contribution> findAllByIdPoll(int idPoll) throws PersistanceException, SQLException {
        try{
            List<Contribution> lc = new ArrayList<>();
            findAllByIdPoll.setInt(1, idPoll);
            ResultSet rs = findAllByIdPoll.executeQuery();
            while (rs.next()){
                lc.add(createFromResultSet(rs));
            }
            return lc;
        }catch (SQLException e){
            throw new PersistanceException(e);
        }
    }

    private static Contribution createFromResultSet(ResultSet result) throws SQLException {
        return new Contribution(result.getInt("ID_CONTRIBUTION"), result.getString("NAME_CONTRIBUTOR"), result.getInt("IDP"), result.getInt("IDC"));

    }


    @Override
    public int persist(Connection connection) throws PersistanceException {
        int idContributionPersisted;

        try{
            Statement st = connection.createStatement();
            String query = "INSERT INTO PEOPOLL.CONTRIBUTIONS(NAME_CONTRIBUTOR, IDP, IDC) VALUES ('"+ this.nameContributor +"','" + this.idPoll + "','" + this.idChoice +"')";
            System.out.println(query);
            st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = st.getGeneratedKeys();
            if(rs.next()){
                idContributionPersisted = rs.getInt(1);
                setIdContribution(idContributionPersisted);
                logger.info("Contribution created " + this);
                return idContributionPersisted;
            }else{
                throw new PersistanceException("Contribution non created");
            }

        }catch(Exception e){
            throw new PersistanceException(e);
        }

    }

    @Override
    public void merge(Connection connection) throws PersistanceException {
        //No need
    }

    @Override
    public void update(Connection connection) throws PersistanceException {
        //No need
    }

    @Override
    public void remove(Connection connection) throws PersistanceException {
        try{
            String query = "DELETE FROM PEOPOLL.CONTRIBUTION WHERE ID_CONTRIBUTION=\'" + idContribution + "\'";
            System.out.println(query);
            connection.createStatement().executeUpdate(query);
            logger.info(this + " delete");
        }catch (SQLException e){
            throw new PersistanceException(e);
        }
    }
}

//TODO equals haschode compare
