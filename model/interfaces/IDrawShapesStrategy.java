package model.interfaces;

import model.shapes.ShapeConfiguration;

import java.awt.*;

public interface IDrawShapesStrategy {
    //int drawShapes(int x1, int y1, int x2, int y2);
    void drawShapes(Graphics2D g, ShapeConfiguration s);
}
