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
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PollViewController implements Initializable {

    private Poll pollToDisplay;

    private PollController pollController = new PollController();

    private ChoiceController choiceController = new ChoiceController();

    private List<Choice> listChoices;

    private List<Contribution> listContributions;

    @FXML
    private AnchorPane rootView;

    @FXML
    private JFXTextField managerCodeTextField;

    @FXML
    private Pane managerPane;

    @FXML
    private GridPane gridContributions;

    @FXML
    private Text titlePollText;


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
        System.out.println(managerCodeTextField.getText());
        System.out.println(pollToDisplay.getManagerCode());
        if(String.valueOf(managerCodeTextField.getText()).equals(pollToDisplay.getManagerCode().toString())){
            managerPane.setVisible(true);
            managerCodeTextField.setVisible(false);
            System.out.println("match");
        }else{
            managerPane.setVisible(false);
            System.out.println("doesnt match");
        }
    }

    /**
     * Set the view of poll
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titlePollText.setText(pollToDisplay.getTitle());
        if(pollToDisplay.isIsLocked()){
            //TODO change image
        }
        bindGridView();
        //TODO bind listContributions from DB

        setView();
    }

    /**
     * Setting up the view
     */
    private void bindGridView(){
        listChoices = pollToDisplay.getChoicesList();
        setGridViewColumns(listChoices.size());
        listContributions = new ArrayList<>();
        listContributions.add(new Contribution("Corentin"));
        listContributions.add(new Contribution("Cyril"));
        listContributions.add(new Contribution("Goddamn"));
        listContributions.add(new Contribution("C'est la merde"));
        setGridViewRows(listContributions.size());
        System.out.println("Taille de listChoices : " + listChoices.size());
        System.out.println("Taille de listContributions : " + listContributions.size());
        setRatioContributors(pollToDisplay.getNbMaxContributor());

        bindGridViewColumns();
        bindGridViewRows();
    }

    /**
     * Method that set up the columns
     * @param columns number of columns
     */
    private void setGridViewColumns(int columns){
        for(int i=0; i < columns; i++){
            System.out.println("ajout colonne");
            //gridContributions.addColumn(i+1, new Text("ff"));
            //gridContributions.addColumn(i);
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
        for(int i = 0; i < listChoices.size(); i++){
            Choice currentChoice = listChoices.get(i);
            System.out.println(currentChoice);
            Text txt = new Text(currentChoice.toString());
            txt.setWrappingWidth(100);
            gridContributions.addColumn(i+1, txt);
        }
    }

    private void bindGridViewRows(){
        for(int i = 0; i == listContributions.size(); i++){
            Contribution currentContribution = listContributions.get(i);
            TextField txtField = new TextField(currentContribution.getNameContributor());
            for(int j = 0; j == listChoices.size(); j++){
                if(i > 0){
                    gridContributions.add(txtField, i, j);
                    Checkbox checkbox = new Checkbox();
                    //TODO add checkbox in colsn
                }
            }
        }
    }

    private void setView(){
        /**
         * center value in GridPane
         */
        GridPane.setValignment(gridContributions, VPos.CENTER);
        GridPane.setHalignment(gridContributions, HPos.CENTER);

        ColumnConstraints colConst = new ColumnConstraints();
        colConst.setPercentWidth(100 / (listChoices.size() + 2));

        gridContributions.getColumnConstraints().add(colConst);
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
