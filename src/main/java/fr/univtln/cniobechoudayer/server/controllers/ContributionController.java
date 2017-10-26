package fr.univtln.cniobechoudayer.server.controllers;

public class ContributionController {

    public static void save(String nameContribution, int idPoll, int idChoice){
        //TODO check if the tuple exists in db (returns idContribution)
        //idContrib = Contribution.findById();
        //TODO if null then insert in db otherwise just update using the returned idContribution
        //if(idContrib (==) null) -> Contribution.persist(new Contribution(nameContribution, idPoll, idChoice))
        // if(idContrib (!=) null -> Contribution.merge(idContribution, nameContribution, idPoll, idChoice)
    }

}
