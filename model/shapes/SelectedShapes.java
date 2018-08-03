package model.shapes;

import model.interfaces.IDrawShapesStrategy;
import model.interfaces.ISelectShapeIterator;

import java.awt.*;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

public class SelectedShapes extends Rectangle implements ISelectShapeIterator {

    private ArrayList<IDrawShapesStrategy> selectedShapes;

    public SelectedShapes(){
        selectedShapes = new ArrayList<>();
    }

    public void addShapes(ArrayList<IDrawShapesStrategy> shapes, Point start, Point end){
        double x = Math.min(start.x, end.x);
        double y = Math.min(start.y, end.y);
        double width = Math.abs(start.x - end.x);
        double height = Math.abs(start.y - end.y);
        for(IDrawShapesStrategy shape: shapes){
            if (shape.intersects(x, y, width, height)){
                selectedShapes.add(shape);
            }
        }
    }

    public ArrayList<IDrawShapesStrategy> getSelectedShapes(){
        return selectedShapes;
    }

    public void deselectAllShapes(){
        selectedShapes.removeAll(selectedShapes);
    }

    @Override
    public Iterator<IDrawShapesStrategy> createIterator() {
        return selectedShapes.iterator();
    }
}
