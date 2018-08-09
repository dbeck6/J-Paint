package controller.commands;

import controller.ICommand;
import controller.IUndoable;
import model.interfaces.IDrawShapesStrategy;
import model.shapes.ShapeLists;

import java.io.IOException;
import java.util.ArrayList;

public class CopyCommand implements ICommand, IUndoable {

    private ShapeLists masterShapeList;
    private ArrayList<IDrawShapesStrategy> clipBoard;


    public CopyCommand(ShapeLists masterShapeList) {
        this.masterShapeList = masterShapeList;
    }

    @Override
    public void run() throws IOException {
        copy();

        // add command to CommandHistory
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        masterShapeList.clearShapeClipBoard();
        clipBoard = masterShapeList.getShapeClipBoard();

    }

    @Override
    public void redo() {
        copy();
    }

    private void copy() {
           masterShapeList.setShapeClipBoard();
           clipBoard = masterShapeList.getShapeClipBoard();
    }
}
