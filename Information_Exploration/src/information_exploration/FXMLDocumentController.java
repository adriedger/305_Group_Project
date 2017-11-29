/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package information_exploration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author tahmi
 */
public class FXMLDocumentController implements Initializable {

    String category, textSearch;
    ArrayList<String> array = new ArrayList<>();
    UndoManager undoManager = new UndoManager();
    ReadNobel process;
    List<Laureate> laureates;
    
    @FXML
    private ComboBox selectionBox;

    @FXML
    private Label history;

    @FXML
    private TextField searchText;

    @FXML
    private TextField startYearText;

    @FXML
    private TextField endYearText;

    @FXML
    private ComboBox categoryBox;
    ObservableList<String> categoryList
            = FXCollections.observableArrayList("General", "Name", "Prize", "Gender", "Year", "Country");

    @FXML
    private ListView listMain;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            process = new ReadNobel();
        } catch (MalformedURLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            laureates = process.read();
        } catch (Exception ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ObservableList<Laureate> lList = FXCollections.observableList(laureates);
        listMain.setItems(lList);
        categoryBox.setItems(categoryList);
        selectionBox.setVisible(false);
        startYearText.setVisible(false);
        endYearText.setVisible(false);
        searchText.setVisible(false);

    }

    /**
     * for the search button it will should use the search text to run the
     * search
     * @throws java.lang.Exception
     */
    @FXML
    public void handleButtonSearch() throws Exception {
        if (category == null) {
            //do nothing, no values entered
        } else {

            if ((category.equals("Country")
                    || category.equals("Prize"))
                    && selectionBox.getSelectionModel().getSelectedItem() != null) {

                textSearch = selectionBox.getSelectionModel().getSelectedItem().toString();
                Command c = new CategorySearch(laureates, category, textSearch);
                undoManager.addCommand(c);
                laureates = c.execute();
                fillSelectionBox(category);
                

            } else if (category.equals("Year")) {

                int start = 1000;
                int finish = 3000;
                handleYearSearch(start, finish);

            } else if (category.equals("General")) {

                if (searchText != null) {
                    textSearch = searchText.getText();
                    Command c = new GeneralSearch(laureates, textSearch);
                    undoManager.addCommand(c);
                    laureates = c.execute();
                } else 
                    return;
                
            } else {

                if (searchText != null) {
                    textSearch = searchText.getText();
                    Command c = new CategorySearch(laureates, category, textSearch);
                    undoManager.addCommand(c);
                    laureates = c.execute();
                } else
                    return;
            }
            searchText.clear();
            updateListView();
            getHistory();
        }
    }

    @FXML
    public void handleBackButton() throws Exception{
        if (!array.isEmpty()) {
            array.remove((array.size()) - 1);
            history.setText(array.toString());
            laureates = undoManager.undoCommand();
            updateListView();
            fillSelectionBox(category);
        }
    }
    
    @FXML
    public void handleResetButton() throws Exception {
        if (undoManager.canReset()) {
            laureates = undoManager.resetHome();
            array.clear();
            history.setText(array.toString());
            updateListView();
            fillSelectionBox(category);
        }
    }

    /**
     * determines what category is chosen
     *
     * @throws java.lang.Exception
     */
    @FXML
    public void categoryChosen() throws Exception {
        //gets the category
        String cat = categoryBox.getSelectionModel().getSelectedItem().toString();
        //System.out.println(category);
        category = cat;
        System.out.println(category);
        searchText.setVisible(false);
        startYearText.setVisible(false);
        endYearText.setVisible(false);
        selectionBox.setVisible(false);

        switch (category) {
            case "Country":
                fillSelectionBox(category);
                selectionBox.setVisible(true);
                break;
            case "Prize":
                fillSelectionBox(category);
                selectionBox.setVisible(true);
                break;
            case "Year":
                startYearText.setVisible(true);
                endYearText.setVisible(true);
                break;
            default:
                searchText.setVisible(true);
                break;
        }

    }

    public void getHistory() {
        array.add(category + ": " + textSearch);
        history.setText(array.toString());
    }

    public void fillSelectionBox(String category) throws MalformedURLException, Exception {

        if (category.equals("Country")) {
            CountryList countries = new CountryList(laureates);
            selectionBox.setItems(FXCollections.observableArrayList(countries.countries));
        } else if (category.equals("Prize")) {
            PrizeList prizes = new PrizeList(laureates);
            selectionBox.setItems(FXCollections.observableArrayList(prizes.prizes));
        }

    }
    
    private void updateListView() {
        listMain.setItems(null);
        ObservableList<Laureate> lList = FXCollections.observableList(laureates);
        listMain.setItems(lList);
    }

    private void handleYearSearch(int start, int finish) {
        StringBuilder builder = new StringBuilder();
        if (!startYearText.getText().isEmpty()) {
            start = Integer.parseInt(startYearText.getText());
            builder.append(start);
        }
        builder.append("-");
        if (!endYearText.getText().isEmpty()) {
            finish = Integer.parseInt(endYearText.getText());
            builder.append(finish);
        }
        textSearch = builder.toString();
        Command c = new YearSearch(laureates, start, finish);
        undoManager.addCommand(c);
        laureates = c.execute();
    }
    
    @FXML
    public void handleMouseClick(MouseEvent arg0) throws IOException{

        Group root = new Group();
        Scene scene = new Scene(root, 600, 230);
        Stage stage = new Stage();
        
        Laureate current = (Laureate)listMain.getSelectionModel().getSelectedItem();
        
        stage.setTitle(current.getEntry().get("name").toString() + " Card");
        
        URL imageURL = new URL(current.getEntry().get("photo").toString());
        try{
            InputStream in = imageURL.openStream();
            Image pic = new Image(in);
            ImageView viewer = new ImageView();
            viewer.setImage(pic);
            root.getChildren().add(viewer);
        } catch (FileNotFoundException ex){}
        
        Label name = new Label("Name: " + current.getEntry().get("name").toString());
        name.setLayoutX(170);
        name.setLayoutY(0);
        root.getChildren().add(name);     
        Label birth = new Label("Birthyear: " + current.getEntry().get("birthyear").toString());
        birth.setLayoutX(170);
        birth.setLayoutY(22);
        root.getChildren().add(birth);
        Label death = new Label("Deathyear: " + current.getEntry().get("deathyear").toString());
        death.setLayoutX(170);
        death.setLayoutY(44);
        root.getChildren().add(death);
        Label gender = new Label("Gender: " + current.getEntry().get("gender").toString().substring(0, 1).toUpperCase() + current.getEntry().get("gender").toString().substring(1));
        gender.setLayoutX(170);
        gender.setLayoutY(66);
        root.getChildren().add(gender);
        Label country = new Label("Nationality: " + current.getEntry().get("country").toString());
        country.setLayoutX(170);
        country.setLayoutY(88);
        root.getChildren().add(country);
        Label prize = new Label("Nobel Prize: " + current.getEntry().get("year").toString() + " " + current.getEntry().get("prize").toString().substring(0, 1).toUpperCase() + current.getEntry().get("prize").toString().substring(1));
        prize.setLayoutX(170);
        prize.setLayoutY(110);
        root.getChildren().add(prize);
        Label aff = new Label("Academic Affiliation: " + current.getEntry().get("affiliation").toString());
        aff.setLayoutX(170);
        aff.setLayoutY(132);
        root.getChildren().add(aff);
        Label bio = new Label("Bio Link: " + current.getEntry().get("biography").toString());
        bio.setLayoutX(170);
        bio.setLayoutY(154);
        root.getChildren().add(bio);
        Label mot = new Label("Motivation: " + current.getEntry().get("motivation").toString());
        mot.setLayoutX(170);
        mot.setLayoutY(176);
        mot.setMaxWidth(430);
        mot.setWrapText(true);
        root.getChildren().add(mot);
        
        stage.setScene(scene);
        stage.show();
    }   
}
