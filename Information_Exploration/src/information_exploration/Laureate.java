/*
 * Macewan University CMPT 305 Professor: Brian Brookwell
 */
package information_exploration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author adriedger
 */
public class Laureate {
    private final Map<String, String> entries = new LinkedHashMap<>();
    
    public void addEntry(String key, String data){
        entries.put(key, data);        
    }

    /**
     * getEntry() - returns the entries linked hash map from a laureate object
     * 
     * @return linked hash map of laureate info
     */
    public Map getEntry() {
        return entries;
    }
    
    public void output(){
        for(Map.Entry<String, String> entry : entries.entrySet()){
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("");
    }

    /**
     * toString() - converts laureate object to output in string format
     * 
     * @return - string containing a laureate object's information
     */
    @Override
    public String toString() {
       StringBuilder builder = new StringBuilder();
       builder.append(entries.get("name"));
       builder.append("\n");
       builder.append(entries.get("prize"));
       builder.append(" ");
       builder.append(entries.get("year"));
       //builder.append("\n");
       
       /*
       for(Map.Entry<String, String> entry : entries.entrySet()){
           builder.append(entry.getKey()).append(": ").append(entry.getValue());
        } 
        */
       
       return builder.toString();
    }

}
