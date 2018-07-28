package model.shapes;

import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.IDrawShapesStrategy;
import view.interfaces.IGuiWindow;

public class Rectangle implements IDrawShapesStrategy {

    private IGuiWindow g;
    private ShapeConfiguration s;

    private ShapeType rectangle;
    private ShapeColor primary;
    private ShapeColor secondary;
    private ShapeShadingType shade;

    public Rectangle(IGuiWindow g, ShapeConfiguration s){
        this.g = g;
        this.s = s;
    }

    @Override
    public void getParameters() {
        rectangle = s.shapeType;
        primary = s.primaryColor;
        secondary = s.secondaryColor;
        shade = s.shapeShadingType;
    }

    @Override
    public void drawShapes() {

    }

    /*@Override
    public void drawShapes2(Graphics2D g) {
       g.setColor(color);
        g.fillRect(left,top,width,height);
       g.setColor(Color.black);
        g.drawRect(left,top,width,height);
    }*/
}
