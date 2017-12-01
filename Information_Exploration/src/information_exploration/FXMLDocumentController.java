/*
 * CMPT 305 - Group Project (Fall 2017)
 * Group 6
 *
 */
package information_exploration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXMLDocumentController - main class to handle the FXMLDocument
 *
 * @author tahmid + Stephen Doyle (doyles8@mymacewan.ca)
 */
public class FXMLDocumentController extends Application implements Initializable {

    String category, textSearch;
    ArrayList<String> array = new ArrayList<>();
    UndoManager undoManager = new UndoManager();
    ReadNobel process;
    List<Laureate> laureates;

    @FXML
    private ComboBox selectionBox;

    @FXML
    private ComboBox sortByBox;
    ObservableList<String> sortList = FXCollections.observableArrayList("Name", "Year");

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
    ObservableList<String> categoryList = FXCollections.observableArrayList("General", "Name", "Prize", "Gender", "Year", "Country");

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

            } else if (category.equals("Gender")
                    && selectionBox.getSelectionModel().getSelectedItem() != null) {
                textSearch = selectionBox.getSelectionModel().getSelectedItem().toString();
                Command c = new GenderSearch(laureates, textSearch);
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
            case "Gender":
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

        SelectionUpdater selections = new SelectionUpdater(laureates, category);
        selectionBox.setItems(FXCollections.observableArrayList(selections.selections));

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
        builder.append("->");
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

    @FXML
    public void handleMouseClick(MouseEvent arg0) throws IOException {
//        System.out.println("CLICK DETECTED");
        Group root = new Group();
        Scene scene = new Scene(root, 600, 230);
        Stage stage = new Stage();

        Laureate current = (Laureate) listMain.getSelectionModel().getSelectedItem();

        stage.setTitle(current.getEntry().get("name").toString() + " Card");

        InputStream in = new FileInputStream("nullpic.jpg");
        Image pic = new Image(in);
        ImageView viewer = new ImageView();
        viewer.setImage(pic);

        URL imageURL = new URL(current.getEntry().get("photo").toString());
        try {
            in = imageURL.openStream();
            pic = new Image(in);
            viewer.setImage(pic);
        } catch (FileNotFoundException ex) {
        }
        root.getChildren().add(viewer);

        Label name = new Label("Name: " + current.getEntry().get("name").toString());
        name.setLayoutX(170);
        name.setLayoutY(0);
        root.getChildren().add(name);

        Label birth = new Label("Birthyear: " + current.getEntry().get("birthyear").toString());
        birth.setLayoutX(170);
        birth.setLayoutY(22);
        root.getChildren().add(birth);

        if (!current.getEntry().get("deathyear").toString().equals("0000-00-00")) {
            Label death = new Label("Deathyear: " + current.getEntry().get("deathyear").toString());
            death.setLayoutX(170);
            death.setLayoutY(44);
            root.getChildren().add(death);
        }

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

        if (!current.getEntry().get("affiliation").toString().equals(", , ")) {
            Label aff = new Label("Affiliation: " + current.getEntry().get("affiliation").toString());
            aff.setLayoutX(170);
            aff.setLayoutY(132);
            root.getChildren().add(aff);
        }

        Label bio = new Label("Bio Link: ");
        bio.setLayoutX(170);
        bio.setLayoutY(154);
        root.getChildren().add(bio);
        Hyperlink link = new Hyperlink(current.getEntry().get("biography").toString());
        link.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getHostServices().showDocument(link.getText());
            }
        });
        link.setLayoutX(216);
        link.setLayoutY(151);
        root.getChildren().add(link);

        Label mot = new Label("Motivation: " + current.getEntry().get("motivation").toString());
        mot.setLayoutX(170);
        mot.setLayoutY(176);
        mot.setMaxWidth(430);
        mot.setWrapText(true);
        root.getChildren().add(mot);

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    }
}
