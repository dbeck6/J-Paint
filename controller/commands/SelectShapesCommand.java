package controller.commands;

import controller.ICommand;
import controller.IUndoable;
import model.interfaces.IDrawShapesStrategy;
import model.shapes.ShapeLists;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class SelectShapesCommand implements ICommand, IUndoable {

    private ShapeLists shapeLists;
    private Point start, end;
    private ArrayList<IDrawShapesStrategy> undoSelectShapes;
    private Iterator<IDrawShapesStrategy> undoSelectShapesIterator;

    public SelectShapesCommand(ShapeLists shapeLists, Point start, Point end){
        this.shapeLists = shapeLists;
        this.start = start;
        this.end = end;
    }
    @Override
    public void run() throws IOException {
        selector();
        // add command to CommandHistory
        CommandHistory.add(this);
    }

    @Override
    public void undo() {

        if(!shapeLists.isSelectReady()){
            for (IDrawShapesStrategy shape: undoSelectShapes) {
                Shape finalS = shape.getShapeParameters();
                shapeLists.getCurrentShapeList().removeIf((IDrawShapesStrategy i) -> i.getShapeParameters().equals(finalS));
            }
            shapeLists.getCurrentShapeList().addAll(undoSelectShapes);
            shapeLists.getSelectedShapesList().removeAll(undoSelectShapes);
        } else {
            shapeLists.getSelectedShapesList().addAll(undoSelectShapes);
            for (IDrawShapesStrategy shape: undoSelectShapes) {
                Shape finalS = shape.getShapeParameters();
                shapeLists.getCurrentShapeList().removeIf((IDrawShapesStrategy i) -> i.getShapeParameters().equals(finalS));
            }
        }
        shapeLists.setSelectReady();
    }

    @Override
    public void redo() {

        if(shapeLists.isSelectReady()){
            for (IDrawShapesStrategy shape: undoSelectShapes) {
                Shape finalS = shape.getShapeParameters();
                shapeLists.getCurrentShapeList().removeIf((IDrawShapesStrategy i) -> i.getShapeParameters().equals(finalS));
            }
            shapeLists.getSelectedShapesList().addAll(undoSelectShapes);
        } else {
            for (IDrawShapesStrategy shape: undoSelectShapes) {
                Shape finalS = shape.getShapeParameters();
                shapeLists.getCurrentShapeList().removeIf((IDrawShapesStrategy i) -> i.getShapeParameters().equals(finalS));
            }
            shapeLists.getCurrentShapeList().addAll(undoSelectShapes);
            shapeLists.getSelectedShapesList().removeAll(undoSelectShapes);
        }
        shapeLists.setSelectReady();
    }

    private  void selector() throws IOException {

        if(shapeLists.isSelectReady()){
            shapeLists.addShapes(shapeLists.getCurrentShapeList(), start, end);
            setUndoSelectShapes();
        } else if (!shapeLists.isSelectReady()) {
            setUndoSelectShapes();
            shapeLists.deselectAllShapes();
        } else throw new IOException();
    }

    private void setUndoSelectShapes(){

        undoSelectShapes = new ArrayList<>(shapeLists.getSelectedShapesList().size());
        undoSelectShapesIterator = shapeLists.createSelectedShapeIterator();
        // clone entire SelectShapes ArrayList
        while(undoSelectShapesIterator.hasNext()){
            undoSelectShapes.add(undoSelectShapesIterator.next().clone());
        }
    }
}
