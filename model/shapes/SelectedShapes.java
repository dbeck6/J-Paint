package model.shapes;

import model.interfaces.ISelectShapeIterator;

import java.awt.*;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

public class SelectedShapes extends Rectangle implements ISelectShapeIterator {

    private ArrayList<Shape> selectedShapes;

    public SelectedShapes(){
        selectedShapes = new ArrayList<>();
    }

    public void addShapes(ArrayList<Shape> shapes, Point start, Point end){
        int x = Math.min(start.x, end.x);
        int y = Math.min(start.y, end.y);
        int width = Math.abs(start.x - end.x);
        int height = Math.abs(start.y - end.y);
        Rectangle rect = new Rectangle(x, y, width, height);
        for(Shape shape: shapes){
            //second part of OR statement may not be necessary
            if (rect.getBounds().intersects(shape.getBounds()) || shape.contains(x, y)){
                selectedShapes.add(shape);
               // System.out.println("Collected shape " + shape.toString());
               // System.out.println(selectedShapes.toString());
            }
        }
    }

    public ArrayList<Shape> getSelectedShapes(){
        return selectedShapes;
    }

    public void deselectAllShapes(){
        selectedShapes.removeAll(selectedShapes);
    }

    @Override
    public Iterator<Shape> createIterator() {
        return selectedShapes.iterator();
    }
}
