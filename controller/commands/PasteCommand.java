package controller.commands;

import controller.ICommand;
import controller.IUndoable;
import model.interfaces.IDrawShapesStrategy;
import model.shapes.ShapeLists;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class PasteCommand implements ICommand, IUndoable {

    private ShapeLists masterShapeList;
    private ArrayList<IDrawShapesStrategy> clipBoard;
    private int deltaX, deltaY;

    public PasteCommand(ShapeLists masterShapeList) {
        this.masterShapeList = masterShapeList;
    }

    @Override
    public void run() throws IOException {
        paste();

        // add command to CommandHistory
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        for (IDrawShapesStrategy paste: clipBoard) {
            Shape finalS = paste.getShapeParameters();
            masterShapeList.getCurrentShapeList().removeIf((IDrawShapesStrategy i) -> i.getShapeParameters() == finalS);
        }
        masterShapeList.notifyObservers();
    }

    @Override
    public void redo() {
        for (IDrawShapesStrategy paste: clipBoard) {
            masterShapeList.getCurrentShapeList().add(paste);
        }
        masterShapeList.notifyObservers();
    }

    private void paste(){
        if (!masterShapeList.getShapeClipBoard().isEmpty() && masterShapeList.isCopyReady()){

           clipBoard = new ArrayList<>(masterShapeList.getShapeClipBoard().size());
           Iterator<IDrawShapesStrategy> clipBoardIterator = masterShapeList.createShapeClipBoardIterator();

           // clone entire ShapeClipBoard ArrayList
           while(clipBoardIterator.hasNext()){
               clipBoard.add(clipBoardIterator.next().clone());
           }

           Random rand = new Random();
           deltaX = rand.nextInt(300) + 50;
           deltaY = rand.nextInt(300) + 50;

           //System.out.println("deltaX = "+ deltaX + " deltaY = "+ deltaY);

            for (IDrawShapesStrategy paste: clipBoard) {
                masterShapeList.getCurrentShapeList().add(paste);
                Point pStart = new Point((int)(paste.getStartPoint().getX() + deltaX), (int)(paste.getStartPoint().getY() + deltaY));
                Point pEnd = new Point(paste.getCurrentWidth() + (int) pStart.getX(), paste.getCurrentHeight() + (int) pStart.getY());
                paste.setStartAndEndPoint(pStart, pEnd);
            }
           masterShapeList.notifyObservers();
        }
    }
}
