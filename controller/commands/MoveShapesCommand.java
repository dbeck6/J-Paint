package controller.commands;

import controller.ICommand;
import controller.IUndoable;
import model.interfaces.IDrawShapesStrategy;
import model.shapes.ShapeLists;

import java.awt.*;
import java.io.IOException;
import java.util.Iterator;

public class MoveShapesCommand implements ICommand, IUndoable {

    private ShapeLists shapeLists;
    private Point start, end;
    private int deltaX, deltaY;

    private Iterator<IDrawShapesStrategy> selectedShapeIterator;

    public MoveShapesCommand(ShapeLists shapeLists, Point start, Point end) {
        this.shapeLists = shapeLists;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() throws IOException {
        // can set this up better...
        if(shapeLists.getSelectedShapesList().isEmpty())
            System.out.println("Please select shapes before moving");
        else move();

        // add command to CommandHistory
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        undoMove(deltaX, deltaY);
    }

    @Override
    public void redo() {
        redoMove(deltaX, deltaY);
    }

    private void move(){

        deltaX = (int) (end.getX() - start.getX());
        deltaY = (int) (end.getY() - start.getY());

        selectedShapeIterator = shapeLists.createSelectedShapeIterator();

        // add a cloneable ArrayList to help multiple moves?

        move(selectedShapeIterator);
    }

    private void move(Iterator<IDrawShapesStrategy> selectedShapeIterator){
        while(selectedShapeIterator.hasNext()) {
            IDrawShapesStrategy temp = selectedShapeIterator.next();
            Point dStart = new Point((int)(temp.getStartPoint().getX() + deltaX), (int)(temp.getStartPoint().getY() + deltaY));
            Point dEnd = new Point(temp.getCurrentWidth() + (int) dStart.getX(), temp.getCurrentHeight() + (int) dStart.getY());
            temp.setStartAndEndPoint(dStart, dEnd);
        }
        shapeLists.notifyObservers();
    }

    private void undoMove(int deltaX, int deltaY){


        selectedShapeIterator = shapeLists.createSelectedShapeIterator();

        while(selectedShapeIterator.hasNext()) {
            IDrawShapesStrategy temp = selectedShapeIterator.next();
            Point dStart = new Point((int)(temp.getStartPoint().getX() - deltaX), (int)(temp.getStartPoint().getY() - deltaY));
            Point dEnd = new Point((int) (temp.getEndPoint().getX() - deltaX), (int) (temp.getEndPoint().getY() - deltaY));
            temp.setStartAndEndPoint(dStart, dEnd);
        }
        shapeLists.notifyObservers();
    }

    private void redoMove(int deltaX, int deltaY){

        selectedShapeIterator = shapeLists.createSelectedShapeIterator();

        while(selectedShapeIterator.hasNext()) {
            IDrawShapesStrategy temp = selectedShapeIterator.next();
            Point dStart = new Point((int)(temp.getStartPoint().getX() + deltaX), (int)(temp.getStartPoint().getY() + deltaY));
            Point dEnd = new Point((int) (temp.getEndPoint().getX() + deltaX), (int) (temp.getEndPoint().getY() + deltaY));
            temp.setStartAndEndPoint(dStart, dEnd);
        }
        shapeLists.notifyObservers();
    }
}
