package model.shapes;

import java.awt.*;

public interface IShapeFactory extends Shape {
   void createShape();
   // will need access to ApplicationState
}
