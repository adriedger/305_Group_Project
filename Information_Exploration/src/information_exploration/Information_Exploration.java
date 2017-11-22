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
        
        Command newC = new CategorySearch(laureates, "year", "1950");  
        undoManager.addCommand(newC);
        laureates = newC.execute();
        
        Command nextC = new CategorySearch(laureates, "prize", "physics");
        undoManager.addCommand(nextC);
        laureates = nextC.execute();
        
        laureates = undoManager.undoCommand();
        
        //laureates = process.basicSearch("year", "1950");
        for (Laureate l : laureates)
            l.output();
        
    }
}
