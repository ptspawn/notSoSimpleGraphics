package org.herebdragons.graphics.canvas;

import org.herebdragons.Config;
import org.herebdragons.graphics.enums.CanvasType;
import org.herebdragons.graphics.enums.WindowBehaviour;

import javax.swing.*;
import java.awt.*;

public class CanvasFactory {


    private static CanvasType type = Config.DEFAULT_RENDERING;

    public static Canvas createCanvas(String Title, Dimension size) {
        return createCanvas(Title, size, WindowBehaviour.EXIT_ON_CLOSE, true);
    }

    public static Canvas createCanvas(String title) {

        return createCanvas(title, null, WindowBehaviour.EXIT_ON_CLOSE, false);
    }

    public static Canvas createCanvas(final String title, final Dimension size, final WindowBehaviour behaviourOnExit, final boolean isDecorated) {

        final Canvas canvas = new Canvas(size);
        canvas.setType(type);
        canvas.setBehaviorOnExit(behaviourOnExit);
        canvas.setDecorated(isDecorated);
        canvas.setTitle(title);
        canvas.setDimension(size);

        return canvas;

    }

    public static CanvasType getType() {
        return type;
    }

    public static void setRenderingMethod(CanvasType canvasType) {
        type = canvasType;
    }
}
