package controller.commands;

import controller.ICommand;
import controller.IUndoable;
import model.interfaces.IDrawShapesStrategy;
import model.shapes.ShapeLists;

import java.io.IOException;
import java.util.ArrayList;

public class PasteCommand implements ICommand, IUndoable {

    private ShapeLists masterShapeList;
    private ArrayList<IDrawShapesStrategy> clipBoard;

    public PasteCommand(ShapeLists masterShapeList) {
        this.masterShapeList = masterShapeList;
    }

    @Override
    public void run() throws IOException {


        // add command to CommandHistory
        CommandHistory.add(this);
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }

    private void paste(){
        if (!masterShapeList.getShapeClipBoard().isEmpty()){

        }
    }
}
