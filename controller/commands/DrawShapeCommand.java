package controller.commands;

import model.interfaces.IApplicationState;
import model.interfaces.ICommand;
import model.interfaces.IUndoable;
import model.shapes.ShapeConfiguration;
import view.interfaces.IGuiWindow;

import java.io.IOException;

public class DrawShapeCommand implements ICommand, IUndoable {

    private IGuiWindow guiWindow;

    public ShapeConfiguration shapeConfiguration = new ShapeConfiguration();


    public DrawShapeCommand(IGuiWindow guiWindow) {
        this.guiWindow = guiWindow;
    }

    /*public void setShapeCon (ShapeConfiguration shape){
        shape = IApplicationState.setActiveShapeConfiguration();
    }*/

    @Override
    public void run() throws IOException {
        IApplicationState.getCurrentShapeConfiguration(shapeConfiguration);
        System.out.println("Made it to DrawShape Command");
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
