package model.shapes;

import model.ShapeColor;
import model.ShapeColorMap;
import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.IDrawShapesStrategy;

import java.awt.*;

public class Ellipse implements IDrawShapesStrategy {

    private Graphics2D graphics;
    private ShapeConfiguration shapeConfiguration;

    private ShapeType ellipse;
    private ShapeColor primary;
    private ShapeColor secondary;
    private ShapeShadingType shade;

    //creates Singleton shapeColorMap object if not already created
    ShapeColorMap shapeColorMap = ShapeColorMap.getInstance();

    private int x, y, width, height;

    public Ellipse(Graphics2D graphics, ShapeConfiguration shapeConfiguration, Point start, Point end){
        this.graphics = graphics;
        this.shapeConfiguration = shapeConfiguration;
        this.ellipse = shapeConfiguration.shapeType;
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
        graphics.fillOval(x, y, width, height);
        graphics.setStroke(new BasicStroke(5));
        graphics.setColor(shapeColorMap.get(shapeConfiguration.secondaryColor)); // secondary
        graphics.drawOval(x, y, width, height);
    }
}
