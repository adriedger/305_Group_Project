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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author tahmi
 */
public class FXMLDocumentController implements Initializable {
    String category,textSearch;
    ArrayList<String> array = new ArrayList<>();
  
    
    public Button search;
    public Button back;
    @FXML
    private AnchorPane rootPane;
    
    /**
     *for the search button it will should use the search text to run the search
     */
    @FXML
    public void handleButtonSearch() {
        textSearch = searchText.getText();
        if (!textSearch.equals("")) {
            System.out.println("search clicked and the category is: " + category);
            System.out.println(textSearch + " is being searched");
            getHistory();
        }

    }

    @FXML
    public void handleBackButton(){
        if (!array.isEmpty()){
            array.remove((array.size())-1);
            history.setText(array.toString());
        }
    }
    /**
     * determines what category is chosen
     * @throws java.lang.Exception
     */
    @FXML
    public void categoryChosen() throws Exception{
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
    
    public void getHistory(){
        //ArrayList<String> array = new ArrayList<>();
        array.add(category+": "+textSearch);
        history.setText(array.toString());
    }
    //@FXML
    //private AutoCompleteTextField autoSearch;
    
    public void fillSelectionBox(String category) throws MalformedURLException, Exception {
        ReadNobel process = new ReadNobel();
        List<Laureate> laureates = process.read();
        if (category.equals("Country")) {
            CountryList countries = new CountryList(laureates);
            selectionBox.setItems(FXCollections.observableArrayList(countries.countries));
        } else if (category.equals("Prize")) {
            PrizeList prizes = new PrizeList(laureates);
            selectionBox.setItems(FXCollections.observableArrayList(prizes.prizes));
        }
        
    }
    
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

    ObservableList<String> categoryList = FXCollections.observableArrayList("General","Name","Prize","Gender","Year","Country");
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        categoryBox.setItems(categoryList);
        selectionBox.setVisible(false);
        startYearText.setVisible(false);
        endYearText.setVisible(false);
        searchText.setVisible(false);
                
        
    }    
    
}
