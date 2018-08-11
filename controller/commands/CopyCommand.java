package controller.commands;

import controller.ICommand;
import controller.IUndoable;
import model.shapes.ShapeLists;

import java.io.IOException;

public class CopyCommand implements ICommand, IUndoable {

    private ShapeLists masterShapeList;

    public CopyCommand(ShapeLists masterShapeList) {
        this.masterShapeList = masterShapeList;
    }

    @Override
    public void run() throws IOException {
        copy();

        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        masterShapeList.clearShapeClipBoard();
    }

    @Override
    public void redo() {
        masterShapeList.setShapeClipBoard();
    }

    private void copy() throws IOException {
        if(!masterShapeList.isCopyReady()) {
            masterShapeList.setShapeClipBoard();
        }else if(masterShapeList.isCopyReady()){
            masterShapeList.clearShapeClipBoard();
        }else throw new IOException();
    }
}
