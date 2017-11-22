/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package information_exploration;

import java.util.List;
import java.util.Stack;

/**
 *
 * @author Stephen Doyle (doyles8@mymacewan.ca)
 */
public class UndoManager {

    Stack<Command> undoStack;

    public UndoManager() {
        undoStack = new Stack<>();
    }

    public void addCommand(Command newCommand) {
        undoStack.add(newCommand);
    }

    public List<Laureate> undoCommand() {
        if (undoStack.isEmpty()) {
            System.out.println("Nothing to undo");
            return null;
        } else {
            Command newCommand = undoStack.pop();
            return newCommand.undo();
        }
    }
}
