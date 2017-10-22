package fr.univtln.cniobechoudayer.client.views;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

import javax.swing.text.html.ImageView;

public class Step2Controller {

    @FXML
    private ImageView nextStepArrow;

    @FXML
    private ImageView previousStepArrow;

    @FXML
    private JFXTextField mailCreatorTextField;

    @FXML
    private JFXTextField nameCreatorTextField;

    public Step2Controller(){

    }

    /*
    Method to go back to step 1
     */
    @FXML
    private void goToPreviousStep(){

    }

    /*
    Method to access step 3 of poll creation
     */
    @FXML
    private void goToNextStep(){
        if(nameCreatorTextField.getText() != null && nameCreatorTextField.getText().length() > 0){
            if(mailCreatorTextField.getText() != null && mailCreatorTextField.getText().length() > 0){
                //TODO display next screen
            }else{
                //TODO display error mail
            }
        }else{
            //TODO display error name
        }
    }


}
