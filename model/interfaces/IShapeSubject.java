package model.interfaces;

import view.gui.PaintCanvas;

public interface IShapeSubject {
    void registerObserver(PaintCanvas paintCanvas);
    void notifyObservers();
}
