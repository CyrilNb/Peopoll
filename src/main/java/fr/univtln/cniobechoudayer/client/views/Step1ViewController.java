package fr.univtln.cniobechoudayer.client.views;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import javax.swing.text.html.ImageView;
import java.io.IOException;

public class Step1ViewController {

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

    public Step1ViewController(){

    }

    /*
    Method to access the step 2 of poll creation
     */
    @FXML
    private void nextStep() throws IOException {
        loadScreen("PollCreationStep2View");
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


}
