package fr.univtln.cniobechoudayer.server.controllers;

import fr.univtln.cniobechoudayer.model.EntityManager;
import fr.univtln.cniobechoudayer.model.entities.Contribution;
import fr.univtln.cniobechoudayer.server.database.DatabaseManager;
import fr.univtln.cniobechoudayer.server.exceptions.PersistanceException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContributionController {

    public static void save(String nameContributor, int idPoll, int idChoice) throws SQLException, PersistanceException {

        EntityManager entityManager = EntityManager.getInstance();
        System.out.println("trying to save");
        System.out.println("cont created" + createContribution(nameContributor, idPoll, idChoice));
        int idContribution;
    /*
        try{
            idContribution = Contribution.findById();
        }catch (PersistanceException e){
            System.out.println(e);
        }
*/

        //TODO if null then insert in db otherwise just update using the returned idContribution
        //if(idContrib (==) null) -> Contribution.persist(new Contribution(nameContributor, idPoll, idChoice))
        // if(idContrib (!=) null -> Contribution.merge(idContribution, nameContributor, idPoll, idChoice)
    }

    public static int createContribution(String nameContribution, int idPoll, int idChoice) throws SQLException, PersistanceException {
        EntityManager em = EntityManager.getInstance();
        int idCreatedContribution = em.persist(new Contribution(nameContribution, idPoll, idChoice));
        em.dispose();
        return idCreatedContribution;
    }

    public static List<Contribution> getAllContributionsFor(int idPoll) throws PersistanceException, SQLException {
        EntityManager em = EntityManager.getInstance();
        List<Contribution> lc = new ArrayList<>();
        lc = Contribution.findAllByIdPoll(idPoll);
        if(lc == null){
            return Collections.emptyList();
        }else{
            return lc;
        }
    }


}
