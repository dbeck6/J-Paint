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
        PaintCanvas canvas = new PaintCanvas();
        IGuiWindow guiWindow = new GuiWindow(canvas);
        IUiModule uiModule = new Gui(guiWindow);
        ApplicationState appState = new ApplicationState(uiModule);
        ClickHandler clickHandler = new ClickHandler(canvas, appState);
        (canvas).addMouseListener(clickHandler);
        IJPaintController controller = new JPaintController(uiModule, appState, clickHandler);
        controller.setup();
        }
}
