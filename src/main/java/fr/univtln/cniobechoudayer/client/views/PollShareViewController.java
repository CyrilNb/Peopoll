package fr.univtln.cniobechoudayer.client.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import javax.swing.text.html.ImageView;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PollShareViewController implements Initializable{

    @FXML
    private AnchorPane rootView;

    @FXML
    private Text accessCodeText;

    @FXML
    private Text managementCodeText;

    @FXML
    private ImageView sendCodeMailButton;

    @FXML
    private ImageView viewPollButton;

    public PollShareViewController(int codePoll){

    }

    /*
    Method to display HomeView
     */
    @FXML
    private void goBackHome() throws IOException {
        loadScreen("HomeView");
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
}
