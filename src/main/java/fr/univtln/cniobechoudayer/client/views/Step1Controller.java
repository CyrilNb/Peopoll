package fr.univtln.cniobechoudayer.client.views;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

import javax.swing.text.html.ImageView;

public class Step1Controller {

    @FXML
    private JFXTextField titlePollTextField;

    @FXML
    private JFXTextField placePollTextField;

    @FXML
    private JFXTextField infoPollTextField;

    @FXML
    private ImageView nextStepArrow;

    public Step1Controller(){

    }

    /*
    Method to access the step 2 of poll creation
     */
    @FXML
    private void nextStep(){
        if(titlePollTextField.getText().length() != 0 && titlePollTextField.getText() != null){
            titlePollTextField.getStyleClass().add("text-input.error");
        }else{

        }
    }


}
