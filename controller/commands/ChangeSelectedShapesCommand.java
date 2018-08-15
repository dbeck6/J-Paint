package controller.commands;

import controller.ICommand;
import controller.IUndoable;
import model.interfaces.IApplicationState;
import model.interfaces.IDrawShapesStrategy;
import model.shapes.ShapeConfiguration;
import model.shapes.ShapeLists;

import java.io.IOException;
import java.util.Iterator;

public class ChangeSelectedShapesCommand implements ICommand, IUndoable {

    private IApplicationState appState;
    private ShapeLists shapeLists;
    private ShapeConfiguration shapeConfiguration = new ShapeConfiguration();
    private Iterator<IDrawShapesStrategy> selectedShapeIterator;


    public ChangeSelectedShapesCommand(IApplicationState appState, ShapeLists shapeLists) {
        this.appState = appState;
        this.shapeLists = shapeLists;
    }

    @Override
    public void run() throws IOException {
        changeShapes();
        // add command to CommandHistory
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        selectedShapeIterator = shapeLists.createSelectedShapeIterator();

        while(selectedShapeIterator.hasNext()){
            IDrawShapesStrategy temp = selectedShapeIterator.next();
            temp.getOldShapeConfiguration();
        }
        shapeLists.notifyObservers();
    }

    @Override
    public void redo() {
        selectedShapeIterator = shapeLists.createSelectedShapeIterator();

        while(selectedShapeIterator.hasNext()){
            IDrawShapesStrategy temp = selectedShapeIterator.next();
            temp.setNewShapeConfiguration(shapeConfiguration);
        }
        shapeLists.notifyObservers();
    }

    private void changeShapes(){
        appState.getCurrentShapeConfiguration(shapeConfiguration);

        selectedShapeIterator = shapeLists.createSelectedShapeIterator();

        while(selectedShapeIterator.hasNext()){
            IDrawShapesStrategy temp = selectedShapeIterator.next();
            temp.setNewShapeConfiguration(shapeConfiguration);
        }
        shapeLists.notifyObservers();
    }
}
