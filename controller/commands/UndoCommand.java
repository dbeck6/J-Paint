package controller.commands;

import controller.ICommand;

import java.io.IOException;

public class UndoCommand implements ICommand {

    @Override
    public void run() throws IOException {
        CommandHistory.undo();
    }

}
