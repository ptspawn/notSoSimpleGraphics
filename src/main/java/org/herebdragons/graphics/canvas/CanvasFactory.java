package org.herebdragons.graphics.canvas;

import org.herebdragons.Config;
import org.herebdragons.graphics.enums.RendererType;
import org.herebdragons.graphics.enums.ThreadBehaviour;
import org.herebdragons.graphics.enums.WindowBehaviour;
import org.herebdragons.graphics.objects.Manager;
import org.herebdragons.graphics.objects.ObjectManager;
import org.herebdragons.utils.Logger;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class CanvasFactory {

    static Boolean frameShowing = false;

    private static ThreadBehaviour defaultThreading = Config.DEFAULT_THREAD_BEHAVIOUR;
    private static int numThreads = Config.DEFAULT_THREADS;
    private static RendererType rendererType = Config.DEFAULT_RENDERER;

    private static boolean isDecorated=Config.DEFAULT_WINDOW_DECORATION;

    private static WindowBehaviour behaviourOnExit = Config.DEFAULT_BEHAVIOUR_ON_EXIT;

    private static Manager objectManager;


    public static notSoSimpleCanvas createCanvas(String Title, Dimension size, RendererType rendererType) {
        return createCanvas(Title, size, isDecorated, rendererType);
    }

    public static notSoSimpleCanvas createCanvas(String title, RendererType rendererType) {

        return createCanvas(title, null, false, rendererType);
    }

    private static notSoSimpleCanvas createCanvas(final String title, final Dimension size, boolean isDecorated, RendererType rendererType) {
        notSoSimpleCanvas canvas = null;
        notSoSimpleRenderer renderer = null;

        if (objectManager == null)
            objectManager = new ObjectManager();

        switch (rendererType) {
            case JAVA_2D:
                canvas = new JFrameCanvas(size);
                canvas.setDecorated(isDecorated);
                canvas.setLocationRelativeTo(null);
                canvas.setObjectManager(objectManager);
                renderer = new JframeRenderer(canvas);
                renderer.setObjectManager(objectManager);
                canvas.setRenderer(renderer);
                break;
            case JAVA_FX:
                break;
            case OPEN_GL:
                break;
        }

        canvas.setBehaviorOnExit(behaviourOnExit);
        canvas.setTitle(title);

        return canvas;

    }

    public static void startCanvas(final notSoSimpleCanvas nssCanvas) {

        if (nssCanvas == null)
            throw new IllegalArgumentException("notSoSimpleCanvas can't be null!");

        if (defaultThreading == ThreadBehaviour.USER_CONTROLED)
            throw new IllegalStateException("Thread behaviour is set to Manual...\nIf you know what your'te doing then you invoke run!! .|.");

        try {

            SwingUtilities.invokeAndWait(nssCanvas);

        } catch (InterruptedException e) {
            Logger.log("Problem Starting Canvas - "+ e.getMessage());
        } catch (InvocationTargetException e) {
            Logger.log("Problem Starting Canvas - "+ e.getMessage());
        }

        System.out.println("JFDAKJBIAJDBG");

    }


    public static void setObjectManager(Manager objManager) {
        objectManager = objManager;
    }

    public static RendererType getRenderer() {
        return rendererType;
    }

    public static void setRenderer(RendererType renderer) {
        rendererType = renderer;
    }

    public static WindowBehaviour getBehaviourOnExit() {
        return behaviourOnExit;
    }

    public static void setBehaviourOnExit(WindowBehaviour behaviourOnExit) {
        CanvasFactory.behaviourOnExit = behaviourOnExit;
    }

    public static ThreadBehaviour getDefaultThreading() {
        return defaultThreading;
    }

    public static void setDefaultThreading(ThreadBehaviour defaultThreading) {
        CanvasFactory.defaultThreading = defaultThreading;
    }

    public static boolean isIsDecorated() {
        return isDecorated;
    }

    public static void setIsDecorated(boolean isDecorated) {
        CanvasFactory.isDecorated = isDecorated;
    }
}
