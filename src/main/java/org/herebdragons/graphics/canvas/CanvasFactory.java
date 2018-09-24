package org.herebdragons.graphics.canvas;

import org.herebdragons.Config;
import org.herebdragons.graphics.enums.RendererType;
import org.herebdragons.graphics.enums.WindowBehaviour;

import java.awt.*;

public class CanvasFactory {

    private static RendererType renderer = Config.DEFAULT_RENDERER;

    public static Canvas createCanvas(String Title, Dimension size) {
        return createCanvas(Title, size, WindowBehaviour.EXIT_ON_CLOSE, true);
    }

    public static Canvas createCanvas(String title) {

        return createCanvas(title, null, WindowBehaviour.EXIT_ON_CLOSE, false);
    }

    public static Canvas createCanvas(final String title, final Dimension size, final WindowBehaviour behaviourOnExit, final boolean isDecorated) {

        final Canvas canvas = new Canvas(size, renderer);
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

    public static RendererType getRenderer() {
        return renderer;
    }

   public static void setRenderer(RendererType renderer) {
        CanvasFactory.renderer = renderer;
    }
}
