package controller.commands;

import model.interfaces.IApplicationState;
import model.interfaces.ICommand;
import model.shapes.SelectedShapes;
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

    private boolean rightClick = false;

    private ArrayList<Shape> shapes = new ArrayList<>();
    private SelectedShapes selectedShapes = new SelectedShapes();

    public ClickHandler(PaintCanvas canvas, IApplicationState appState){
        this.canvas = canvas;
        this.appState = appState;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }


    @Override
    public void mousePressed(MouseEvent e) {
        // check if right mouse button was used to inverse colors
        if(e.getButton() == MouseEvent.BUTTON3 && rightClick == false) {
            // inverse the colors...
            appState.setInverseColors();
            rightClick = true;
        // set them back to original selection if left click was used after right click
        } else if(e.getButton() == MouseEvent.BUTTON1 && rightClick == true){
            appState.setInverseColors();
            rightClick = false;
        }
        this.start = new Point(e.getX(), e.getY());  }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.end = new Point(e.getX(), e.getY());

        switch (appState.getActiveStartAndEndPointMode()){
            case DRAW:
                command = new DrawShapeCommand(canvas.getGraphics2D(), appState, shapes, start, end);
                break;
            case SELECT:
                command = new SelectShapesCommand(canvas.getGraphics2D(), shapes, selectedShapes, start, end);
                break;
            case MOVE:
                command = new MoveShapesCommand(canvas.getGraphics2D(), selectedShapes, start, end);
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
