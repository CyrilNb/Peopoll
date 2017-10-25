package fr.univtln.cniobechoudayer.client.views;

import com.jfoenix.controls.JFXTextField;
import fr.univtln.cniobechoudayer.model.entities.Poll;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Step1ViewController implements Initializable {

    @FXML
    private AnchorPane rootView;

    @FXML
    private JFXTextField titlePollTextField;

    @FXML
    private JFXTextField placePollTextField;

    @FXML
    private JFXTextField infoPollTextField;

    @FXML
    private Pane nextStepArrow;

    private Map<String, String> currentPoll = new HashMap<String, String>();

    public Step1ViewController(){

    }

    public Step1ViewController(HashMap currentPoll){
        this.currentPoll = currentPoll;
    }

    /*
    Method to access the step 2 of poll creation
     */
    @FXML
    private void nextStep() throws IOException {
        saveFields();
        loadScreen("PollCreationStep2View", (HashMap) currentPoll);
    }

    /*
    Method to display HomeView
     */
    @FXML
    private void goBackHome() throws IOException {
        loadScreen("HomeView");
    }

    /*
    Method to check mandatory fields
     */
    @FXML
    public void checkFields(){
        if(titlePollTextField.getText().length() > 0){
            nextStepArrow.setVisible(true);
        }else{
            nextStepArrow.setVisible(false);
        }
    }

    @FXML
    private void saveFields(){
            currentPoll.put("Title", titlePollTextField.getText());
            currentPoll.put("Location", placePollTextField.getText());
            currentPoll.put("Info", infoPollTextField.getText());
    }

    /*
    Method to load a view
     */
    @FXML
    public void loadScreen(String resource) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + resource + ".fxml"));
        System.out.println("Loading : /fxml/" + resource + ".fxml");
        AnchorPane ap = loader.load();
        rootView.getChildren().setAll(ap);
    }

    /*
    Method to load a view
     */
    @FXML
    public void loadScreen(String resource, HashMap inCreationPoll) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + resource + ".fxml"));
        System.out.println("Loading : /fxml/" + resource + ".fxml");
        loader.setController(new Step2ViewController(inCreationPoll));
        AnchorPane ap = loader.load();
        rootView.getChildren().setAll(ap);
    }


    /**
     * Method called when the controller is instancied
     * Display already existing info from the current poll during the creation
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(currentPoll != null){
            if(currentPoll.containsKey("Title")){
                titlePollTextField.setText(currentPoll.get("Title"));
            }
            if(currentPoll.containsKey("Location") && !currentPoll.get("Location").isEmpty()){
                placePollTextField.setText(currentPoll.get("Location"));
            }
            if(currentPoll.containsKey("Info") && !currentPoll.get("Info").isEmpty()){
                infoPollTextField.setText(currentPoll.get("Info"));
            }
        }
        checkFields();
    }
}
