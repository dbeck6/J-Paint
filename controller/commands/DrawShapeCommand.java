package controller.commands;

import model.ShapeType;
import model.interfaces.IApplicationState;
import controller.ICommand;
import model.interfaces.IDrawShapesStrategy;
import controller.IUndoable;
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
        IDrawShapesStrategy newShape;

        // maybe make this a switch statement
        if(shapeConfiguration.shapeType == ShapeType.RECTANGLE){
            newShape = new Rectangle(graphics, shapeConfiguration, start, end);
        } else if(shapeConfiguration.shapeType == ShapeType.ELLIPSE){
            newShape = new Ellipse(graphics, shapeConfiguration, start, end);
        } else if(shapeConfiguration.shapeType == ShapeType.TRIANGLE){
            newShape = new Triangle(graphics, shapeConfiguration, start, end);
        } else {throw new IOException();}

        newShape.drawShapes();
        // add newShape to currentShapeList
        shapeLists.getCurrentShapeList().add(newShape);
        // testing...
        System.out.println(shapeLists.getCurrentShapeList().toString());

        // add command to CommandHistory
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        // needs to destroy shape somehow...
    }

    @Override
    public void redo() {

    }
}
