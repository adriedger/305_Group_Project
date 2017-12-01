/*
 * Macewan University CMPT 305 Professor: Brian Brookwell
 */
package information_exploration;

import static java.lang.Character.toUpperCase;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author adriedger + Stephen Doyle (doyles8@mymacewan.ca)
 */
public class Laureate implements Comparable<Laureate>, Comparator<Laureate> {

    private final Map<String, String> entries = new LinkedHashMap<>();

    public void addEntry(String key, String data) {
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

    public void output() {
        for (Map.Entry<String, String> entry : entries.entrySet()) {
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
        char pcat[] = entries.get("prize").toCharArray();
        pcat[0] = toUpperCase(pcat[0]);
        String pcats = new String(pcat);
        builder.append(pcats);
        builder.append(" ");
        builder.append(entries.get("year"));
                

        /*
       for(Map.Entry<String, String> entry : entries.entrySet()){
           builder.append(entry.getKey()).append(": ").append(entry.getValue());
        } 
         */
        return builder.toString();
    }

    /**
     * compareTo - compares the year category to that of a given laureate (Used
     * for collection sorting)
     *
     * @param o1 - laureate object being compared to
     * @return - string comparison of year values
     */
    @Override
    public int compareTo(Laureate o1) {
        return this.entries.get("year").compareTo(o1.entries.get("year"));
    }

    /**
     * compare - compares two laureate objects based on alphabetical name
     *
     * @param o1 - first laureate object being compared
     * @param o2 - second laureate object being compared
     * @return - string comparison of name values
     */
    @Override
    public int compare(Laureate o1, Laureate o2) {
        return (o1.entries.get("name")).compareTo(o2.entries.get("name"));
    }

}
