package model.shapes;

import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.IDrawShapesStrategy;

import java.awt.*;

public class Rectangle implements IDrawShapesStrategy {

    private Graphics2D graphics;
    private ShapeConfiguration shapeConfiguration;

    private ShapeType rectangle;
    private ShapeColor primary;
    private ShapeColor secondary;
    private ShapeShadingType shade;

    private Point start, end;

    public Rectangle(Graphics2D graphics, ShapeConfiguration shapeConfiguration, Point start, Point end){
        this.graphics = graphics;
        this.shapeConfiguration = shapeConfiguration;
        this.rectangle = shapeConfiguration.shapeType;
        this.primary = shapeConfiguration.primaryColor;
        this.secondary = shapeConfiguration.secondaryColor;
        this.shade = shapeConfiguration.shapeShadingType;
        this.start = start;
        this.end = end;
    }

    @Override
    public void drawShapes() {
        //graphics.setColor(primary);
        //graphics.fillRect()
    }

    /*@Override
    public void drawShapes2(Graphics2D g) {
       g.setColor(color);
        g.fillRect(left,top,width,height);
       g.setColor(Color.black);
        g.drawRect(left,top,width,height);
    }*/
}
