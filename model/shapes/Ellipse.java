package model.shapes;

import model.ShapeColor;
import model.ShapeColorMap;
import model.ShapeShadingType;
import model.interfaces.IDrawShapesStrategy;

import java.awt.*;
import java.awt.Rectangle;

public class Ellipse extends Rectangle implements IDrawShapesStrategy {

    private Graphics2D graphics;
    private Shape currShape;
    private ShapeConfiguration shapeConfiguration;
    private ShapeColor primary;
    private ShapeColor secondary;
    private ShapeShadingType shade;

    //creates Singleton shapeColorMap object if not already created
    ShapeColorMap shapeColorMap = ShapeColorMap.getInstance();

    private Point start, end;

    public Ellipse(Graphics2D graphics, ShapeConfiguration shapeConfiguration, Point start, Point end) {
        this.graphics = graphics;
        this.shapeConfiguration = shapeConfiguration;
        this.primary = shapeConfiguration.primaryColor;
        this.secondary = shapeConfiguration.secondaryColor;
        this.shade = shapeConfiguration.shapeShadingType;
        this.start = start;
        this.end = end;
    }

    @Override
    public void drawShapes() {
        int x = Math.min(start.x, end.x);
        int y = Math.min(start.y, end.y);
        int width = getCurrentWidth();
        int height = getCurrentHeight();
        Rectangle ellipse = new Rectangle(x, y, width, height);
        this.currShape = ellipse;
        graphics.setColor(shapeColorMap.get(primary)); //primary
        if (shade == ShapeShadingType.FILLED_IN || shade == ShapeShadingType.OUTLINE_AND_FILLED_IN) {
            graphics.fillOval(ellipse.x, ellipse.y, ellipse.width, ellipse.height);
        }
        graphics.setStroke(new BasicStroke(5));
        graphics.setColor(shapeColorMap.get(secondary)); // secondary
        if (shade == ShapeShadingType.OUTLINE || shade == ShapeShadingType.OUTLINE_AND_FILLED_IN) {
            graphics.drawOval(ellipse.x, ellipse.y, ellipse.width, ellipse.height);
        }
    }

    @Override
    public Shape getShapeParameters() {
        return currShape;
    }

    @Override
    public Point getStartPoint() {
        return start;
    }

    @Override
    public Point getEndPoint() {
        return end;
    }

    @Override
    public void setStartAndEndPoint(Point start, Point end) {
        this.start = start; this.end = end;
    }

    @Override
    public int getCurrentWidth() {
        return Math.abs(start.x - end.x);
    }

    @Override
    public int getCurrentHeight() {
        return Math.abs(start.y - end.y);
    }

    @Override
    public void getOldShapeConfiguration() {
        this.primary = shapeConfiguration.primaryColor;
        this.secondary = shapeConfiguration.secondaryColor;
        this.shade = shapeConfiguration.shapeShadingType;
    }

    @Override
    public void setNewShapeConfiguration(ShapeConfiguration shapeConfiguration) {
        this.primary = shapeConfiguration.primaryColor;
        this.secondary = shapeConfiguration.secondaryColor;
        this.shade = shapeConfiguration.shapeShadingType;
    }

    @Override
    public IDrawShapesStrategy clone(){
        IDrawShapesStrategy clone;
        clone = (IDrawShapesStrategy) super.clone();
        return clone;
    }
}
