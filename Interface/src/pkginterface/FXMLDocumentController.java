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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

/**
 *
 * @author tahmi
 */
public class FXMLDocumentController implements Initializable {
    //Category
    @FXML
    private ComboBox<String> category;
    
    @FXML
    private Label label;
    
    @FXML
    private void ButtonAction(ActionEvent event){
        label.setText("Selected: "+ category.getValue());
    }
    ObservableList<String> List = FXCollections.observableArrayList("Year","Prize","Name","Country","Gender");
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
