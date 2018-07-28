package model.shapes;

import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.IDrawShapesStrategy;
import view.interfaces.IGuiWindow;

public class Ellipse implements IDrawShapesStrategy {

    private IGuiWindow g;
    private ShapeConfiguration s;

    private ShapeType ellipse;
    private ShapeColor primary;
    private ShapeColor secondary;
    private ShapeShadingType shade;

    public Ellipse(IGuiWindow g, ShapeConfiguration s){
        this.g = g;
        this.s = s;
    }

    @Override
    public void getParameters() {
        ellipse = s.shapeType;
        primary = s.primaryColor;
        secondary = s.secondaryColor;
        shade = s.shapeShadingType;
    }

    @Override
    public void drawShapes() {

    }
}
