package fr.univtln.cniobechoudayer.client.views;

import com.jfoenix.controls.*;
import fr.univtln.cniobechoudayer.model.entities.Poll;
import fr.univtln.cniobechoudayer.server.controllers.PollController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.*;

import java.io.IOException;

/**
 * Created by Corentin on 21/10/2017.
 */
public class HomeViewController extends VBox {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private JFXTextField codeTextField;

    @FXML
    private JFXButton pollCreateButton;

    @FXML
    private JFXButton searchCodeButton;

    private PollController pollController;

    public HomeViewController(){

    }

    /**
    Method to search a poll using a code when users click
     */
    @FXML
    private void searchPoll() throws IOException {
        if(codeTextField.getText().length() != 0 || codeTextField.getText() != null){
            Poll searchedPoll = pollController.searchPollByCode(codeTextField.getText());
            if(searchedPoll != null){
                loadScreen("PollView", searchedPoll);
            }
        }else{

        }
    }

    /**
    Method to display poll creation view
     */
    @FXML
    private void createPoll() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PollCreationStep1View.fxml"));
        Pane cmdPane = fxmlLoader.load();
        try {
            rootPane.getChildren().clear();
            rootPane.getChildren().add(cmdPane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
    Method to check if access code text field is empty
    Enable - Disable access button
     */
    @FXML
    private void isAccessCodeTextFieldEmpty(){
        if(codeTextField.getText().length() > 0){
            searchCodeButton.setDisable(false);
        }else{
            searchCodeButton.setDisable(true);
        }
    }

    /**
     * Method to load a new view
     * @param resource
     * @throws IOException
     */
    @FXML
    public void loadScreen(String resource) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + resource + ".fxml"));
        System.out.println("Loading : /fxml/" + resource + ".fxml");
        loader.setController(new Step1ViewController());
        AnchorPane ap = loader.load();
        rootPane.getChildren().setAll(ap);
    }

    /**
     * Method to display a searched poll
     */
    @FXML
    public void loadScreen(String resource, Poll searchedPoll) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + resource + ".fxml"));
        System.out.println("Loading : /fxml/" + resource + ".fxml");
        loader.setController(new PollViewController(searchedPoll));
        AnchorPane ap = loader.load();
        rootPane.getChildren().setAll(ap);
    }




}
