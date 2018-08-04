package model.shapes;

import model.ShapeColor;
import model.ShapeColorMap;
import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.IDrawShapesStrategy;

import java.awt.*;
import java.util.ArrayList;

public class Triangle extends Polygon implements IDrawShapesStrategy {

    private Graphics2D graphics;
    private ArrayList<Shape> shapes;
    private ShapeConfiguration shapeConfiguration;

    private ShapeType triangle;
    private ShapeColor primary;
    private ShapeColor secondary;
    private ShapeShadingType shade;

    //creates Singleton shapeColorMap object if not already created
    ShapeColorMap shapeColorMap = ShapeColorMap.getInstance();

    private int[] x, y;
    private final int points = 3;

    public Triangle(Graphics2D graphics, ArrayList<Shape> shapes, ShapeConfiguration shapeConfiguration, Point start, Point end){
        this.graphics = graphics;
        this.shapes = shapes;
        this.shapeConfiguration = shapeConfiguration;
        this.triangle = shapeConfiguration.shapeType;
        this.primary = shapeConfiguration.primaryColor;
        this.secondary = shapeConfiguration.secondaryColor;
        this.shade = shapeConfiguration.shapeShadingType;
        this.x = new int[]{start.x, ((start.x + end.x)/2), end.x};
        this.y = new int[]{end.y, start.y, end.y};
    }

    @Override
    public void drawShapes() {
        graphics.setColor(shapeColorMap.get(primary)); //primary
        if(shade == ShapeShadingType.FILLED_IN || shade == ShapeShadingType.OUTLINE_AND_FILLED_IN) {
            graphics.fillPolygon(x, y, points);
            Polygon triangle = new Polygon(x, y, points);
            shapes.add(triangle);
        }
        graphics.setStroke(new BasicStroke(5));
        graphics.setColor(shapeColorMap.get(secondary)); // secondary
        if(shade == ShapeShadingType.OUTLINE || shade == ShapeShadingType.OUTLINE_AND_FILLED_IN) {
            graphics.drawPolygon(x, y, points);
            Polygon triangle = new Polygon(x, y, points);
            shapes.add(triangle);
        }
    }
}
