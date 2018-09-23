package org.herebdragons.graphics.canvas;

import org.herebdragons.graphics.enums.WindowBehaviour;

import javax.swing.*;
import java.awt.*;

public class CanvasFactory {

    public static Canvas createCanvas(String Title, Dimension size) {
        return createCanvas(Title, size, WindowBehaviour.EXIT_ON_CLOSE, true);
    }

    public static Canvas createCanvas(String title) {

        return createCanvas(title, null, WindowBehaviour.EXIT_ON_CLOSE, false);
    }

    public static Canvas createCanvas(final String title, final Dimension size, final WindowBehaviour behaviourOnExit, final boolean isDecorated) {

        final Canvas canvas = new Canvas(size);
        canvas.setBehaviorOnExit(behaviourOnExit);
        canvas.setDecorated(isDecorated);
        canvas.setTitle(title);
        canvas.setDimension(size);

        /*SwingUtilities.invokeLater(new Runnable() {

            public void run() {

                canvas.init();
            }
        });*/

        return canvas;

    }
}
