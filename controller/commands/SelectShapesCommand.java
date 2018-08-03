package controller.commands;

import model.interfaces.ICommand;
import model.interfaces.IUndoable;
import model.shapes.SelectedShapes;

import java.awt.*;
import java.io.IOException;

public class SelectShapesCommand implements ICommand, IUndoable {

    private Graphics2D graphics;
    private SelectedShapes selectedShapes;
    private Point start, end;

    public SelectShapesCommand(Graphics2D graphics, SelectedShapes selectedShapes, Point start, Point end){
        this.graphics = graphics;
        this.selectedShapes = selectedShapes;
        this.start = start;
        this.end = end;
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
