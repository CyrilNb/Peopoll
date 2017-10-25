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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Step2ViewController implements Initializable{

    @FXML
    private AnchorPane rootView;

    @FXML
    private Pane nextStepArrow;

    @FXML
    private ImageView previousStepArrow;

    @FXML
    private JFXTextField mailCreatorTextField;

    @FXML
    private JFXTextField nameCreatorTextField;

    @FXML
    private JFXTextField nbMaxPeopleField;

    private HashMap<String, String> currentPoll;

    //Regex pattern to check email
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public Step2ViewController(HashMap currentPoll){
        this.currentPoll = currentPoll;
    }

    /**
    Method to go back to step 1
     */
    @FXML
    private void goToPreviousStep() throws IOException {
        saveFields();
        loadScreen("PollCreationStep1View", currentPoll);
    }

    @FXML
    private void goBackHome() throws IOException{
        loadScreen("HomeView");
    }

    /**
    Method to access step 3 of poll creation
     */
    @FXML
    private void goToNextStep() throws IOException {
        saveFields();
        loadScreen("PollCreationStep3View", currentPoll);
    }

    /**
    Method to loadScreen for without parameters
     */
    @FXML
    public void loadScreen(String resource) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + resource + ".fxml"));
        System.out.println("Loading : /fxml/" + resource + ".fxml");
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
        System.out.println("Loading : /fxml/" + resource + ".fxml");
        if(resource == "PollCreationStep1View")
            loader.setController(new Step1ViewController(currentPoll));
        else if(resource == "PollCreationStep3View")
            loader.setController(new Step3ViewController(currentPoll));
        AnchorPane ap = loader.load();
        rootView.getChildren().setAll(ap);
    }

    /**
    Method to check email pattern
     */
    public static boolean isEmailValid(String emailStr) {
        System.out.println(emailStr);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

    /**
    Method that checks field
     */
    @FXML
    private void checkFields(){
        boolean isNameFieldCorrect = false;
        boolean isMailFieldCorrect = false;

        //Checking name field
        if(nameCreatorTextField.getText().length() > 0){
            isNameFieldCorrect = true;
        }else{
            //TODO display name error
        }

        if(mailCreatorTextField.getText().length() > 0 && isEmailValid(mailCreatorTextField.getText())){
            isMailFieldCorrect = true;
        }

        //If fields are correct, then display next step
        if(isNameFieldCorrect && isMailFieldCorrect){
            nextStepArrow.setVisible(true);
        }else{
            nextStepArrow.setVisible(false);
        }
    }

    /**
     * Method to save field before accessing next step
     */
    private void saveFields(){
            currentPoll.put("Creator", nameCreatorTextField.getText());
            currentPoll.put("Mail", mailCreatorTextField.getText());
            currentPoll.put("NBMAX", nbMaxPeopleField.getText());
    }

    /**
     * Displaying data of the poll in creation
     * if user come steps backward
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(currentPoll != null){
            if(currentPoll.containsKey("Creator")){
                nameCreatorTextField.setText(currentPoll.get("Creator"));
            }
            if(currentPoll.containsKey("Mail")){
                mailCreatorTextField.setText(currentPoll.get("Mail"));
            }
            if(currentPoll.containsKey("NBMAX") && !currentPoll.get("NBMAX").isEmpty()){
                nbMaxPeopleField.setText(currentPoll.get("NBMAX"));
            }
        }
        checkFields();
    }
}
