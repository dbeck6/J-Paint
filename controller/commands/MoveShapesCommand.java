package controller.commands;

import model.interfaces.ICommand;
import model.interfaces.IUndoable;
import view.interfaces.IGuiWindow;

import java.io.IOException;

public class MoveShapesCommand implements ICommand, IUndoable {

    private IGuiWindow guiWindow;

    public MoveShapesCommand(IGuiWindow guiWindow) {
        this.guiWindow = guiWindow;
    }

    @Override
    public void run() throws IOException {
        System.out.println("Made it to MoveShape Command");

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
