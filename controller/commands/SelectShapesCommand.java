package controller.commands;

import controller.ICommand;
import controller.IUndoable;
import model.shapes.ShapeLists;

import java.awt.*;
import java.io.IOException;

public class SelectShapesCommand implements ICommand, IUndoable {

    private ShapeLists shapeLists;
    private Point start, end;

    public SelectShapesCommand(ShapeLists shapeLists, Point start, Point end){
        this.shapeLists = shapeLists;
        this.start = start;
        this.end = end;
    }
    @Override
    public void run() throws IOException {
        selector();

        // add command to CommandHistory though not sure its needed...
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        try {
            selector();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Selected list " + shapeLists.getSelectedShapesList().toString());
        System.out.println("Current list " + shapeLists.getCurrentShapeList().toString());
    }

    @Override
    public void redo() {
        try {
            selector();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Selected list " + shapeLists.getSelectedShapesList().toString());
        System.out.println("Current list " + shapeLists.getCurrentShapeList().toString());
    }

    public void selector() throws IOException {
        if(shapeLists.isSelectReady()){
            shapeLists.addShapes(shapeLists.getCurrentShapeList(), start, end);
            shapeLists.setSelectReady();
        } else if (!shapeLists.isSelectReady()) {
            shapeLists.deselectAllShapes();
            shapeLists.setSelectReady();
        } else throw new IOException();
    }
}
