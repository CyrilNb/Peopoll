package fr.univtln.cniobechoudayer.client.views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import fr.univtln.cniobechoudayer.model.entities.*;
import fr.univtln.cniobechoudayer.model.entities.Choice;
import fr.univtln.cniobechoudayer.server.controllers.*;
import fr.univtln.cniobechoudayer.server.exceptions.PersistanceException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import java.sql.SQLException;
import java.util.*;
import java.util.List;

public class PollViewController implements Initializable {

    private Poll pollToDisplay;

    private PollController pollController = new PollController();

    private ChoiceController choiceController = new ChoiceController();

    private List<Choice> listChoices;

    private List<Contribution> listContributions;

    private List<String> namesAlreadyDisplayed = new ArrayList<>();

    private int numberAdds = 0;

    private int numberImportedContributions = 0;

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

    @FXML
    private JFXButton addRowGridPane;


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
    private void savePoll() throws SQLException, PersistanceException, IOException {
        /*ContributionController.save("Corentin", pollToDisplay.getIdPoll(), 1);
        Contribution cont = Contribution.findById(1);
        System.out.println(cont);
        listContributions = Contribution.findAll();
        System.out.println(listContributions.size());*/
        saveDataPoll();
        //loadScreen("PollView", pollToDisplay);

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

            try {
                listChoices = ChoiceController.getAllChoicesFor(pollToDisplay.getIdPoll());
            } catch (PersistanceException e) {
                e.printStackTrace();
            }
            try {
                listContributions = ContributionController.getAllContributionsFor(pollToDisplay.getIdPoll());
            } catch (PersistanceException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                bindGridView();
            } catch (PersistanceException e) {
                e.printStackTrace();
                System.out.println("cant bind grid view");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            numberImportedContributions = listContributions.size();


        bindDatesComboBox();
        setView();

            addRowGridPane.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    gridContributions.addRow(listContributions.size()+1, new TextField());
                    for(int i = 0; i < listChoices.size(); i++){
                        gridContributions.add(new JFXCheckBox(), i+1, listContributions.size()+1);
                        numberAdds++;
                    }
                    listContributions.add(new Contribution());
                }
            });

        }else{
            System.out.println("PollToDisplay null");
        }
    }

    /**
     * Setting up the view
     */
    private void bindGridView() throws PersistanceException, SQLException {
        setGridViewColumns(listChoices.size());
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

    /**
     * Method to add a Blank row
     */
    private void addBlankAddRow(){
    }


    /**
     * Method to bind head of columns (nbMax and choices)
     */
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

    /**
     * Method to bind the rows (contributions) with data
     */
    private void bindGridViewRows(){
        int indexRow = 0;
        for(int i = 0; i < (listContributions.size()+1); i++){
            System.out.println(listContributions + "listContributions");
            if(i > 0){
                Contribution currentContribution = listContributions.get(i-1);
                List<Contribution> contributionListGroupByName;
                //If the name and matching contribs have not been loaded in view yet
                if(isNameContributionsAlreadyLoaded(currentContribution.getNameContributor())) {
                    indexRow++;
                    TextField txtField = new TextField();
                    txtField.setText(currentContribution.getNameContributor());
                    contributionListGroupByName = getContributionForName(currentContribution.getNameContributor());
                    gridContributions.addRow(indexRow, txtField);
                    for (Contribution subContribution : contributionListGroupByName) {
                        for (int j = 1; j < (listChoices.size() + 1); j++) {
                            JFXCheckBox checkbox = new JFXCheckBox();
                            checkbox.setId("checkbox");
                            if (subContribution.getIdChoice() == listChoices.get(j - 1).getIdChoice()) {
                                checkbox.setSelected(true);
                            }
                            //TODO center checkboxes
                            gridContributions.add(checkbox, j, indexRow);
                        }
                    }
                    setNameAlreadyDisplayed(currentContribution.getNameContributor());
                }

            }
        }
    }

    /**
     * Method to get all contributions per name
     * @param nameContributor
     * @return list of contributions
     */
    private List<Contribution> getContributionForName(String nameContributor){
        List<Contribution> lc = new ArrayList<>();
            if (listContributions != null) {
                for (Contribution cont : this.listContributions) {
                    if(cont.getNameContributor() != null){
                        if (cont.getNameContributor().equals(nameContributor)) {
                            lc.add(cont);
                        }
                    }else{
                        lc = Collections.emptyList();
                    }

                }
            }
        System.out.println("Liste des contributions pour le nom : " + nameContributor);
        return lc;
    }

    private boolean isNameContributionsAlreadyLoaded(String nameContributor){
        if(!namesAlreadyDisplayed.contains(nameContributor))
            return true;
        else
            return false;
    }

    /**
     * Method to set a name already displayed and loaded in view
     * @param nameContributor
     */
    private void setNameAlreadyDisplayed(String nameContributor){
        namesAlreadyDisplayed.add(nameContributor);
    }
    /**
     * Method that binds the combobox to define date
     */
    private void bindDatesComboBox(){
        for (Choice choice: listChoices) {
            choicesComboBox.getItems().add(choice);
        }
    }

    /**
     * Method to save all thesaveDataPoll data of the poll
     * @throws SQLException
     * @throws PersistanceException
     */
    private void saveDataPoll() throws SQLException, PersistanceException {
        System.out.println(new PersistanceException("contribution not found"));
        for(int i = 0; i < listContributions.size(); i++){
            if(numberImportedContributions != 0) {
                String name = listContributions.get(i).getNameContributor();
                System.out.println("searched name : " + name);
                List<Contribution> contributionsListForSpecificName = getContributionForName(name);
                for (int j = 0; j < listChoices.size(); j++) {
                    CheckBox chkBox = (CheckBox) getCheckBoxFromGridPane(gridContributions, j + 1, i + 1, true);
                    if (chkBox != null) {
                        for (Contribution c : contributionsListForSpecificName) {
                            if (c.getIdChoice() == listChoices.get(j).getIdChoice()) {
                                System.out.println("The choice ID + " + c.getIdChoice() + " already exists in DB");
                                if (!chkBox.isSelected()) {
                                    System.out.println("delete because checkbox is not selected");
                                    ContributionController.save(name, pollToDisplay.getIdPoll(), listChoices.get(j).getIdChoice(), true);
                                }
                            } else {
                                if (chkBox.isSelected()) {
                                    System.out.println("insert because checkbox is selected");
                                    int idContribAdded = ContributionController.save(name, pollToDisplay.getIdPoll(), listChoices.get(j).getIdChoice(), false);
                                    Contribution newContrib = ContributionController.getContributionBy(idContribAdded);
                                    if(newContrib != null){
                                        System.out.println("Adding " + newContrib + " to listContributions");
                                        listContributions.add(newContrib);
                                    }
                                }
                            }
                        }
                    }
                }
            }else{
                System.out.println("listContributions null");
                for(int k = 0; k < numberAdds; k++){
                    TextField txt = (TextField) getCheckBoxFromGridPane(gridContributions, 0,k+1, false);
                    System.out.println("number adds" + numberAdds);
                    if(txt != null) {
                        String name = txt.getText();
                        for (int l = 0; l < listChoices.size(); l++) {
                            CheckBox chkBox = (CheckBox) getCheckBoxFromGridPane(gridContributions, l + 1, k + 1, true);
                            System.out.println(l + " " + k);
                            if (chkBox.isSelected()) {
                                ContributionController.save(name, pollToDisplay.getIdPoll(), listChoices.get(l).getIdChoice(), false);
                            }
                        }
                    }else{
                        System.out.println("node null in saveNewFieldsPart");
                    }
                }
            }
        }

    }

    /**
     * Method to get the node in a specific cell
     * @param gridPane
     * @param col
     * @param row
     * @return
     */
    private Node getCheckBoxFromGridPane(GridPane gridPane, int col, int row, boolean isCheckBoxSearched) {
        for (Node node : gridPane.getChildren()) {
            CheckBox ch;
            TextField txt;
            if(isCheckBoxSearched){
                try{
                    ch = (CheckBox)node;
                    if(node != null) {
                        if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                            System.out.println("getNodeFromGridPane" + node);
                            return node;
                        }
                    }
                }catch(Exception e){
                }
            }else{
                try{
                    txt = (TextField) node;
                    if(node != null) {
                        if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                            System.out.println("getNodeFromGridPane" + node);
                            return node;
                        }
                    }
                }catch(Exception e){
                }
            }

        }
        return null;
    }

    /**
     * Method to set up the view and components settings
     */
    private void setView(){
        /**
         * center value in GridPane
         */

        GridPane.setValignment(gridContributions, VPos.CENTER);
        GridPane.setHalignment(gridContributions, HPos.CENTER);

        /*
        ColumnConstraints colContruest = new ColumnConstraints();
        colConst.setPercentWidth(100 / (listChoices.size() + 2));

        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setPercentHeight(100 / listContributions.size() + 2);

        gridContributions.getColumnConstraints().add(colConst);
        gridContributions.getRowConstraints().add(rowConstraints);

*/

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

    /**
     * Method to display a poll
     */
    @FXML
    public void loadScreen(String resource, Poll poll) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + resource + ".fxml"));
        loader.setController(new PollViewController(poll));
        AnchorPane ap = loader.load();
        rootView.getChildren().setAll(ap);
    }

    /**
     * Method pour le debug
     */
    public void displayLists(){
        for (Choice choice:listChoices) {
            System.out.println(choice);
        }

        for(Contribution contrib : listContributions){
            System.out.println(contrib);
        }
    }
}
