package fr.univtln.cniobechoudayer.client.views;

import com.jfoenix.controls.JFXTextField;
import fr.univtln.cniobechoudayer.model.entities.Choice;
import fr.univtln.cniobechoudayer.model.entities.Contribution;
import fr.univtln.cniobechoudayer.model.entities.Poll;
import fr.univtln.cniobechoudayer.server.controllers.ChoiceController;
import fr.univtln.cniobechoudayer.server.controllers.PollController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PollViewController implements Initializable {

    private Poll pollToDisplay;

    private PollController pollController = new PollController();

    private ChoiceController choiceController = new ChoiceController();

    private List<Choice> listDates;

    private List<Contribution> listContributions;

    @FXML
    private AnchorPane rootView;

    @FXML
    private JFXTextField managerCodeTextField;

    @FXML
    private Pane managerPane;

    @FXML
    private GridPane gridContributions;


    public PollViewController(Poll pollToDisplay){
        this.pollToDisplay = pollToDisplay;
    }
    /**
    Method to go back to homeview
     */
    @FXML
    private void goBackHome() throws IOException {
        loadScreen("HomeView");
    }

    /**
    Method to define the final date / choice
     */
    @FXML
    private void defineFinalDate(){

    }

    /**
    Save the current state of the poll
     */
    @FXML
    private void savePoll(){

    }

    /**
    Lock the poll (disable grid)
     */
    @FXML
    private void lockPoll(){

    }

    /**
    Grant management access (left bar)
     */
    @FXML
    private void accessAsOrga(){
        if(managerCodeTextField.getText() == pollToDisplay.getManagerCode()){
            managerPane.setVisible(true);
        }else{
            managerPane.setVisible(false);
        }
    }

    /**
     * Set the view of poll
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(pollToDisplay.isIsLocked()){
            //TODO change image
        }
    }

    /**
     * Setting up the view
     */
    private void bindGridView(){
        listDates = choiceController.getAllChoicesFor(pollToDisplay.getIdPoll());
        setGridViewColumns(listDates.size());
        setGridViewRows(listContributions.size());

        setRatioContributors(pollToDisplay.getNbMaxContributor());

        bindGridViewColumns();
    }

    /**
     * Method that set up the columns
     * @param columns number of columns
     */
    private void setGridViewColumns(int columns){
        for(int i=0; i < columns; i++){
            gridContributions.addColumn(i);
        }
    }

    /**
     * Method that set up the rows
     * @param rows number of rows
     */
    private void setGridViewRows(int rows){
        for(int i=0; i < rows; i++){
            gridContributions.addRow(i);
        }
    }

    /**
     * Method that display the number of contributors available
     * @param nbMax
     */
    private void setRatioContributors(int nbMax){
        gridContributions.add(new Text(listContributions.size() + " / " + nbMax), 0, 0);
    }

    private void bindGridViewColumns(){
        /**
         * Set the dates in grid
         */
        for(int i = 0; i == listDates.size(); i++){
            Choice currentChoice = listDates.get(i);
            gridContributions.add(new Text(currentChoice.getDateChoice() + " " + currentChoice.getStartingTime() + " " + currentChoice.getEndingTime()), i+1, 0);
        }
    }

    private void bindGridViewRows(){
        for(int i = 0; i == listContributions.size(); i++){
            Contribution currentContribution = listContributions.get(i);
            TextField txtField = new TextField(currentContribution.getNameContributor());
            for(int j = 0; j == listDates.size(); j++){
                if(i > 0){
                    //gridContributions.add(txtField, i, j);
                    Checkbox checkbox = new Checkbox();
                    //TODO add checkbox in colsn
                }
            }
        }
    }



    /**
    Method to load any view from PollViewController
     */
    @FXML
    public void loadScreen(String resource) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + resource + ".fxml"));
        System.out.println("Loading : /fxml/" + resource + ".fxml");
        AnchorPane ap = loader.load();
        rootView.getChildren().setAll(ap);
    }
}
