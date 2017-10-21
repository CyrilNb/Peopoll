package fr.univtln.cniobechoudayer.client.net;

import com.jfoenix.controls.*;
import javafx.fxml.FXML;

/**
 * Created by Corentin on 21/10/2017.
 */
public class HomeViewController {

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
    private void createPoll(){

    }

}
