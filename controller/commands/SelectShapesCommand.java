package controller.commands;

import model.interfaces.ICommand;
import model.interfaces.IUndoable;

import java.awt.*;
import java.io.IOException;

public class SelectShapesCommand implements ICommand, IUndoable {

    private Graphics2D graphics;

    public SelectShapesCommand(Graphics2D graphics){
        this.graphics = graphics;
    }
    @Override
    public void run() throws IOException {
        System.out.println("Made it to SelectShape Command");

        // add command to CommandHistory
        CommandHistory.add(this);
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}
