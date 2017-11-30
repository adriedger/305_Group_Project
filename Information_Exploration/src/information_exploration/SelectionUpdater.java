/*
 * CMPT 305 - Group Project (Fall 2017)
 * Group 6
 *
 */
package information_exploration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * SelectionUpdater class - contains a list of all prize categories that appear
 * in a given list of laureates.
 *
 * @author Stephen Doyle (doyles8@mymacewan.ca)
 */
public class SelectionUpdater {

    ArrayList<String> selections;

    /**
     * PrizeList - creates a list of prize categories that appear in the given
     * list of laureates
     *
     * @param list - the list of laureates to check
     * @param category - category to find the possible selections for
     */
    public SelectionUpdater(List<Laureate> list, String category) {
        selections = new ArrayList<>();
        String temp;
        category = category.toLowerCase();

        for (Laureate l : list) {
            if (l.getEntry().get(category) != null) {
                temp = l.getEntry().get(category).toString();
                if (!selections.contains(temp) && !temp.equals("category")) {
                    selections.add(temp);
                }
            }
        }
        Collections.sort(selections);
    }

    /**
     * toString - outputs the data in the list of the categories
     *
     * @return - SelectionUpdater object in string form
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        selections.forEach((p) -> {
            builder.append(p);
            builder.append("\n");
        });
        return builder.toString();
    }
}
