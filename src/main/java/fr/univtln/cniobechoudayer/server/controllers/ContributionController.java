package fr.univtln.cniobechoudayer.server.controllers;

import fr.univtln.cniobechoudayer.model.EntityManager;
import fr.univtln.cniobechoudayer.model.entities.Contribution;
import fr.univtln.cniobechoudayer.server.exceptions.PersistanceException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContributionController {

    public static int save(String nameContributor, int idPoll, int idChoice, boolean delete) throws SQLException, PersistanceException {
        int createdContrib = -1;
        EntityManager em = EntityManager.getInstance();
        Contribution contribution = Contribution.findByParams(nameContributor, idPoll, idChoice);
        if(nameContributor != null){
            if(delete){
                createdContrib = contribution.getIdContribution();
                System.out.println("Contribution to delete : " + contribution);
                em.remove(contribution);
            }else{
                if(contribution.getNameContributor() == null) {
                    int idCreatedContribution = em.persist(new Contribution(nameContributor, idPoll, idChoice));
                    System.out.println("Created contribution : " + idCreatedContribution);
                    createdContrib = idCreatedContribution;
                }
            }
        }
        em.dispose();
        return createdContrib;
    }

    public static List<Contribution> getAllContributionsByPoll(int idPoll) throws PersistanceException, SQLException {
        EntityManager em = EntityManager.getInstance();
        List<Contribution> listContribution = new ArrayList<>();
        listContribution = Contribution.findAllByIdPoll(idPoll);
        em.dispose();
        if(listContribution == null){
            return Collections.emptyList();
        }else{
            return listContribution;
        }
    }

    public static Contribution getContributionBy(int idContribution) throws PersistanceException {
        EntityManager em = EntityManager.getInstance();
        Contribution ct = Contribution.findById(idContribution);
        em.dispose();
        return ct;
    }


}
