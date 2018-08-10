package main;

import controller.IJPaintController;
import controller.JPaintController;
import controller.commands.ClickHandler;
import model.persistence.ApplicationState;
import model.shapes.ShapeLists;
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
        ShapeLists masterShapeList = new ShapeLists();
        // don't forget about below
        masterShapeList.registerObserver(canvas);
        ClickHandler clickHandler = new ClickHandler(canvas, appState, masterShapeList);
        (canvas).addMouseListener(clickHandler);
        IJPaintController controller = new JPaintController(uiModule, appState, masterShapeList);
        controller.setup();
        }
}
