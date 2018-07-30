package main;

import controller.IJPaintController;
import controller.JPaintController;
import controller.commands.ClickHandler;
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
        ((GuiWindow) guiWindow).addMouseListener(new ClickHandler(guiWindow, appState));

        }
}
