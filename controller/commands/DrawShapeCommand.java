package controller.commands;

import model.interfaces.IApplicationState;
import model.interfaces.ICommand;
import model.interfaces.IUndoable;
import model.shapes.ShapeConfiguration;
import view.interfaces.IGuiWindow;

import java.io.IOException;

public class DrawShapeCommand implements ICommand, IUndoable {

    private IGuiWindow guiWindow;
    private IApplicationState appState;

    private ShapeConfiguration shapeConfiguration = new ShapeConfiguration();


    public DrawShapeCommand(IGuiWindow guiWindow, IApplicationState appState) {
        this.guiWindow = guiWindow;
        this.appState = appState;
    }

    @Override
    public void run() throws IOException {
        appState.getCurrentShapeConfiguration(shapeConfiguration);
       /* System.out.println("Made it to DrawShape Command");
        System.out.println(shapeConfiguration.primaryColor);*/


        // add command to CommandHistory
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        // needs to destroy shape somehow...
    }

    @Override
    public void redo() {

    }
}
