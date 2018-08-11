package model.interfaces;

import java.awt.*;

public interface IDrawShapesStrategy extends Shape, Cloneable {
    void drawShapes();
    Shape getShapeParameters();
    Point getStartPoint();
    Point getEndPoint();
    void setStartAndEndPoint(Point start, Point end);
    int getCurrentWidth();
    int getCurrentHeight();
    IDrawShapesStrategy clone();
}
