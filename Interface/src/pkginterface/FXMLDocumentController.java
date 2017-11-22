/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkginterface;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author tahmi
 */
public class FXMLDocumentController implements Initializable {
    public Button search;
    String category;
    /**
     *for the search button it will should use the search text to run the search
     */
    @FXML
    public void handleButtonSearch(){
        System.out.println("search clicked and the category is: "+ category);
       
    }
    
    @FXML
    public void categoryChosen(){
        //gets the category
        String cat = categoryBox.getSelectionModel().getSelectedItem().toString();
        //System.out.println(category);
        category = cat;
        System.out.println(category);
    }
    
    @FXML
    private TabPane tabPane;
    
    
    @FXML
    private ComboBox categoryBox;
    ObservableList<String> categoryList = FXCollections.observableArrayList("General","Name","Prize","Gender","Year","Country");
    
    @FXML
    private void loadTab(ActionEvent Event) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("forTabs.fxml"));
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        categoryBox.setItems(categoryList);
        
    }    
    
}
