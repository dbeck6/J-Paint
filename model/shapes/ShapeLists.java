package model.shapes;

import model.interfaces.IDrawShapesStrategy;
import model.interfaces.ISelectShapeIterator;

import java.awt.*;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

public class ShapeLists extends Rectangle implements ISelectShapeIterator {

    private ArrayList<IDrawShapesStrategy> currentShapeList;
    private ArrayList<IDrawShapesStrategy> selectedShapesList;
    private ArrayList<IDrawShapesStrategy> shapeClipBoard;

    public ShapeLists(){
        currentShapeList = new ArrayList<>();
        selectedShapesList = new ArrayList<>();
        shapeClipBoard = new ArrayList<>();
    }

    public void addShapes(ArrayList<IDrawShapesStrategy> shapes, Point start, Point end){
        int x = Math.min(start.x, end.x);
        int y = Math.min(start.y, end.y);
        int width = Math.abs(start.x - end.x);
        int height = Math.abs(start.y - end.y);
        Rectangle rect = new Rectangle(x, y, width, height);
        Shape s;
        for(IDrawShapesStrategy shape: shapes){
            s = shape.getShapeParameters();
            if (rect.getBounds().intersects(s.getBounds()) || s.contains(x, y)){
                selectedShapesList.add(shape);
                System.out.println("Collected shape " + shape.getShapeParameters().toString());
            }
        }
        // remove selected shapes from currentShapeList
        for (IDrawShapesStrategy shape: selectedShapesList) {
            currentShapeList.remove(shape);
        }
        System.out.println("Original shapelist " + currentShapeList.toString());

    }

    public void setShapeClipBoard(){
        shapeClipBoard = selectedShapesList;
        System.out.println("ShapeClipBoard is " + selectedShapesList.toString());

    }

    public ArrayList<IDrawShapesStrategy> getCurrentShapeList(){ return currentShapeList; }

    public ArrayList<IDrawShapesStrategy> getSelectedShapesList(){ return selectedShapesList; }

    public ArrayList<IDrawShapesStrategy> getShapeClipBoard(){return shapeClipBoard;}

    public void deselectAllShapes(){
        for(IDrawShapesStrategy shape: selectedShapesList) {
            currentShapeList.add(shape);
        }
        selectedShapesList.removeAll(selectedShapesList);
        System.out.println("Original shapelist " + currentShapeList.toString());
        System.out.println("Selected shapelist " + selectedShapesList.toString());
    }

    public void clearShapeClipBoard(){
        shapeClipBoard.removeAll(shapeClipBoard);
        System.out.println("ShapeClipBoard is " + selectedShapesList.toString());

    }

    @Override
    public Iterator<IDrawShapesStrategy> createCurrentShapeIterator() { return currentShapeList.iterator(); }

    @Override
    public Iterator<IDrawShapesStrategy> createSelectedShapeIterator() { return selectedShapesList.iterator(); }

    @Override
    public Iterator<IDrawShapesStrategy> createShapeClipBoardIterator() {
        return shapeClipBoard.iterator();
    }
}
