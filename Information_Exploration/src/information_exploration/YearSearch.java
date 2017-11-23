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
 * @author Brandon Carl (carlb2@mymacewan.ca)
 */
public class YearSearch implements Command {

    private final List<Laureate> oldList;   
    private final int start;
    private final int finish;

    /**
     * CategorySearch() - creates a new command to search a list of laureates
     * based on the given arguments
     *
     * @param list - list to apply the search filter too
     * @param start - the year to start compare to
     * @param finish - the year to finish compare to
     */
    public YearSearch(List<Laureate> list, int start, int finish) {
        this.oldList = list;
        this.start = start;
        this.finish = finish;
    }

    /**
     * execute() - Executes a search for a range of years entered by the user.
     *
     * @return - new list of laureates with the applied filter for years
     */
    @Override
    public List<Laureate> execute() {

        List<Laureate> newList = new ArrayList<>();
        String[] temp;
        int flag = 0;

        for (Laureate l : oldList) {
            // Checks if the laureate award year is within the range
            if (!l.getEntry().get("year").equals("") && !l.getEntry().get("year").equals("year")) {
                if (Integer.parseInt((String) l.getEntry().get("year")) <= this.finish 
                        && Integer.parseInt((String) l.getEntry().get("year")) >= this.start) {
                    newList.add(l);
                    flag = 1;
                }
            }
            // Checks if birthyear is within the range
            if (l.getEntry().get("birthyear") != null && !l.getEntry().get("birthyear").equals("") && !l.getEntry().get("birthyear").equals("born")) {
                temp = l.getEntry().get("birthyear").toString().split("-");
                if (Integer.parseInt(temp[0]) <= this.finish && Integer.parseInt(temp[0]) >= this.start) {
                    if (flag != 1)
                        newList.add(l);
                }
            }
            // Checks if deathyear is within the range
            if (l.getEntry().get("deathyear") != null && !l.getEntry().get("deathyear").equals("") && !l.getEntry().get("deathyear").equals("died")) {
                temp = l.getEntry().get("deathyear").toString().split("-");
                if (Integer.parseInt(temp[0]) <= this.finish && Integer.parseInt(temp[0]) >= this.start) {
                    if (flag != 1)
                        newList.add(l);
                }
            }
            
            flag = 0;

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