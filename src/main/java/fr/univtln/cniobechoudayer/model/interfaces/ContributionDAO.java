package fr.univtln.cniobechoudayer.model.interfaces;

import fr.univtln.cniobechoudayer.model.entities.Contribution;

import java.util.List;

/**
 * Interface to provide CRUD methods to deal with database
 * Created by Cyril on 24/10/2017.
 */
public interface ContributionDAO {
    List<Contribution> findAll();
    Contribution findById();
    boolean insertContribution(Contribution contribution);
    boolean updateContribution(Contribution contribution);
    boolean deleteContribution(Contribution contribution);
}
