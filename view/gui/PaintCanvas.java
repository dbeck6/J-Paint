package view.gui;

import model.interfaces.IDrawShapesStrategy;
import view.interfaces.IShapeObserver;

import javax.swing.JComponent;
import java.awt.*;
import java.util.ArrayList;

public class PaintCanvas extends JComponent implements IShapeObserver {

    public Graphics2D getGraphics2D() {
        return (Graphics2D)getGraphics();
    }

    @Override
    public void update(ArrayList<IDrawShapesStrategy> shapes, ArrayList<IDrawShapesStrategy> shapes2) {
        Graphics2D g = getGraphics2D();
        g.clearRect(0,0, 1200, 800);
        g.setColor(Color.WHITE);
        g.fillRect(0,0,1200,800);

        for (IDrawShapesStrategy shape: shapes) {
            shape.drawShapes();
        }
        for (IDrawShapesStrategy shape: shapes2) {
            shape.drawShapes();
        }
    }
}


