package fr.univtln.cniobechoudayer.client.views;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Step2Controller {

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

    //Regex pattern to check email
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public Step2Controller(){

    }

    /*
    Method to go back to step 1
     */
    @FXML
    private void goToPreviousStep() throws IOException {
        loadScreen("PollCreationStep1View");
    }

    @FXML
    private void goBackHome() throws IOException{
        loadScreen("HomeView");
    }

    /*
    Method to access step 3 of poll creation
     */
    @FXML
    private void goToNextStep() throws IOException {
        loadScreen("PollCreationStep3View");
    }

    /*
    Method to loadScreen for step2View
     */
    @FXML
    public void loadScreen(String resource) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + resource + ".fxml"));
        System.out.println("Loading : /fxml/" + resource + ".fxml");
        AnchorPane ap = loader.load();
        rootView.getChildren().setAll(ap);
    }

    /*
    Method to check email pattern
     */
    public static boolean isEmailValid(String emailStr) {
        System.out.println(emailStr);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

    /*
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
        }else{
            //TODO display mail error
        }

        //If fields are correct, then display next step
        if(isNameFieldCorrect && isMailFieldCorrect){
            nextStepArrow.setVisible(true);
            System.out.println("Fields valid");
        }else{
            nextStepArrow.setVisible(false);
            System.out.println("Fields non valid");
        }
    }

}
