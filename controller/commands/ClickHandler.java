package controller.commands;

import controller.ICommand;
import model.StartAndEndPointMode;
import model.interfaces.IApplicationState;
import model.shapes.ShapeLists;
import view.gui.PaintCanvas;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class ClickHandler implements MouseListener {

    private PaintCanvas canvas;
    private IApplicationState appState;
    private Point start, end;
    private ICommand command;

    private boolean rightClick = false;

    private ShapeLists masterShapeList;

    public ClickHandler(PaintCanvas canvas, IApplicationState appState, ShapeLists masterShapeList){
        this.canvas = canvas;
        this.appState = appState;
        this.masterShapeList = masterShapeList;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // check if right mouse button was used to inverse colors
        if(e.getButton() == MouseEvent.BUTTON3 && !rightClick && appState.getActiveStartAndEndPointMode() == StartAndEndPointMode.DRAW) {
            // inverse the colors...
            appState.setInverseColors();
            rightClick = true;
        // set them back to original selection if left click was used after right click
        } else if(e.getButton() == MouseEvent.BUTTON1 && rightClick && appState.getActiveStartAndEndPointMode() == StartAndEndPointMode.DRAW){
            appState.setInverseColors();
            rightClick = false;
        }
        this.start = e.getPoint();  }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.end = e.getPoint();

        switch (appState.getActiveStartAndEndPointMode()){
            case DRAW:
                command = new DrawShapeCommand(canvas.getGraphics2D(), appState, masterShapeList, start, end);
                break;
            case SELECT:
                // if shapes have been selected, right mouse click will change the shape parameters
                if(e.getButton() == MouseEvent.BUTTON3 && !masterShapeList.isSelectReady()){
                    command = new ChangeSelectedShapesCommand(appState, masterShapeList);
                } else
                command = new SelectShapesCommand(masterShapeList, start, end);
                break;
            case MOVE:
                command = new MoveShapesCommand(masterShapeList, start, end);
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


