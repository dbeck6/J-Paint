package controller.commands;

import controller.ICommand;
import controller.IUndoable;
import model.shapes.ShapeLists;

import java.awt.*;
import java.io.IOException;

public class SelectShapesCommand implements ICommand, IUndoable {

    private Graphics2D graphics;
    private ShapeLists shapeLists;
    private Point start, end;

    public SelectShapesCommand(Graphics2D graphics, ShapeLists shapeLists, Point start, Point end){
        this.graphics = graphics;
        this.shapeLists = shapeLists;
        this.start = start;
        this.end = end;
    }
    @Override
    public void run() throws IOException {
            selector();

        // add command to CommandHistory though not sure its needed...
        //CommandHistory.add(this);
    }

    @Override
    public void undo() {}

    @Override
    public void redo() {}

    public void selector(){
        if(shapeLists.getSelectedShapesList().isEmpty()){
            shapeLists.addShapes(shapeLists.getCurrentShapeList(), start, end);
        } else {
            shapeLists.deselectAllShapes();
            //System.out.println("This is deselect " + shapeLists.getSelectedShapesList().toString());
        }
    }
}
