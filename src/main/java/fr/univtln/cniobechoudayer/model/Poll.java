package fr.univtln.cniobechoudayer.model;

import java.util.Date;

/**
 * Class which represents a Poll
 * Created by Cyril on 16/10/2017.
 */
public class Poll {
    /**private fields**/
    private int shareCode;
    private String managerCode;
    private String location;
    private String description;
    private String title;
    private String managerMail;
    private boolean isLocked = false;
    private int nbMaxContributor;
    private Date finalDate;

    /**
     * Default constructor
     */
    public Poll() {
    }

    /**
     * Constructor
     * @param shareCode
     * @param managerCode
     * @param location
     * @param description
     * @param title
     * @param managerMail
     * @param isLocked
     * @param nbMaxContributor
     * @param finalDate
     */
    public Poll(int shareCode, String managerCode, String location, String description, String title, String managerMail, boolean isLocked, int nbMaxContributor, Date finalDate) {
        this.shareCode = shareCode;
        this.managerCode = managerCode;
        this.location = location;
        this.description = description;
        this.title = title;
        this.managerMail = managerMail;
        this.isLocked = isLocked;
        this.nbMaxContributor = nbMaxContributor;
        this.finalDate = finalDate;
    }

    /**
     * Constructor
     * @param shareCode
     * @param managerCode
     * @param title
     * @param managerMail
     */
    public Poll(int shareCode, String managerCode, String title, String managerMail) {
        this.shareCode = shareCode;
        this.managerCode = managerCode;
        this.title = title;
        this.managerMail = managerMail;
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
     * Gets managerMail.
     *
     * @return Value of managerMail.
     */
    public String getManagerMail() {
        return managerMail;
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
     * @param shareCode New value of private fields.
     */
    public void setShareCode(int shareCode) {
        this.shareCode = shareCode;
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
     * Gets finalDate.
     *
     * @return Value of finalDate.
     */
    public Date getFinalDate() {
        return finalDate;
    }

    /**
     * Sets new managerMail.
     *
     * @param managerMail New value of managerMail.
     */
    public void setManagerMail(String managerMail) {
        this.managerMail = managerMail;
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
    public int getShareCode() {
        return shareCode;
    }

    /**
     * Sets new location.
     *
     * @param location New value of location.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Sets new finalDate.
     *
     * @param finalDate New value of finalDate.
     */
    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }

    /**
     * Sets new nbMaxContributor.
     *
     * @param nbMaxContributor New value of nbMaxContributor.
     */
    public void setNbMaxContributor(int nbMaxContributor) {
        this.nbMaxContributor = nbMaxContributor;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("shareCode: ").append(this.shareCode)
                .append(" managerCode: ").append(this.managerCode)
                .append(" title; ").append(this.title)
                .append(" managerMail: ").append(this.managerMail);
        return sb.toString();
    }
}

//TODO equals haschode compare
