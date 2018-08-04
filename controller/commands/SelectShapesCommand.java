package controller.commands;

import model.interfaces.ICommand;
import model.interfaces.IUndoable;
import model.shapes.SelectedShapes;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class SelectShapesCommand implements ICommand, IUndoable {

    private Graphics2D graphics;
    private ArrayList<Shape> shapes;
    private SelectedShapes selectedShapes;
    private Point start, end;

    public SelectShapesCommand(Graphics2D graphics, ArrayList<Shape> shapes, SelectedShapes selectedShapes, Point start, Point end){
        this.graphics = graphics;
        this.shapes = shapes;
        this.selectedShapes = selectedShapes;
        this.start = start;
        this.end = end;
    }
    @Override
    public void run() throws IOException {
        //System.out.println("reached selection command");
        selector();
        // add command to CommandHistory
        CommandHistory.add(this);
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }

    public void selector(){
        if(selectedShapes.getSelectedShapes().isEmpty()){
            selectedShapes.addShapes(this.shapes, this.start, this.end);
            //System.out.println("Collected shape " + selectedShapes.getSelectedShapes().toString());
        } else {
            selectedShapes.deselectAllShapes();
           // System.out.println("This is deselect " + selectedShapes.getSelectedShapes().toString());
        }
    }
}
