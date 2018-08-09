package model.shapes;

import model.ShapeColor;
import model.ShapeColorMap;
import model.ShapeShadingType;
import model.interfaces.IDrawShapesStrategy;

import java.awt.*;

public class Triangle extends Polygon implements IDrawShapesStrategy {

    private Graphics2D graphics;
    private Shape currShape;
    private ShapeColor primary;
    private ShapeColor secondary;
    private ShapeShadingType shade;

    //creates Singleton shapeColorMap object if not already created
    ShapeColorMap shapeColorMap = ShapeColorMap.getInstance();

    private Point start, end;
    private final int points = 3;

    public Triangle(Graphics2D graphics, ShapeConfiguration shapeConfiguration, Point start, Point end) {
        this.graphics = graphics;
        this.primary = shapeConfiguration.primaryColor;
        this.secondary = shapeConfiguration.secondaryColor;
        this.shade = shapeConfiguration.shapeShadingType;
        this.start = start;
        this.end = end;
    }

    @Override
    public void drawShapes() {
        int[] xArray = new int[]{start.x, ((start.x + end.x) / 2), end.x};
        int[] yArray = new int[]{end.y, start.y, end.y};
        Polygon triangle = new Polygon(xArray, yArray, points);
        this.currShape = triangle;
        graphics.setColor(shapeColorMap.get(primary)); //primary
        if (shade == ShapeShadingType.FILLED_IN || shade == ShapeShadingType.OUTLINE_AND_FILLED_IN) {
            graphics.fillPolygon(triangle.xpoints, triangle.ypoints, triangle.npoints);
        }
        graphics.setStroke(new BasicStroke(5));
        graphics.setColor(shapeColorMap.get(secondary)); // secondary
        if (shade == ShapeShadingType.OUTLINE || shade == ShapeShadingType.OUTLINE_AND_FILLED_IN) {
            graphics.drawPolygon(triangle.xpoints, triangle.ypoints, triangle.npoints);
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
}
