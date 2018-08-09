package model.interfaces;

import java.util.Iterator;

public interface ISelectShapeIterator {
    Iterator<IDrawShapesStrategy> createCurrentShapeIterator();
    Iterator<IDrawShapesStrategy> createSelectedShapeIterator();
    Iterator<IDrawShapesStrategy> createShapeClipBoardIterator();
}
