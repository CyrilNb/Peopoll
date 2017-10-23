package fr.univtln.cniobechoudayer.client.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

public class MainController implements Initializable{

    @FXML
    private AnchorPane rootView;


    @FXML
    public void loadScreen(String resource) throws IOException{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + resource + ".fxml"));
            System.out.println("Loading : /fxml/" + resource + ".fxml");
            AnchorPane ap = loader.load();
            rootView.getChildren().setAll(ap);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadScreen("HomeView");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
