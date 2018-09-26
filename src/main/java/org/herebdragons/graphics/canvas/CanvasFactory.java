package org.herebdragons.graphics.canvas;

import org.herebdragons.Config;
import org.herebdragons.graphics.enums.RendererType;
import org.herebdragons.graphics.enums.ThreadBehaviour;
import org.herebdragons.graphics.enums.WindowBehaviour;
import org.herebdragons.graphics.objects.ObjectManager;

import javax.swing.*;
import java.awt.*;

public class CanvasFactory {

    private static ThreadBehaviour defaultThreading = Config.DEFAULT_THREAD_BEHAVIOUR;
    private static int numThreads = Config.DEFAULT_THREADS;
    private static RendererType rendererType = Config.DEFAULT_RENDERER;


    public static notSoSimpleCanvas createCanvas(String Title, Dimension size, RendererType rendererType) {
        return createCanvas(Title, size, WindowBehaviour.EXIT_ON_CLOSE, true, rendererType);
    }

    public static notSoSimpleCanvas createCanvas(String title, RendererType rendererType) {

        return createCanvas(title, null, WindowBehaviour.EXIT_ON_CLOSE, false, rendererType);
    }

    private static notSoSimpleCanvas createCanvas(final String title, final Dimension size, final WindowBehaviour behaviourOnExit, boolean isDecorated, RendererType rendererType) {
        notSoSimpleCanvas canvas = null;
        notSoSimpleRenderer renderer = null;
        ObjectManager objectManager = new ObjectManager();

        switch (rendererType) {
            case SWING:
                renderer = new JframeRenderer();
                canvas = new JFrameCanvas(size, renderer.getGraphicsConfig());
                canvas.setObjectManager(objectManager);
                canvas.setRenderer(renderer);
                canvas.setObjectManager(objectManager);
                break;
            case JAVA_FX:
                break;
            case OPEN_GL:
                break;
        }

        canvas.setBehaviorOnExit(behaviourOnExit);
        canvas.setDecorated(isDecorated);
        canvas.setTitle(title);
        canvas.setDimension(size);

        final notSoSimpleCanvas nssCanvas = canvas;

        if (defaultThreading != ThreadBehaviour.USER_CONTROLED) {
            SwingUtilities.invokeLater(new Runnable() {

                public void run() {

                    nssCanvas.run();
                }
            });
        }
        return canvas;

    }

    public static RendererType getRenderer() {
        return rendererType;
    }

    public static void setRenderer(RendererType renderer) {
        rendererType = renderer;
    }
}
