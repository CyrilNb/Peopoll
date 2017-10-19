package fr.univtln.cniobechoudayer.model;

/**
 * Class which represent a contribution made by someone
 * Created by Cyril on 16/10/2017.
 */
public class Contribution {
    /** private fields**/
    private int idContribution;
    private String nameContributor;

    /** Constructor **/
    public Contribution(String nameContributor) {
        this.nameContributor = nameContributor;
    }

    /** Constructor **/
    public Contribution(int idContribution, String nameContributor) {
        this.idContribution = idContribution;
        this.nameContributor = nameContributor;
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


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("idContribution: ").append(this.idContribution)
                .append(" nameContributor: ").append(this.nameContributor);
        return sb.toString();
    }
}

//TODO equals haschode compare
