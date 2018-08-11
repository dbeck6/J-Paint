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
        try {
            copy();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*masterShapeList.clearShapeClipBoard();
        masterShapeList.setCopyReady();*/
    }

    @Override
    public void redo() {
        try {
            copy();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*masterShapeList.setShapeClipBoard();
        masterShapeList.setCopyReady();*/
    }

    private void copy() throws IOException {
        if(!masterShapeList.isCopyReady()) {
            masterShapeList.setShapeClipBoard();
        }else if(masterShapeList.isCopyReady()){
            masterShapeList.clearShapeClipBoard();
        }else throw new IOException();
    }
}
