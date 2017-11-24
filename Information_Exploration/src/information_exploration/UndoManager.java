/*
 * CMPT 305 - Group Project (Fall 2017)
 * Group 6
 *
 */
package information_exploration;

import java.util.List;
import java.util.Stack;

/**
 * UndoManager class - contains an undo manager that stores command objects
 *
 * @author Stephen Doyle (doyles8@mymacewan.ca)
 */
public class UndoManager {

    Stack<Command> undoStack;

    /**
     * UndoManager() - creates a new UndoManager object, contains a Stack of
     * commands.
     */
    public UndoManager() {
        undoStack = new Stack<>();
    }

    /**
     * addCommand() - adds a new command into the undoStack
     *
     * @param newCommand - command being added to the undoStack
     */
    public void addCommand(Command newCommand) {
        undoStack.add(newCommand);
    }

    /**
     * undoCommand() - pops a command off the undoStack and applies its undo
     *
     * @return - the list prior to a command applying its filter to it (stored
     * in the command)
     */
    public List<Laureate> undoCommand() {
        if (undoStack.isEmpty()) {
            System.out.println("Nothing to undo");
            return null;
        } else {
            Command newCommand = undoStack.pop();
            return newCommand.undo();
        }
    }
    
    public boolean canReset() {
        return (!undoStack.isEmpty());
    }
    
    public List<Laureate> resetHome() {
        
        Command newCommand = null;
        if (undoStack.isEmpty()) {
            System.out.println("Nothing to undo");
            return null;
        } else {
            while(!undoStack.isEmpty()) {
                newCommand = undoStack.pop();
            }
            if (newCommand != null) 
                return newCommand.undo();
        }
        return null;
    }
}

