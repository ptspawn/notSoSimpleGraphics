package org.herebdragons.graphics.canvas;

import org.herebdragons.config.Config;
import org.herebdragons.graphics.enums.WindowBehaviour;
import org.herebdragons.graphics.objects.Manager;
import org.herebdragons.graphics.objects.notSoSimpleObject;
import org.herebdragons.utils.FrameRate;


import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.security.InvalidParameterException;

public class Canvas implements notSoSimpleCanvas {

    private JFrame window;
    private WindowBehaviour behaviorOnExit;
    private boolean isDecorated;
    private boolean isResizable;
    private String title;
    private Dimension dimension;
    private boolean fullScreenMode = false;

    private FrameRate frameRate;

    private GraphicsDevice graphicsDevice;
    private DisplayMode currentDisplayMode;
    private DisplayMode gameDisplayMode;
    private BufferStrategy bs;


    private Manager objectManager;


    Canvas(Dimension size) {

        dimension = size;

        if (size == null) {
            dimension = new Dimension(Config.MIN_SIZE, Config.MIN_SIZE);
            fullScreenMode = true;
        }

        if (dimension.height <= 0 || dimension.width <= 0) {
            throw new InvalidParameterException("Invalid Dimensions");
        }

        getGraphicsEnvironment();


    }

    public void setVisible(boolean visibility) {
        window.setVisible(visibility);
    }

    public void setFullScreen(boolean fullScreen) {

        DisplayMode dispMode = null;

        if (!fullScreen) {
            setDecorated(isDecorated);
            setResizable(isResizable);

            graphicsDevice.setFullScreenWindow(null);
            dispMode = currentDisplayMode;

        } else {

            if (!graphicsDevice.isFullScreenSupported()) {
                System.err.println("ERROR: Not Supported!!!");
            }

            setDecorated(false);
            setResizable(false);
            graphicsDevice.setFullScreenWindow(window);
            dispMode = gameDisplayMode;
        }


        if (graphicsDevice != null && graphicsDevice.isDisplayChangeSupported()) {
            try {
                graphicsDevice.setDisplayMode(dispMode);
            } catch (Exception ex) {
                System.err.println("Problem setting the display mode\n" + ex.getMessage());
            }
        }

    }

    public void close() {

        switch (behaviorOnExit) {
            case EXIT_ON_CLOSE:
                System.err.println("Exiting notSoSimpleGraphics");
                System.exit(0);
                break;
            case DO_NOTHING_ON_CLOSE:
                break;
            case HIDE_ON_CLOSE:
                window.setVisible(false);
                break;
            case DISPOSE_ON_CLOSE:
                break;
        }

    }

    private DisplayMode getDisplayMode() {

        DisplayMode currentDM = graphicsDevice.getDisplayMode();

        return new DisplayMode(
                currentDM.getWidth(), currentDM.getHeight(), DisplayMode.BIT_DEPTH_MULTI, currentDM.getRefreshRate());
    }

    private void getGraphicsEnvironment() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        graphicsDevice = ge.getDefaultScreenDevice();
        currentDisplayMode = getDisplayMode();
        gameDisplayMode = currentDisplayMode;

    }

    public void init() {

        window = new JFrame();

        if (dimension == null) {
            setFullScreen(true);
        } else {
            setDimension(dimension);
            setDecorated(isDecorated);
        }

        setBgColor(Color.black);

        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                close();
            }
        });

        setTitle(title);
        setLocationRelativeTo(null);
        setVisible(true);
        requestFocus();

        window.createBufferStrategy(2);
        bs = window.getBufferStrategy();
    }

    public void setIcon(Image image) {
        if (window == null)
            return;

        window.setIconImage(image);
    }

    public void update() {
        do {
            do {
                Graphics g = null;
                try {
                    g = bs.getDrawGraphics();
                    g.clearRect(0, 0, dimension.width, dimension.height);
                    objectManager.render(g);
                } finally {
                    if (g != null) {
                        g.dispose();
                    }
                }
            } while (bs.contentsRestored());
            bs.show();
        } while (bs.contentsLost());
    }

    public void addObject(notSoSimpleObject object) {
        objectManager.addObject(object);
    }

    public void hideObject(notSoSimpleObject object) {
        objectManager.hideObject(object);
    }

    public void destroyObject(notSoSimpleObject object) {
        objectManager.hideObject(object);
    }

    private void render(Graphics g) {
        g.setColor(Color.GREEN);
        g.drawString(frameRate.toString(), 30, 30);
    }

    public void run() {
        init();
    }

    public void setDecorated(boolean isDecorated) {
        this.isDecorated = isDecorated;
        if (window == null)
            return;

        window.setUndecorated(!isDecorated);
    }

    public boolean isDecorated() {
        return isDecorated;
    }

    public Color getBgColor() {
        if (window == null)
            return null;

        return window.getBackground();
    }

    public void setBgColor(Color bgColor) {
        if (window == null)
            return;
        window.setBackground(bgColor);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;

        if (window == null)
            return;

        window.setTitle(title);
    }

    public Dimension getDimension() {
        if (window != null)
            dimension = window.getSize();
        return dimension;
    }

    public void setDimension(Dimension dimension) {

        if (fullScreenMode)
            throw new IllegalStateException("You can't change the window dimensions while in Fullscreen mode");

        this.dimension = dimension;
        if (window == null)
            return;
        window.setSize(dimension);
    }

    public Point getLocation() {
        if (window == null)
            return null;
        return window.getLocation();
    }

    public void setLocation(Point location) {
        if (fullScreenMode)
            throw new IllegalStateException("You can't change the window position while in Fullscreen mode");


        if (window == null)
            return;
        window.setLocation(location);
    }

    public WindowBehaviour getBehaviorOnExit() {
        return behaviorOnExit;
    }

    public void setBehaviorOnExit(WindowBehaviour behaviorOnExit) {

        this.behaviorOnExit = behaviorOnExit;

    }

    public void setResizable(boolean resizable) {
        if (window == null)
            return;
        window.setResizable(resizable);
    }

    public boolean isResizable() {
        if (window == null)
            return false;
        return isResizable;
    }

    public void requestFocus() {
        if (window == null)
            return;
        window.requestFocus();
    }

    public Manager getObjectManager() {
        return objectManager;
    }

    public void setObjectManager(Manager objectManager) {
        this.objectManager = objectManager;
    }

    private void setLocationRelativeTo(Component component) {
        if (window == null)
            return;
        window.setLocationRelativeTo(component);
    }
}
