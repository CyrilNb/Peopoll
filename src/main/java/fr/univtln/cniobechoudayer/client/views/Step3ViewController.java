package fr.univtln.cniobechoudayer.client.views;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTimePicker;
import fr.univtln.cniobechoudayer.server.controllers.PollController;
import fr.univtln.cniobechoudayer.server.exceptions.PersistanceException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Step3ViewController implements Initializable{

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

    private PollController pollController;

    private int idPollCreated;

    private HashMap<String, String> currentPoll;


    /**
     * constructor retrieving previous data
     * @param passedPoll is the current poll in creation
     */
    public Step3ViewController(HashMap passedPoll){
        this.currentPoll = passedPoll;
    }

    @FXML
    private void goBackHome() throws IOException {
        loadScreen("HomeView");
    }

    @FXML
    private int validatePollCreation() throws IOException, PersistanceException {

        //Parse NBMAX
        int nbMax;
        try {
            nbMax = Integer.parseInt(currentPoll.get("NBMAX"));
        }catch(Exception e){
            nbMax = 0;
        }

        idPollCreated = pollController.createPoll(currentPoll.get("Title"), currentPoll.get("Location"), currentPoll.get("Info"), currentPoll.get("Creator"), currentPoll.get("Mail"), nbMax);
        loadScreen("PollShareCodesView", idPollCreated);
        return 1;
    }

    @FXML
    private void goToPreviousStep() throws IOException {
        loadScreen("PollCreationStep2View", currentPoll);
    }

    @FXML
    private void addTimeSlotToPoll(){
        validationCreationArrow.setVisible(true);
    }

    @FXML
    public void loadScreen(String resource) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + resource + ".fxml"));
        AnchorPane ap = loader.load();
        rootView.getChildren().setAll(ap);
    }

    /**
     Method to loadScreen with parameters
     @param resource is the view
     @param currentPoll in creation to share its data through the steps
     */
    @FXML
    public void loadScreen(String resource, HashMap currentPoll) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + resource + ".fxml"));
        if(resource == "PollCreationStep2View")
            loader.setController(new Step2ViewController(currentPoll));
        AnchorPane ap = loader.load();
        rootView.getChildren().setAll(ap);
    }

    /**
     * load final share screen
     * @param resource name of view
     * @param idPoll id of created poll in order to display final screen
     * @throws IOException
     */
    @FXML
    public void loadScreen(String resource, int idPoll) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + resource + ".fxml"));
        loader.setController(new PollShareViewController(idPoll));
        AnchorPane ap = loader.load();
        rootView.getChildren().setAll(ap);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO
    }
}
