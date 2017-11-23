/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package information_exploration;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Stephen Doyle (doyles8@mymacewan.ca)
 */
public class GeneralSearch implements Command{
   private final List<Laureate> oldList;
   private final String value;
 
    public GeneralSearch(List<Laureate> list, String value) {
        this.oldList = list;
        this.value = value;
    }

    /**
     * execute() - executes the search command
     *
     * @return - new list of laureates with the applied filter
     */
    @Override
    public List<Laureate> execute() {

        List<Laureate> newList = new ArrayList<>();

        for (Laureate l : oldList) {
            if (l.getEntry().values() != null) {
                if (l.getEntry().values().contains(value)) {
                    newList.add(l);
                }
            }
        }
        return newList;
    }

    /**
     * undo() - rolls the list back to its state prior to the search filter
     *
     * @return - the list prior to apply the search filter
     */
    @Override
    public List<Laureate> undo() {
        return oldList;
    }
    
}

    

