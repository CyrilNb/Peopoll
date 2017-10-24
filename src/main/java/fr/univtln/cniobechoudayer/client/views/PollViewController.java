package fr.univtln.cniobechoudayer.client.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PollViewController implements Initializable {

    @FXML
    private AnchorPane rootView;

    /*
    Method to go back to homeview
     */
    @FXML
    private void goBackHome() throws IOException {
        loadScreen("HomeView");
    }

    /*
    Method to define the final date / choice
     */
    @FXML
    private void defineFinalDate(){

    }

    /*
    Save the current state of the poll
     */
    @FXML
    private void savePoll(){

    }

    /*
    Lock the poll (disable grid)
     */
    @FXML
    private void lockPoll(){

    }

    /*
    Get management access (left bar)
     */
    @FXML
    private void accessAsOrga(){

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /*
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
