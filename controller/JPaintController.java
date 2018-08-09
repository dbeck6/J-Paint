package controller;

import controller.commands.ClickHandler;
import model.interfaces.IApplicationState;
import view.EventName;
import view.interfaces.IUiModule;

public class JPaintController implements IJPaintController {
    private final IUiModule uiModule;
    private final IApplicationState applicationState;
    private final ClickHandler clickHandler;

    public JPaintController(IUiModule uiModule, IApplicationState applicationState, ClickHandler clickHandler) {
        this.uiModule = uiModule;
        this.applicationState = applicationState;
        this.clickHandler = clickHandler;
    }

    @Override
    public void setup() {
        setupEvents();
    }

    private void setupEvents() {
        uiModule.addEvent(EventName.CHOOSE_SHAPE, () -> applicationState.setActiveShape());
        uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, () -> applicationState.setActivePrimaryColor());
        uiModule.addEvent(EventName.CHOOSE_SECONDARY_COLOR, () -> applicationState.setActiveSecondaryColor());
        uiModule.addEvent(EventName.CHOOSE_SHADING_TYPE, () -> applicationState.setActiveShadingType());
        uiModule.addEvent(EventName.CHOOSE_START_POINT_ENDPOINT_MODE, () -> applicationState.setActiveStartAndEndPointMode());
        // need to add events for undo, redo, copy, paste, and delete commands
        /*uiModule.addEvent(EventName.UNDO, ()-> );
        uiModule.addEvent(EventName.REDO, () ->);
        uiModule.addEvent(EventName.COPY, () ->);
        uiModule.addEvent(EventName.PASTE, () ->);
        uiModule.addEvent(EventName.DELETE, () ->);*/
    }
}
