package controller;

import controller.commands.*;
import model.interfaces.IApplicationState;
import model.shapes.ShapeLists;
import view.EventName;
import view.interfaces.IUiModule;

import java.io.IOException;

public class JPaintController implements IJPaintController {
    private final IUiModule uiModule;
    private final IApplicationState applicationState;
    private final ShapeLists masterShapeList;

    public JPaintController(IUiModule uiModule, IApplicationState applicationState, ShapeLists masterShapeList) {
        this.uiModule = uiModule;
        this.applicationState = applicationState;
        this.masterShapeList = masterShapeList;
    }

    @Override
    public void setup() {
        setupEvents();
    }

    private void setupEvents() {
        ICommand undo = new UndoCommand();
        ICommand redo = new RedoCommand();
        ICommand copy = new CopyCommand(masterShapeList);
        ICommand paste = new PasteCommand(masterShapeList);
        ICommand delete = new DeleteCommand(masterShapeList);
        uiModule.addEvent(EventName.CHOOSE_SHAPE, () -> applicationState.setActiveShape());
        uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, () -> applicationState.setActivePrimaryColor());
        uiModule.addEvent(EventName.CHOOSE_SECONDARY_COLOR, () -> applicationState.setActiveSecondaryColor());
        uiModule.addEvent(EventName.CHOOSE_SHADING_TYPE, () -> applicationState.setActiveShadingType());
        uiModule.addEvent(EventName.CHOOSE_START_POINT_ENDPOINT_MODE, () -> applicationState.setActiveStartAndEndPointMode());
        // need to add events for undo, redo, copy, paste, and delete commands
        // may need to make a new command each time to keep stack relevant to order of commands
        uiModule.addEvent(EventName.UNDO, () -> { try{ undo.run();} catch (IOException e){e.printStackTrace();} });
        uiModule.addEvent(EventName.REDO, () -> { try{ redo.run();} catch (IOException e){e.printStackTrace();} });
        uiModule.addEvent(EventName.COPY, () -> { try{ copy.run();} catch (IOException e){e.printStackTrace();} });
        uiModule.addEvent(EventName.PASTE, () -> { try{ paste.run();} catch (IOException e){e.printStackTrace();} });
        uiModule.addEvent(EventName.DELETE, () -> { try{ delete.run();} catch (IOException e){e.printStackTrace();} });
    }
}
