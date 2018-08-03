package model.interfaces;

import java.util.Iterator;

public interface ISelectShapeIterator {
    public Iterator<IDrawShapesStrategy> createIterator();
}
