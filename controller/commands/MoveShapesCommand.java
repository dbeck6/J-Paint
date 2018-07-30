package controller.commands;

import model.interfaces.ICommand;
import model.interfaces.IUndoable;

import java.awt.*;
import java.io.IOException;

public class MoveShapesCommand implements ICommand, IUndoable {

    private Graphics2D graphics;

    public MoveShapesCommand(Graphics2D graphics) {
        this.graphics = graphics;
    }

    @Override
    public void run() throws IOException {
        System.out.println("Made it to MoveShape Command");

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
