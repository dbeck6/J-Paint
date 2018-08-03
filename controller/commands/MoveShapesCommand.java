package controller.commands;

import model.interfaces.ICommand;
import model.interfaces.IDrawShapesStrategy;
import model.interfaces.IUndoable;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class MoveShapesCommand implements ICommand, IUndoable {

    private Graphics2D graphics;
    private ArrayList<IDrawShapesStrategy> shapes;
    private Point start, end;

    public MoveShapesCommand(Graphics2D graphics, ArrayList<IDrawShapesStrategy> shapes, Point start, Point end) {
        this.graphics = graphics;
        this.shapes = shapes;
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
