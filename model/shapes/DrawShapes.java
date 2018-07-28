package model.shapes;

import controller.commands.ClickHandler;
import view.interfaces.IGuiWindow;

import java.util.LinkedList;

public class DrawShapes extends ClickHandler {

    private LinkedList<DrawShapes> shapes = new LinkedList<>();
    // maybe a clickHandler saved here??
    private ClickHandler c;
    private IGuiWindow g;

    public DrawShapes(ClickHandler c, IGuiWindow g){
        this.c = c;
        this.g = g;
    }

    // some kind of while loop evaluates if a shape has been created or null?
}
