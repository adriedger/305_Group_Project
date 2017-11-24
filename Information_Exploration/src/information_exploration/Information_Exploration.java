/*
 * Macewan University CMPT 305 Professor: Brian Brookwell
 */
package information_exploration;

import java.net.MalformedURLException;
import javafx.fxml.FXMLLoader;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author adriedger
 */
public class Information_Exploration extends Application{
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Information Exploration");
        stage.show();
    }

    /**
     * @param args the command line arguments
     * @throws java.net.MalformedURLException
     */
    public static void main(String[] args) throws MalformedURLException, Exception {
        launch(args);
        UndoManager undoManager = new UndoManager();
        ReadNobel process = new ReadNobel();
        List<Laureate> laureates = process.read();  

   

        //List<String> countries = 
        //for(Laureate l : laureates)
           //l.output();
        
        /*
        Command newC = new GeneralSearch(laureates, "1929");  
        undoManager.addCommand(newC);
        laureates = newC.execute();
        
        CountryList countries = new CountryList(laureates);
        System.out.println(countries);
        PrizeList prizes = new PrizeList(laureates);
        System.out.println(prizes);
        
        //Command newC = new CategorySearch(laureates, "country", "canada");
        //undoManager.addCommand(newC);
        //laureates = newC.execute();
       
        
        //for (Laureate l : laureates)
            //l.output();
        
         //= availCountry(laureates);
*/

    }
}
