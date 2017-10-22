package fr.univtln.cniobechoudayer.model;

import java.util.*;

/**
 * Created by Cyril on 16/10/2017.
 */
public class Poll {

    private int ID;

    private String title;

    private String location;

    private String description;

    private String nameCreator;

    private String emailCreator;

    private List<Choice> listChoices;

    /*
    Constructor using PollBuilder to create a new instance
     */
    public Poll(PollBuilder pb){
        this.ID = pb.ID;
        this.title = pb.title;
        this.location = pb.location;
        this.description = pb.description;
        this.nameCreator = pb.nameCreator;
        this.emailCreator = pb.emailCreator;
        this.listChoices = pb.listChoices;
    }
    /*
    Use of builder pattern to make poll creation clear and flexible
    as we have some optional parameters. Also to pass the poll object
    through the creation steps, and add param on the fly.
     */
    public static class PollBuilder {

        private int ID;

        private String title;

        private String location;

        private String description;

        private String nameCreator;

        private String emailCreator;

        private List<Choice> listChoices;

        /*
        As a PollBuilder has two mandatory params at step1
        when creating a PollBuilder, we assure to have those params
         */
        public PollBuilder(int ID, String title){
            this.ID = ID;
            this.title = title;
        }

        /*
        We can also have a constructor params and add
        the mandatory params as if they were optionals
        using the two methods below
         */
        /*
        public PollBuilder setID(int ID){
            this.ID = ID;
            return this;
        }

        public PollBuilder setTitle(String title){
            this.title = title;
            return this;
        }
        */

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

        public PollBuilder setEmailCreator(String emailCreator){
            this.emailCreator = emailCreator;
            return this;
        }

        public PollBuilder setListChoices(List<Choice> listChoices){
            this.listChoices = listChoices;
            return this;
        }

        public Poll build(){
            return new Poll(this);
        }
    }
}
