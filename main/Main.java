package main;

import controller.IJPaintController;
import controller.JPaintController;
import model.commands.ClickHandler;
import model.commands.DrawShapeCommand;
import model.commands.MoveShapesCommand;
import model.commands.SelectShapesCommand;
import model.interfaces.ICommand;
import model.persistence.ApplicationState;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.IUiModule;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        IGuiWindow guiWindow = new GuiWindow(new PaintCanvas());
        IUiModule uiModule = new Gui(guiWindow);
        ApplicationState appState = new ApplicationState(uiModule);
        IJPaintController controller = new JPaintController(uiModule, appState);
        controller.setup();

        //testing ClickHandler
        ((GuiWindow) guiWindow).addMouseListener(new ClickHandler());

        //Command section of application TRUE MIGHT NOT BE USEFUL HERE
        while(true){
            ICommand command;
            // check application state for command
            switch (appState.getActiveStartAndEndPointMode()){
                case DRAW:
                    command = new DrawShapeCommand(guiWindow);
                    break;
                case SELECT:
                    command = new SelectShapesCommand(guiWindow);
                    break;
                case MOVE:
                    command = new MoveShapesCommand(guiWindow);
                    break;
                default:
                    throw new Error();
            }
            command.run();
        }

    }
}
