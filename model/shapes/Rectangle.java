package model.shapes;

import model.ShapeColor;
import model.ShapeColorMap;
import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.IDrawShapesStrategy;

import java.awt.*;

public class Rectangle extends java.awt.Rectangle implements IDrawShapesStrategy {

    private Graphics2D graphics;
    private ShapeConfiguration shapeConfiguration;

    private ShapeType rectangle;
    private ShapeColor primary;
    private ShapeColor secondary;
    private ShapeShadingType shade;

    //creates Singleton shapeColorMap object if not already created
    ShapeColorMap shapeColorMap = ShapeColorMap.getInstance();

    private int x, y, height, width;

    public Rectangle(Graphics2D graphics, ShapeConfiguration shapeConfiguration, Point start, Point end){
        this.graphics = graphics;
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
        graphics.setColor(shapeColorMap.get(shapeConfiguration.primaryColor)); //primary
        if(shade == ShapeShadingType.FILLED_IN || shade == ShapeShadingType.OUTLINE_AND_FILLED_IN) {
            graphics.fillRect(x, y, width, height);
        }
        graphics.setStroke(new BasicStroke(5));
        graphics.setColor(shapeColorMap.get(shapeConfiguration.secondaryColor)); // secondary
        if(shade == ShapeShadingType.OUTLINE || shade == ShapeShadingType.OUTLINE_AND_FILLED_IN) {
            graphics.drawRect(x, y, width, height);
        }
    }
}
