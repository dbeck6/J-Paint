package model.interfaces;

import java.util.Iterator;

public interface ISelectShapeIterator {
    Iterator<IDrawShapesStrategy> createIterator();
}
