package controller.commands;

import controller.ICommand;
import controller.IUndoable;
import model.interfaces.IDrawShapesStrategy;
import model.shapes.ShapeLists;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class DeleteCommand implements ICommand, IUndoable {

    private ShapeLists masterShapeList;
    private ArrayList<IDrawShapesStrategy> deletedShapes;

    public DeleteCommand(ShapeLists masterShapeList) {
        this.masterShapeList = masterShapeList;
    }

    @Override
    public void run() throws IOException {
        delete();

        // add command to CommandHistory
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        for (IDrawShapesStrategy delete: deletedShapes) {
            masterShapeList.getSelectedShapesList().add(delete);
        }
        masterShapeList.setSelectReady();
        masterShapeList.notifyObservers();
    }

    @Override
    public void redo() {
        for (IDrawShapesStrategy delete: deletedShapes) {
            Shape finalS = delete.getShapeParameters();
            masterShapeList.getSelectedShapesList().removeIf((IDrawShapesStrategy i) -> i.getShapeParameters() == finalS);
        }
        masterShapeList.setSelectReady();
        masterShapeList.notifyObservers();
    }

    private void delete(){
        if(!masterShapeList.isSelectReady()) {
            System.out.println("reached delete command");

            deletedShapes = new ArrayList<>(masterShapeList.getSelectedShapesList().size());
            Iterator<IDrawShapesStrategy> deleteShapesIterator = masterShapeList.createSelectedShapeIterator();

            // clone entire selectedShapes ArrayList
            while (deleteShapesIterator.hasNext()) {
                deletedShapes.add(deleteShapesIterator.next().clone());
            }

            for (IDrawShapesStrategy delete : deletedShapes) {
                Shape finalS = delete.getShapeParameters();
                masterShapeList.getSelectedShapesList().removeIf((IDrawShapesStrategy i) -> i.getShapeParameters() == finalS);
            }
            masterShapeList.setSelectReady();
            masterShapeList.notifyObservers();
        }
    }
}
