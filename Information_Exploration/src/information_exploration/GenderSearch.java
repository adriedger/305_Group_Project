/*
 * CMPT 305 - Group Project (Fall 2017)
 * Group 6
 *
 */
package information_exploration;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Stephen Doyle (doyles8@mymacewan.ca)
 */
public class GenderSearch implements Command {

    private final List<Laureate> oldList;
    private final String value;

    /**
     * CategorySearch() - creates a new command to search a list of laureates
     * based on the given arguments
     *
     * @param list - list to apply the search filter too
     * @param value - the value the categories are being compared too
     */
    public GenderSearch(List<Laureate> list, String value) {
        this.oldList = list;
        this.value = value.toLowerCase();
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
            if (l.getEntry().get("gender") != null) {
                if (l.getEntry().get("gender").toString().toLowerCase().equals(value)) {
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
