package model.shapes;

import model.ShapeColor;
import model.ShapeColorMap;
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

    //creates Singleton shapeColorMap object if not already created
    ShapeColorMap shapeColorMap = ShapeColorMap.getInstance();

    private int[] x, y;
    private final int points = 3;

    public Triangle(Graphics2D graphics, ShapeConfiguration shapeConfiguration, Point start, Point end){
        this.graphics = graphics;
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
        graphics.setColor(shapeColorMap.get(shapeConfiguration.primaryColor)); //primary
        if(shade == ShapeShadingType.FILLED_IN || shade == ShapeShadingType.OUTLINE_AND_FILLED_IN) {
            graphics.fillPolygon(x, y, points);
        }
        graphics.setStroke(new BasicStroke(5));
        graphics.setColor(shapeColorMap.get(shapeConfiguration.secondaryColor)); // secondary
        if(shade == ShapeShadingType.OUTLINE || shade == ShapeShadingType.OUTLINE_AND_FILLED_IN) {
            graphics.drawPolygon(x, y, points);
        }
    }
}
