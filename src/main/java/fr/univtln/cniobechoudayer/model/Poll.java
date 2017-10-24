
package fr.univtln.cniobechoudayer.model;

import java.util.Date;
import java.util.List;

/**
 * Class which represents a Poll
 * Created by Cyril on 16/10/2017.
 */
public class Poll {

    /**private fields**/
    private int idPoll;
    private String managerCode;
    private String location;
    private String description;
    private String title;
    private String mailCreator;
    private String nameCreator;
    private boolean isLocked = false;
    private int nbMaxContributor;
    private Date finalDate;

    private List<Choice> choicesList;
    private List<Comment> commentsList;

    /**
     * Constructor using PollBuilder to create a new instance
     * @param pb pollbuilder
     */
    public Poll(PollBuilder pb){
        this.idPoll = pb.idPoll;
        this.title = pb.title;
        this.location = pb.location;
        this.description = pb.description;
        this.nameCreator = pb.nameCreator;
        this.mailCreator = pb.mailCreator;
        this.choicesList = pb.choicesList;
        this.commentsList = pb.commentsList;
        this.nbMaxContributor = pb.nbMaxContributor;
        this.finalDate = pb.finalDate;
        this.managerCode = pb.managerCode;
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
        private boolean isLocked = false;
        private int nbMaxContributor;
        private Date finalDate;
        private List<Choice> choicesList;
        private List<Comment> commentsList;

        /*
        As a PollBuilder has two mandatory params at step1
        when creating a PollBuilder, we assure to have those params
         */
        public PollBuilder(int idPoll, String title){
            this.idPoll = idPoll;
            this.title = title;
        }

        /* We can also have a constructor params and add
        the mandatory params as if they were optionals
        using the two methods below */

        /*
        public PollBuilder setID(int idPoll){
            this.idPoll = idPoll;
            return this;
        }

        public PollBuilder setTitle(String title){
            this.title = title;
            return this;
        }*/


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

        public PollBuilder setFinalDate(Date finalDate){
            this.finalDate = finalDate;
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
     * Gets finalDate.
     *
     * @return Value of finalDate.
     */
    public Date getFinalDate() {
        return finalDate;
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
                .append(" title; ").append(this.title)
                .append(" mailCreator: ").append(this.mailCreator);
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
}

//TODO equals haschode compare