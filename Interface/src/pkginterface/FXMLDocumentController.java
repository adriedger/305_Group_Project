/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkginterface;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

/**
 *
 * @author tahmi
 */
public class FXMLDocumentController implements Initializable {
    
    public Button search;
    
    /**
     *for the search button it will should use the search text to run the search
     */
    public void handleButtonSearch(){
        System.out.println("search clicked");
    }
    
    
    @FXML
    private Label label;
    
    @FXML
    private ComboBox categoryBox;
    ObservableList<String> categoryList = FXCollections.observableArrayList("Name","Prize","Gender","Year","Country");
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        categoryBox.setItems(categoryList);
    }    
    
}
