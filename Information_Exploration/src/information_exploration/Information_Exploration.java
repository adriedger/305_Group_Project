/*
 * Macewan University CMPT 305 Professor: Brian Brookwell
 */
package information_exploration;

import java.net.MalformedURLException;
import java.util.List;

/**
 *
 * @author adriedger
 */
public class Information_Exploration {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MalformedURLException, Exception {
        UndoManager undoManager = new UndoManager();
        ReadNobel process = new ReadNobel();
        List<Laureate> laureates = process.read();
        //for(Laureate l : laureates)
           //l.output();
        
        /*
        Command newC = new GeneralSearch(laureates, "1929");  
        undoManager.addCommand(newC);
        laureates = newC.execute();
        */
        Command newC = new CategorySearch(laureates, "country", "canada");  
        undoManager.addCommand(newC);
        laureates = newC.execute();
       
        
        for (Laureate l : laureates)
            l.output();
        

        
    }
}
