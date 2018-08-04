package model.shapes;

import model.ShapeColor;
import model.ShapeColorMap;
import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.IDrawShapesStrategy;

import java.awt.*;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Ellipse extends Rectangle implements IDrawShapesStrategy {

    private Graphics2D graphics;
    private ArrayList<Shape> shapes;
    private ShapeConfiguration shapeConfiguration;

    private ShapeType ellipse;
    private ShapeColor primary;
    private ShapeColor secondary;
    private ShapeShadingType shade;

    //creates Singleton shapeColorMap object if not already created
    ShapeColorMap shapeColorMap = ShapeColorMap.getInstance();

    private int x, y, width, height;

    public Ellipse(Graphics2D graphics, ArrayList<Shape> shapes, ShapeConfiguration shapeConfiguration, Point start, Point end){
        this.graphics = graphics;
        this.shapes = shapes;
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
        graphics.setColor(shapeColorMap.get(primary)); //primary
        if(shade == ShapeShadingType.FILLED_IN || shade == ShapeShadingType.OUTLINE_AND_FILLED_IN) {
            graphics.fillOval(x, y, width, height);
            Rectangle ellipse = new Rectangle(x, y, width, height);
            shapes.add(ellipse);
        }
        graphics.setStroke(new BasicStroke(5));
        graphics.setColor(shapeColorMap.get(secondary)); // secondary
        if(shade == ShapeShadingType.OUTLINE || shade == ShapeShadingType.OUTLINE_AND_FILLED_IN) {
            graphics.drawOval(x, y, width, height);
            Rectangle ellipse = new Rectangle(x, y, width, height);
            shapes.add(ellipse);
        }
    }
}
