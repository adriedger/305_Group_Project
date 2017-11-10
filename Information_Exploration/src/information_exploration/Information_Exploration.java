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
        ReadNobel process = new ReadNobel();
        List<Laureate> laureates = process.read();
        for(Laureate l : laureates)
            l.output(); 
    }
}
