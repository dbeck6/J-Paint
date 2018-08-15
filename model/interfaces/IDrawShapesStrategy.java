package model.interfaces;

import model.shapes.ShapeConfiguration;

import java.awt.*;

public interface IDrawShapesStrategy extends Shape, Cloneable {
    void drawShapes();
    Shape getShapeParameters();
    Point getStartPoint();
    Point getEndPoint();
    void setStartAndEndPoint(Point start, Point end);
    int getCurrentWidth();
    int getCurrentHeight();
    void getOldShapeConfiguration();
    void setNewShapeConfiguration(ShapeConfiguration shapeConfiguration);
    IDrawShapesStrategy clone();
}
