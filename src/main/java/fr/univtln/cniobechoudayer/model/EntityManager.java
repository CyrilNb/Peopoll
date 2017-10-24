package fr.univtln.cniobechoudayer.model;

import fr.univtln.cniobechoudayer.server.database.DatabaseManager;
import fr.univtln.cniobechoudayer.server.exceptions.PersistanceException;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Observable;

/**
 * Class which abstracts
 * Created by Cyril on 24/10/2017.
 */
public class EntityManager extends Observable{
    private Connection connection;

    private EntityManager() {
        try {
            //L'entity manager utilise une seule connexion
            connection = DatabaseManager.getConnection();
            //Le modele observe tout les entity manager
            //addObserver(ModelBibliotheque.getInstance());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static EntityManager getInstance() {
        return new EntityManager();
    }

    public void dispose() throws PersistanceException {
        if (connection==null) throw new PersistanceException("Entity manager without connection.");
        DatabaseManager.releaseConnection(connection);
        //deleteObserver(ModelBibliotheque.getInstance());
    }

    public void persist(Entity entity) throws PersistanceException {
        if (connection==null) throw new PersistanceException("Entity manager without connection.");
        entity.persist(connection);
        setChanged();
        notifyObservers(entity.getClass());
    }

    public void merge(Entity entity) throws PersistanceException {
        if (connection==null) throw new PersistanceException("Entity manager without connection.");
        entity.merge(connection);
        setChanged();
        notifyObservers(entity.getClass());
    }

    public void remove(Entity entity) throws PersistanceException {
        if (connection==null) throw new PersistanceException("Entity manager without connection.");
        entity.remove(connection);
        setChanged();
        notifyObservers(entity.getClass());
    }

    public void commit() throws PersistanceException {
        if (connection==null) throw new PersistanceException("Entity manager without connection.");
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new PersistanceException(e);
        }
    }

    public void rollback() throws PersistanceException {
        if (connection==null) throw new PersistanceException("Entity manager without connection.");
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new PersistanceException(e);
        }
    }

    public boolean getAutoCommit() throws PersistanceException {
        if (connection==null) throw new PersistanceException("Entity manager without connection.");
        try {
            return connection.getAutoCommit();
        } catch (SQLException e) {
            throw new PersistanceException(e);
        }
    }

    public void setAutoCommit(boolean autoCommit) throws PersistanceException {
        if (connection==null) throw new PersistanceException("Entity manager without connection.");
        try {
            connection.setAutoCommit(autoCommit);
        } catch (SQLException e) {
            throw new PersistanceException(e);
        }
    }

    public DatabaseMetaData getMetaData() throws PersistanceException {
        if (connection==null) throw new PersistanceException("Entity manager without connection.");
        try {
            return connection.getMetaData();
        } catch (SQLException e) {
            throw new PersistanceException(e);
        }
    }
}
