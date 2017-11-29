/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package information_exploration;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

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
     *
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
                if (!yearCheck(startYearText.getText()) ||
                        !yearCheck(endYearText.getText()))
                    return;            
                int start = 1000;
                int finish = 3000;
                handleYearSearch(start, finish);

            } else if (category.equals("General")) {

                if (!searchText.equals("")) {
                    textSearch = searchText.getText();
                    Command c = new GeneralSearch(laureates, textSearch);
                    undoManager.addCommand(c);
                    laureates = c.execute();
                } else {
                    return;
                }

            } else {

                if (!searchText.equals("")) {
                    textSearch = searchText.getText();
                    Command c = new CategorySearch(laureates, category, textSearch);
                    undoManager.addCommand(c);
                    laureates = c.execute();
                } else {
                    return;
                }
            }
            searchText.clear();
            updateListView();
            getHistory();
        }
    }

    @FXML
    public void handleBackButton() throws Exception {
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
        endYearText.clear();
        startYearText.clear();
    }

    private boolean yearCheck(String year) {
        for (char ch : year.toCharArray()) {
            if (!Character.isDigit(ch)) {
                return false;
            }
        }
        return true;
    }
}
