package model.shapes;

import model.ShapeColor;
import model.ShapeColorMap;
import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.IDrawShapesStrategy;

import java.awt.*;
import java.util.ArrayList;

public class Rectangle extends java.awt.Rectangle implements IDrawShapesStrategy {

    private Graphics2D graphics;
    private ArrayList<Shape> shapes;
    private ShapeConfiguration shapeConfiguration;

    private ShapeType rectangle;
    private ShapeColor primary;
    private ShapeColor secondary;
    private ShapeShadingType shade;

    //creates Singleton shapeColorMap object if not already created
    ShapeColorMap shapeColorMap = ShapeColorMap.getInstance();

    private int x, y, height, width;

    public Rectangle(Graphics2D graphics, ArrayList<Shape> shapes, ShapeConfiguration shapeConfiguration, Point start, Point end){
        this.graphics = graphics;
        this.shapes = shapes;
        this.shapeConfiguration = shapeConfiguration;
        this.rectangle = shapeConfiguration.shapeType;
        this.primary = shapeConfiguration.primaryColor;
        this.secondary = shapeConfiguration.secondaryColor;
        this.shade = shapeConfiguration.shapeShadingType;
        this.x = Math.min(start.x, end.x);
        this.y = Math.min(start.y, end.y);
        this.width = Math.abs(start.x - end.x);
        this.height = Math.abs(start.y - end.y);
    }

    @Override
    public void drawShapes() {
        graphics.setColor(shapeColorMap.get(primary)); //primary
        if(shade == ShapeShadingType.FILLED_IN || shade == ShapeShadingType.OUTLINE_AND_FILLED_IN) {
            graphics.fillRect(x, y, width, height);
            java.awt.Rectangle rectangle = new java.awt.Rectangle(x, y, width, height);
            shapes.add(rectangle);
        }
        graphics.setStroke(new BasicStroke(5));
        graphics.setColor(shapeColorMap.get(secondary)); // secondary
        if(shade == ShapeShadingType.OUTLINE || shade == ShapeShadingType.OUTLINE_AND_FILLED_IN) {
            graphics.drawRect(x, y, width, height);
            java.awt.Rectangle rectangle = new java.awt.Rectangle(x, y, width, height);
            shapes.add(rectangle);
        }
    }
}
