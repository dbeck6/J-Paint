package controller.commands;

import controller.ICommand;
import controller.IUndoable;
import model.shapes.ShapeLists;

import java.io.IOException;

public class DeleteCommand implements ICommand, IUndoable {

    private ShapeLists masterShapeList;

    public DeleteCommand(ShapeLists masterShapeList) {
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
}
