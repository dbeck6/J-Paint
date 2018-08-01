package model;

import java.awt.*;
import java.util.EnumMap;

public final class ShapeColorMap {

    private static ShapeColorMap shapeColorMap;
    private static EnumMap<ShapeColor, Color> colorMap;
    private ShapeColorMap(){
        colorMap = null;
    }

    public static ShapeColorMap getInstance() {
        if (shapeColorMap == null) {
            shapeColorMap = new ShapeColorMap();
            colorMap = new EnumMap<>(ShapeColor.class);
            colorMap.put(ShapeColor.BLACK, Color.black);
            colorMap.put(ShapeColor.BLUE, Color.BLUE);
            colorMap.put(ShapeColor.CYAN, Color.CYAN);
            colorMap.put(ShapeColor.DARK_GRAY, Color.DARK_GRAY);
            colorMap.put(ShapeColor.GRAY, Color.GRAY);
            colorMap.put(ShapeColor.GREEN, Color.GREEN);
            colorMap.put(ShapeColor.LIGHT_GRAY, Color.LIGHT_GRAY);
            colorMap.put(ShapeColor.MAGENTA, Color.MAGENTA);
            colorMap.put(ShapeColor.ORANGE, Color.ORANGE);
            colorMap.put(ShapeColor.PINK, Color.PINK);
            colorMap.put(ShapeColor.RED, Color.RED);
            colorMap.put(ShapeColor.WHITE, Color.WHITE);
            colorMap.put(ShapeColor.YELLOW, Color.YELLOW);
            // testing Singleton
            System.out.println("Singleton Color Map Created");
        }
        return shapeColorMap;
    }

    public static Color get(ShapeColor shapeColor){
        return colorMap.get(shapeColor);
    }

}
