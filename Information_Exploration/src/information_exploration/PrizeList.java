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
 * PrizeList class - contains a list of all prize categories that appear in a
 * given list of laureates.
 *
 * @author Stephen Doyle (doyles8@mymacewan.ca)
 */
public class PrizeList {

    ArrayList<String> prizes;

    /**
     * PrizeList - creates a list of prize categories that appear in the given
     * list of laureates
     *
     * @param list - the list of laureates to check
     */
    public PrizeList(List<Laureate> list) {
        prizes = new ArrayList<>();
        String temp;

        for (Laureate l : list) {
            if (l.getEntry().get("prize") != null) {
                temp = l.getEntry().get("prize").toString();
                if (!prizes.contains(temp) && !temp.equals("category")) {
                    prizes.add(temp);
                }
            }
        }
        Collections.sort(prizes);
    }

    /**
     * toString - outputs the data in the list of prize categories
     *
     * @return - PrizeList object in string format
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        prizes.forEach((p) -> {
            builder.append(p);
            builder.append("\n");
        });
        return builder.toString();
    }
}
