/*
 * CMPT 305 - Group Project (Fall 2017)
 * Group 6
 *
 */
package information_exploration;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
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
import static java.util.Comparator.comparing;

/**
 * FXMLDocumentController - main class to handle the FXMLDocument
 *
 * @author tahmi + Stephen Doyle (doyles8@mymacewan.ca)
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
    private ComboBox sortByBox;
    ObservableList<String> sortList = FXCollections.observableArrayList("Name",
            "Year");

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

    /**
     * initialize - pulls the data from the Nobel prize database, builds the
     * observable list.
     *
     * @param url
     * @param rb
     */
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
        sortByBox.setItems(sortList);
        categoryBox.setItems(categoryList);
        selectionBox.setVisible(false);
        startYearText.setVisible(false);
        endYearText.setVisible(false);
        searchText.setVisible(false);

    }

    /**
     * handleButtonSearch - creates a command from the user input + selection,
     * executes that command, updates the listView, stores the command in the
     * undoManager and adds to the history bar. Will also prevent the user from
     * performing unwanted actions (ie: an empty search text)
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
                if (!yearCheck(startYearText.getText())
                        || !yearCheck(endYearText.getText())) {
                    return;
                }
                handleYearSearch();

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

    /**
     * handleBackButton - executes the undo of the command at the top of the
     * undoManagers stack, removes the latest entry in the history bar.
     *
     * @throws Exception
     */
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

    /**
     * handleResetButton - restores the list to its original state (runs all
     * possible undo commands) and clears the history bar
     *
     * @throws Exception
     */
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

    /**
     * sortBy - sorts the listView based on the category selected in sortByBox.
     */
    @FXML
    private void sortBy() {
        String cat = sortByBox.getSelectionModel().getSelectedItem().toString();
        System.out.println("Sort By" + cat);
        if (cat.equals("Name")) {
            Collections.sort(laureates, new Laureate());
        }
        if (cat.equals("Year")) {
            Collections.sort(laureates);
        }
        updateListView();
    }

    /**
     * getHistory - updates the history bar at the top of the screen
     */
    public void getHistory() {
        array.add(category + ": " + textSearch);
        history.setText(array.toString());
    }

    /**
     * fillSelectionBox - Fills the category selection box with all the
     * available options given a category string.
     *
     * @param category - the category to check for available options
     * @throws MalformedURLException
     * @throws Exception
     */
    public void fillSelectionBox(String category) throws MalformedURLException, Exception {

        if (category.equals("Country")) {
            CountryList countries = new CountryList(laureates);
            selectionBox.setItems(FXCollections.observableArrayList(countries.countries));
        } else if (category.equals("Prize")) {
            PrizeList prizes = new PrizeList(laureates);
            selectionBox.setItems(FXCollections.observableArrayList(prizes.prizes));
        }

    }

    /**
     * updateListView - updates the items in the list view after a command is
     * performed. Works as an observer
     */
    private void updateListView() {
        listMain.setItems(null);
        ObservableList<Laureate> lList = FXCollections.observableList(laureates);
        listMain.setItems(lList);
    }

    /**
     * handleYearSearch - by default the year range is 1000-3000, changes those
     * values if either textbook contains a valid numerical entry.
     */
    private void handleYearSearch() {
        int start = 1000;
        int finish = 3000;
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

    /**
     * yearCheck - static function to prevent user from searching years with
     * characters not numbers
     *
     * @param year - string being checked
     * @return - true/false if string contains non digits
     */
    private boolean yearCheck(String year) {
        for (char ch : year.toCharArray()) {
            if (!Character.isDigit(ch)) {
                return false;
            }
        }
        return true;
    }
}
