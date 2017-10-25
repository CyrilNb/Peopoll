package fr.univtln.cniobechoudayer.client.views;

import fr.univtln.cniobechoudayer.model.entities.Poll;
import fr.univtln.cniobechoudayer.server.controllers.PollController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PollViewController implements Initializable {

    private Poll pollToDisplay;

    private PollController pollController = new PollController();


    @FXML
    private AnchorPane rootView;


    public PollViewController(Poll pollToDisplay){
        this.pollToDisplay = pollToDisplay;
    }
    /**
    Method to go back to homeview
     */
    @FXML
    private void goBackHome() throws IOException {
        loadScreen("HomeView");
    }

    /**
    Method to define the final date / choice
     */
    @FXML
    private void defineFinalDate(){

    }

    /**
    Save the current state of the poll
     */
    @FXML
    private void savePoll(){

    }

    /**
    Lock the poll (disable grid)
     */
    @FXML
    private void lockPoll(){

    }

    /**
    Get management access (left bar)
     */
    @FXML
    private void accessAsOrga(){

    }

    /**
     * Set the view of poll
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(pollToDisplay.isIsLocked()){
            //TODO change image
        }
    }

    /**
    Method to load any view from PollViewController
     */
    @FXML
    public void loadScreen(String resource) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + resource + ".fxml"));
        System.out.println("Loading : /fxml/" + resource + ".fxml");
        AnchorPane ap = loader.load();
        rootView.getChildren().setAll(ap);
    }
}
