package controller.commands;

import controller.ICommand;
import controller.IUndoable;
import model.ShapeType;
import model.interfaces.IApplicationState;
import model.interfaces.IDrawShapesStrategy;
import model.shapes.*;
import model.shapes.Rectangle;

import java.awt.*;
import java.io.IOException;

public class DrawShapeCommand implements ICommand, IUndoable {

    private Graphics2D graphics;
    private IApplicationState appState;
    private Point start, end;
    private ShapeConfiguration shapeConfiguration = new ShapeConfiguration();
    private ShapeLists shapeLists;
    private IDrawShapesStrategy newShape;

    public DrawShapeCommand(Graphics2D graphics, IApplicationState appState, ShapeLists shapesLists, Point start, Point end) {
        this.graphics = graphics;
        this.appState = appState;
        this.shapeLists = shapesLists;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() throws IOException {
        appState.getCurrentShapeConfiguration(shapeConfiguration);

        // maybe make this a switch statement
        if(shapeConfiguration.shapeType == ShapeType.RECTANGLE){
            newShape = new Rectangle(graphics, shapeConfiguration, start, end);
        } else if(shapeConfiguration.shapeType == ShapeType.ELLIPSE){
            newShape = new Ellipse(graphics, shapeConfiguration, start, end);
        } else if(shapeConfiguration.shapeType == ShapeType.TRIANGLE){
            newShape = new Triangle(graphics, shapeConfiguration, start, end);
        } else {throw new IOException();}

        // add newShape to currentShapeList
        shapeLists.getCurrentShapeList().add(newShape);
        shapeLists.notifyObservers();
        // add command to CommandHistory
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        Shape finalS = newShape.getShapeParameters();
        shapeLists.getCurrentShapeList().removeIf((IDrawShapesStrategy i) -> i.getShapeParameters().equals(finalS));
        //clear canvas
        shapeLists.notifyObservers();
    }

    @Override
    public void redo() {
        // add newShape back into list
        shapeLists.getCurrentShapeList().add(newShape);
        //clear canvas
        shapeLists.notifyObservers();
    }
}
