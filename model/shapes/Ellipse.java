package model.shapes;

import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.IDrawShapesStrategy;
import view.interfaces.IGuiWindow;

public class Ellipse implements IDrawShapesStrategy {

    private IGuiWindow guiWindow;
    private ShapeConfiguration shapeConfiguration;

    private ShapeType ellipse;
    private ShapeColor primary;
    private ShapeColor secondary;
    private ShapeShadingType shade;

    public Ellipse(IGuiWindow guiWindow, ShapeConfiguration shapeConfiguration){
        this.guiWindow = guiWindow;
        this.shapeConfiguration = shapeConfiguration;
        this.ellipse = shapeConfiguration.shapeType;
        this.primary = shapeConfiguration.primaryColor;
        this.secondary = shapeConfiguration.secondaryColor;
        this.shade = shapeConfiguration.shapeShadingType;
    }

    @Override
    public void drawShapes() {

    }
}
