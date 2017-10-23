package fr.univtln.cniobechoudayer.client.views;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTimePicker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import javax.swing.text.html.ImageView;
import java.io.IOException;

public class Step3Controller {

    @FXML
    private AnchorPane rootView;

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
    private Pane validationCreationArrow;

    @FXML
    private ImageView homeButton;

    @FXML
    private Text titlePollText;


    public Step3Controller(){

    }

    @FXML
    private void goBackHome() throws IOException {
        loadScreen("HomeView");
    }

    @FXML
    private void validatePollCreation() throws IOException {
        loadScreen("PollShareCodesView");
    }

    @FXML
    private void goToPreviousStep() throws IOException {
        loadScreen("PollCreationStep2View");
    }

    @FXML
    private void addTimeSlotToPoll(){
        validationCreationArrow.setVisible(true);
    }

    @FXML
    public void loadScreen(String resource) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + resource + ".fxml"));
        System.out.println("Loading : /fxml/" + resource + ".fxml");
        AnchorPane ap = loader.load();
        rootView.getChildren().setAll(ap);
    }


}
