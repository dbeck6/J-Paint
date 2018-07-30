package controller.commands;

import model.interfaces.IApplicationState;
import model.interfaces.ICommand;
import model.interfaces.IDrawShapesStrategy;
import view.gui.PaintCanvas;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

public class ClickHandler extends MouseAdapter {

    // int coordinate values
    private PaintCanvas canvas;
    private IApplicationState appState;
    private Point start, end;
    private ICommand command;

    private ArrayList<IDrawShapesStrategy> shapes = new ArrayList<>();

    public ClickHandler(PaintCanvas canvas, IApplicationState appState){
        this.canvas = canvas;
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
                command = new DrawShapeCommand(canvas.getGraphics2D(), appState, shapes, start, end);
                break;
            case SELECT:
                command = new SelectShapesCommand(canvas.getGraphics2D());
                break;
            case MOVE:
                command = new MoveShapesCommand(canvas.getGraphics2D());
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
