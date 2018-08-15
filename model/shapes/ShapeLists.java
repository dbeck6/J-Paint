package model.shapes;

import model.interfaces.IDrawShapesStrategy;
import model.interfaces.ISelectShapeIterator;
import model.interfaces.IShapeSubject;
import view.gui.PaintCanvas;

import java.awt.*;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

public class ShapeLists extends Rectangle implements ISelectShapeIterator, IShapeSubject {

    private ArrayList<IDrawShapesStrategy> currentShapeList;
    private ArrayList<IDrawShapesStrategy> selectedShapesList;
    private ArrayList<IDrawShapesStrategy> shapeClipBoard;
    private PaintCanvas canvas;
    private boolean selectReady = true;
    private boolean copyReady = false;

    public ShapeLists(){
        currentShapeList = new ArrayList<>();
        selectedShapesList = new ArrayList<>();
        shapeClipBoard = new ArrayList<>();
    }

    public boolean isCopyReady(){return copyReady;}

    public void setCopyReady(){copyReady = !copyReady;}

    public boolean isSelectReady(){return selectReady;}

    public void setSelectReady(){selectReady = !selectReady;}

    public void addShapes(ArrayList<IDrawShapesStrategy> shapes, Point start, Point end){
        int x = Math.min(start.x, end.x);
        int y = Math.min(start.y, end.y);
        int width = Math.abs(start.x - end.x);
        int height = Math.abs(start.y - end.y);
        Rectangle rect = new Rectangle(x, y, width, height);

        for(IDrawShapesStrategy shape: shapes){
            Shape s = shape.getShapeParameters();
            if (rect.getBounds().intersects(s.getBounds()) || s.contains(x, y)){
                selectedShapesList.add(shape);
            }
        }
        // remove selected shapes from currentShapeList
        for (IDrawShapesStrategy shape: selectedShapesList) {
            Shape finalS = shape.getShapeParameters();
            currentShapeList.removeIf((IDrawShapesStrategy i) -> i.getShapeParameters().equals(finalS));
        }
        setSelectReady();
    }

    public void setShapeClipBoard(){
        shapeClipBoard.addAll(selectedShapesList);
        setCopyReady();
    }

    public ArrayList<IDrawShapesStrategy> getCurrentShapeList(){ return currentShapeList; }

    public ArrayList<IDrawShapesStrategy> getSelectedShapesList(){ return selectedShapesList; }

    public ArrayList<IDrawShapesStrategy> getShapeClipBoard(){return shapeClipBoard;}

    public void deselectAllShapes(){
        currentShapeList.addAll(selectedShapesList);
        selectedShapesList.clear();
        setSelectReady();
    }

    public void clearShapeClipBoard(){
        shapeClipBoard.clear();
        setCopyReady();
    }

    @Override
    public Iterator<IDrawShapesStrategy> createCurrentShapeIterator() { return currentShapeList.iterator(); }

    @Override
    public Iterator<IDrawShapesStrategy> createSelectedShapeIterator() { return selectedShapesList.iterator(); }

    @Override
    public Iterator<IDrawShapesStrategy> createShapeClipBoardIterator() {
        return shapeClipBoard.iterator();
    }

    @Override
    public void registerObserver(PaintCanvas paintCanvas) {
        this.canvas = paintCanvas;
    }

    @Override
    public void notifyObservers() {
        canvas.update(currentShapeList, selectedShapesList);
    }
}
