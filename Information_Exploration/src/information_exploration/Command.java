/*
 * CMPT 305 - Group Project (Fall 2017)
 * Group 6
 *
 */
package information_exploration;

import java.util.List;

/**
 * Command interface - interface used to apply search filters on a laureate
 * list.
 *
 * @author Stephen Doyle (doyles8@mymacewan.ca)
 */
public interface Command {

    /**
     * execute() - executes a command
     *
     * @return - list of laureates with a filter applied to it
     */
    public List<Laureate> execute();

    /**
     * undo() - restores the list to its state prior to a stream filter being
     * applied
     *
     * @return -list of laureates prior to filter being applied to it
     */
    public List<Laureate> undo();

}
