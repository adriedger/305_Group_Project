/*
 * Macewan University CMPT 305 Professor: Brian Brookwell
 */
package information_exploration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author adriedger
 */
public class Laureate {
    private Map<String, String> entries = new LinkedHashMap<>();
    
    public void addEntry(String key, String data){
        entries.put(key, data);        
    }    
    
    public void output(){
        for(Map.Entry<String, String> entry : entries.entrySet()){
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("");
    }    
}
