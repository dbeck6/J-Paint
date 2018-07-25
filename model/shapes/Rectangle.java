package model.shapes;

import model.interfaces.IDrawShapesStrategy;

import java.awt.*;

public class Rectangle implements IDrawShapesStrategy {

    @Override
    public int drawShapes(int x1, int y1, int x2, int y2) {
        return 0;
    }

    @Override
    public void drawShapes2(Graphics2D g) {
       // g.setColor(color);
//        g.fillRect(left,top,width,height);
//        g.setColor(Color.black);
//        g.drawRect(left,top,width,height);
    }
}
