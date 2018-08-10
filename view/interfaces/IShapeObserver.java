package view.interfaces;

import model.interfaces.IDrawShapesStrategy;

import java.util.ArrayList;

public interface IShapeObserver {
    void update(ArrayList<IDrawShapesStrategy> shapes, ArrayList<IDrawShapesStrategy> shapes2);
}
