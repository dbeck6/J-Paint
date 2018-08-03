package controller.commands;

import model.interfaces.ICommand;
import model.interfaces.IUndoable;
import model.shapes.SelectedShapes;

import java.awt.*;
import java.io.IOException;

public class MoveShapesCommand implements ICommand, IUndoable {

    private Graphics2D graphics;
    private SelectedShapes selectedShapes;
    private Point start, end;

    public MoveShapesCommand(Graphics2D graphics, SelectedShapes selectedShapes, Point start, Point end) {
        this.graphics = graphics;
        this.selectedShapes = selectedShapes;
        this.start = start;
        this.end = end;
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
