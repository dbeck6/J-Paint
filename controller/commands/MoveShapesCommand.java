package controller.commands;

import controller.ICommand;
import model.interfaces.IDrawShapesStrategy;
import controller.IUndoable;
import model.shapes.ShapeLists;

import java.awt.*;
import java.io.IOException;
import java.util.Iterator;

public class MoveShapesCommand implements ICommand, IUndoable {

    private Graphics2D graphics;
    private ShapeLists shapeLists;
    private Point start, end;

    public MoveShapesCommand(Graphics2D graphics, ShapeLists shapeLists, Point start, Point end) {
        this.graphics = graphics;
        this.shapeLists = shapeLists;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() throws IOException {
        // can set this up better...
        if(shapeLists.getSelectedShapesList().isEmpty())
            System.out.println("Please select shapes before moving");
        else move();
        // add command to CommandHistory
        CommandHistory.add(this);
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }

    public void move(){
        // clear canvas first...though I think I can encapsulate this
        graphics.clearRect(0,0, 1200, 800);
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0,0,1200,800);

        int deltaX = (int) (end.getX() - start.getX());
        int deltaY = (int) (end.getY() - start.getY());

        Iterator<IDrawShapesStrategy> selectedShapeIterator = shapeLists.createSelectedShapeIterator();
        Iterator<IDrawShapesStrategy> masterShapeIterator = shapeLists.createCurrentShapeIterator();

        while(selectedShapeIterator.hasNext()) {
            IDrawShapesStrategy temp = selectedShapeIterator.next();
            Point dStart = new Point((int)(temp.getStartPoint().getX() + deltaX), (int)(temp.getStartPoint().getY() + deltaY));
            Point dEnd = new Point(temp.getCurrentWidth() + (int) dStart.getX(), temp.getCurrentHeight() + (int) dStart.getY());
            temp.setStartAndEndPoint(dStart, dEnd);
            temp.drawShapes();
        }

        while (masterShapeIterator.hasNext()){
            IDrawShapesStrategy curr = masterShapeIterator.next();
            curr.drawShapes();
        }
    }
}
