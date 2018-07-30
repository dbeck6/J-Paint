package model.shapes;

import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.IDrawShapesStrategy;

import java.awt.*;

public class Triangle implements IDrawShapesStrategy {

    private Graphics2D graphics;
    private ShapeConfiguration shapeConfiguration;

    private ShapeType triangle;
    private ShapeColor primary;
    private ShapeColor secondary;
    private ShapeShadingType shade;

    private Point start, end;

    public Triangle(Graphics2D graphics, ShapeConfiguration shapeConfiguration, Point start, Point end){
        this.graphics = graphics;
        this.shapeConfiguration = shapeConfiguration;
        this.triangle = shapeConfiguration.shapeType;
        this.primary = shapeConfiguration.primaryColor;
        this.secondary = shapeConfiguration.secondaryColor;
        this.shade = shapeConfiguration.shapeShadingType;
        this.start = start;
        this.end = end;
    }

    @Override
    public void drawShapes() {

    }
}
