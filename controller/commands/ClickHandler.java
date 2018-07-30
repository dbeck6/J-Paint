package controller.commands;

import model.interfaces.IApplicationState;
import model.interfaces.ICommand;
import model.interfaces.IDrawShapesStrategy;
import view.interfaces.IGuiWindow;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;

public class ClickHandler extends MouseAdapter {

    // int coordinate values
    private IGuiWindow guiWindow;
    private IApplicationState appState;
    private Point start, end;
    private ICommand command;
    private List<IDrawShapesStrategy> shapes;

    public ClickHandler(IGuiWindow guiWindow, IApplicationState appState){
        this.guiWindow = guiWindow;
        this.appState = appState;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }


    @Override
    public void mousePressed(MouseEvent e) {
        this.start = new Point(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.end = new Point(e.getX(), e.getY());

        switch (appState.getActiveStartAndEndPointMode()){
            case DRAW:
                command = new DrawShapeCommand(guiWindow, appState, start, end);
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
        try {
            command.run();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
