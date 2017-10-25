package fr.univtln.cniobechoudayer.client.views;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import fr.univtln.cniobechoudayer.model.entities.*;
import fr.univtln.cniobechoudayer.model.entities.Choice;
import fr.univtln.cniobechoudayer.server.controllers.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.List;

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

    @FXML
    private JFXComboBox choicesComboBox;


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
        if(gridContributions.isVisible()){
            gridContributions.setVisible(false);
            choicesComboBox.setVisible(true);
        }else{
            gridContributions.setVisible(true);
            choicesComboBox.setVisible(false);
        }
    }

    @FXML
    private void datesComboBoxSelected(){
        //TODO when user select a value, update selected date
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
        if(gridContributions.isDisable()){
            gridContributions.setDisable(false);
            pollToDisplay.setIsLocked(false);
        }else{
            gridContributions.setDisable(true);
            pollToDisplay.setIsLocked(true);
        }
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
        if(pollToDisplay != null){
            titlePollText.setText(pollToDisplay.getTitle());
            if(pollToDisplay.isIsLocked()){
                //TODO change image
            }
            bindGridView();
            //TODO bind listContributions from DB

        bindDatesComboBox();
        setView();
        }else{
            System.out.println("PollToDisplay null");
        }
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
        setGridViewRows(listContributions.size() + 1);
        System.out.println("Taille de listChoices : " + listChoices.size());
        System.out.println("Taille de listContributions : " + listContributions.size());
        setRatioContributors(pollToDisplay.getNbMaxContributor());

        bindGridViewColumns();
        bindGridViewRows();
        addBlankAddRow();

        setView();
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
            System.out.println("ajout row");
            gridContributions.addRow(i);
        }
    }

    /**
     * Method that display the number of contributors available
     * @param nbMax
     */
    private void setRatioContributors(int nbMax){
        if(nbMax != 0)
            gridContributions.add(new Text(listContributions.size() + " / " + nbMax), 0, 0);
        else
            gridContributions.add(new Text(String.valueOf(listContributions.size())), 0, 0);
    }

    private void addBlankAddRow(){
        gridContributions.addRow(listContributions.size()+2, new Text("ADD "));
    }

    private void bindGridViewColumns(){
        /**
         * Set the dates in grid
         */
        for(int i = 0; i < listChoices.size(); i++){
            Choice currentChoice = listChoices.get(i);
            System.out.println(currentChoice);
            Text txt = new Text(currentChoice.displayChoiceInIHM());
            txt.setWrappingWidth(100);
            gridContributions.addColumn(i+1, txt);
        }
    }

    private void bindGridViewRows(){
        for(int i = 0; i < (listContributions.size()+1); i++){
            if(i > 0){
            Contribution currentContribution = listContributions.get(i-1);
            TextField txtField = new TextField();
            txtField.setText(currentContribution.getNameContributor());
            System.out.println(txtField.getText());
            System.out.println("adding textfields");
            gridContributions.addRow(i, txtField);
            for(int j = 1; j < (listChoices.size()+1); j++){
                JFXCheckBox checkbox = new JFXCheckBox();
                //TODO center checkboxes
                gridContributions.add(checkbox, j, i);
            }

            }
        }
    }

    private void bindDatesComboBox(){
        for (Choice choice: listChoices) {
            choicesComboBox.getItems().add(choice);
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

        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setPercentHeight(100 / listContributions.size()+2);

        gridContributions.getColumnConstraints().add(colConst);

        choicesComboBox.setVisible(false);

        if(pollToDisplay.isIsLocked()){
            gridContributions.setDisable(true);
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
