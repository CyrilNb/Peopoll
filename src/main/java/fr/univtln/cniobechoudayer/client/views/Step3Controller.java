package fr.univtln.cniobechoudayer.client.views;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTimePicker;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import javax.swing.text.html.ImageView;

public class Step3Controller {

    @FXML
    private JFXDatePicker dayPollPicker;

    @FXML
    private JFXTimePicker startPollPicker;

    @FXML
    private JFXTimePicker endPollPicker;

    @FXML
    private JFXListView timeSlotsListView;

    @FXML
    private ImageView previousStepArrow;

    @FXML
    private ImageView validationCreationArrow;

    @FXML
    private ImageView homeButton;

    @FXML
    private Text titlePollText;


    public Step3Controller(){

    }

    @FXML
    private void goBackHome(){

    }

    @FXML
    private void validatePollCreation(){

    }

    @FXML
    private void goToPreviousStep(){

    }

    @FXML
    private void addTimeSlotToPoll(){

    }

}
