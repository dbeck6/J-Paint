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
    private IDrawShapesStrategy newShape;
    //private IDrawShapesStrategy undoRedoShape;


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

        newShape.drawShapes();
        // add newShape to currentShapeList
        shapeLists.getCurrentShapeList().add(newShape);
        // testing...
        //System.out.println(shapeLists.getCurrentShapeList().toString());

        // add command to CommandHistory
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        // removes the last element of the shape list (most recently drawn shape) AH PROBABLY NEED ANOTHER ARRAYLIST...but maybe not
        //undoRedoShape = shapeLists.getCurrentShapeList().get(shapeLists.getCurrentShapeList().size()-1);
        shapeLists.getCurrentShapeList().remove(newShape);
        //clear canvas
        graphics.clearRect(0,0, 1200, 800);
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0,0,1200,800);
        for (IDrawShapesStrategy shape: shapeLists.getCurrentShapeList()){
            shape.drawShapes();
        }
    }

    @Override
    public void redo() {
        // add newShape back into list
        shapeLists.getCurrentShapeList().add(newShape);
        //clear canvas
        graphics.clearRect(0,0, 1200, 800);
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0,0,1200,800);
        for (IDrawShapesStrategy shape: shapeLists.getCurrentShapeList()){
            shape.drawShapes();
        }
    }
}
