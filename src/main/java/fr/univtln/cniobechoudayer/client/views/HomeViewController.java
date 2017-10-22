package fr.univtln.cniobechoudayer.client.views;

import com.jfoenix.controls.*;
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

    public HomeViewController(){

    }

    /*
    Method to search a poll using a code when users click
     */
    @FXML
    private void searchPoll(){
        if(codeTextField.getText().length() == 0 || codeTextField.getText() == null){

        }else{

        }
    }

    /*
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

    /*
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

}
