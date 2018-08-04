package controller.commands;

import model.interfaces.IApplicationState;
import model.interfaces.ICommand;
import model.shapes.SelectedShapes;
import view.gui.PaintCanvas;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.ArrayList;

public class ClickHandler extends MouseAdapter implements MouseMotionListener {

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
        // clicks in select mode clear selected shapes
        /*if(appState.getActiveStartAndEndPointMode() == StartAndEndPointMode.SELECT && selectedShapes != null){
            selectedShapes.deselectAllShapes();
            System.out.println(selectedShapes.toString());
        } else {
            this.start = new Point(e.getX(), e.getY());
            this.end = new Point(e.getX()+ 4, e.getY() +4);

            command = new SelectShapesCommand(canvas.getGraphics2D(), shapes, selectedShapes, start, end);

            try {
                command.run();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }*/
      /*  if(appState.getActiveStartAndEndPointMode() == StartAndEndPointMode.SELECT && selectedShapes == null){
            this.start = new Point(e.getX(), e.getY());
            this.end = new Point(e.getX()+ 4, e.getY() +4);

            command = new SelectShapesCommand(canvas.getGraphics2D(), shapes, selectedShapes, start, end);

            try {
                command.run();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            //System.out.println(selectedShapes.toString());
        } else {
            selectedShapes.deselectAllShapes();
            System.out.println(selectedShapes.toString());

        }*/
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
    public void mouseDragged(MouseEvent e){

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.end = new Point(e.getX(), e.getY());
        //System.out.println(start.toString() +" " + end.toString());

        switch (appState.getActiveStartAndEndPointMode()){
            case DRAW:
                command = new DrawShapeCommand(canvas.getGraphics2D(), appState, shapes, start, end);
                break;
            case SELECT:
                //trial selection
              /*  if(selectedShapes.getSelectedShapes().isEmpty() == true){
                    //this.end = new Point(e.getX()+ 4, e.getY() +4);*/
                    command = new SelectShapesCommand(canvas.getGraphics2D(), shapes, selectedShapes, start, end);
               /* } else {
                    selectedShapes.deselectAllShapes();
                    System.out.println("This is deselect " + selectedShapes.getSelectedShapes().toString());
                }*/

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
