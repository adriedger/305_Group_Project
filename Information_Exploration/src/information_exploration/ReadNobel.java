/*
 * Macewan University CMPT 305 Professor: Brian Brookwell
 */
package information_exploration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author adriedger
 */
public class ReadNobel {
    
    private URL stockURL;
    private List<Laureate> laureates;
    
    public ReadNobel() throws MalformedURLException{
        stockURL = new URL("http://api.nobelprize.org/v1/laureate.csv");
        laureates = new ArrayList<>();  
    }
    
    public List<Laureate> read() throws MalformedURLException, IOException, Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(stockURL.openStream()));
        List<String> parsed = new ArrayList<>(); 
        CSVHelper helper = new CSVHelper();
        parsed = helper.parseLine(in);
        parsed = helper.parseLine(in);
        while(parsed != null){  
            Laureate laureate = new Laureate();
            String normalizedName = Normalizer.normalize(parsed.get(2).toLowerCase(), Normalizer.Form.NFD);
            normalizedName = normalizedName.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
            
            laureate.addEntry("id", parsed.get(0));
            laureate.addEntry("year", parsed.get(12));
            laureate.addEntry("prize", parsed.get(13));
            laureate.addEntry("name", parsed.get(1) + " " + parsed.get(2));
            laureate.addEntry("gender",  parsed.get(11));
            laureate.addEntry("photo", "https://www.nobelprize.org/nobel_prizes/"+parsed.get(13)+"/laureates/"+parsed.get(12)+"/"+normalizedName+".jpg");
            laureate.addEntry("country", parsed.get(5));
            laureate.addEntry("affiliation", parsed.get(17) + ", " + parsed.get(18) + ", " + parsed.get(19));
            laureate.addEntry("birthyear", parsed.get(3));
            laureate.addEntry("deathyear", parsed.get(4));
            laureate.addEntry("biography", "https://nobelprize.org/"+parsed.get(13)+"/laureates/"+parsed.get(12)+"/"+normalizedName+"-bio.html");
            laureate.addEntry("motivation", parsed.get(16));
            
            laureates.add(laureate);
            parsed = helper.parseLine(in);
        }
        return laureates;
    }
}