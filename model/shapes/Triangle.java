package model.shapes;

import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.IDrawShapesStrategy;
import view.interfaces.IGuiWindow;

public class Triangle implements IDrawShapesStrategy {

    private IGuiWindow guiWindow;
    private ShapeConfiguration shapeConfiguration;

    private ShapeType triangle;
    private ShapeColor primary;
    private ShapeColor secondary;
    private ShapeShadingType shade;

    public Triangle(IGuiWindow guiWindow, ShapeConfiguration shapeConfiguration){
        this.guiWindow = guiWindow;
        this.shapeConfiguration = shapeConfiguration;
        this.triangle = shapeConfiguration.shapeType;
        this.primary = shapeConfiguration.primaryColor;
        this.secondary = shapeConfiguration.secondaryColor;
        this.shade = shapeConfiguration.shapeShadingType;
    }

    @Override
    public void drawShapes() {

    }
}
