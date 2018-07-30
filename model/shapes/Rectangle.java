package model.shapes;

import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.IDrawShapesStrategy;
import view.interfaces.IGuiWindow;

public class Rectangle implements IDrawShapesStrategy {

    private IGuiWindow guiWindow;
    private ShapeConfiguration shapeConfiguration;

    private ShapeType rectangle;
    private ShapeColor primary;
    private ShapeColor secondary;
    private ShapeShadingType shade;

    public Rectangle(IGuiWindow guiWindow, ShapeConfiguration shapeConfiguration){
        this.guiWindow = guiWindow;
        this.shapeConfiguration = shapeConfiguration;
        this.rectangle = shapeConfiguration.shapeType;
        this.primary = shapeConfiguration.primaryColor;
        this.secondary = shapeConfiguration.secondaryColor;
        this.shade = shapeConfiguration.shapeShadingType;
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
