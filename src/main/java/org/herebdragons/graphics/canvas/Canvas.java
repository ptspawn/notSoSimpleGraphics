package org.herebdragons.graphics.canvas;

import org.herebdragons.config.Config;
import org.herebdragons.graphics.enums.WindowBehaviour;
import org.herebdragons.utils.FrameRate;


import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.security.InvalidParameterException;

public class Canvas implements Runnable {

    private JFrame window;

    private FrameRate frameRate;

    private GraphicsDevice graphicsDevice;
    private DisplayMode currentDisplayMode;
    private BufferStrategy bs;

    public static Canvas createCanvas(String Title, Dimension size) {
        return createCanvas(Title, size, WindowBehaviour.EXIT_ON_CLOSE, true);
    }

    public static Canvas createCanvas(String title) {
        Canvas canvas = createCanvas(title, null, WindowBehaviour.EXIT_ON_CLOSE, false);

        return canvas;
    }

    public static Canvas createCanvas(final String title, final Dimension size, final WindowBehaviour behaviourOnExit, final boolean isDecorated) {

        final Canvas canvas = new Canvas(size);

        SwingUtilities.invokeLater(new Runnable() {

            public void run() {

                canvas.createAndShowGUI(title, size, behaviourOnExit, isDecorated);
            }
        });

        return canvas;

    }


    private Canvas(Dimension size) {

        if (size == null) {
            size = new Dimension(Config.MIN_SIZE, Config.MIN_SIZE);
        }

        if (size.height <= 0 || size.width <= 0) {

            throw new InvalidParameterException("Invalid Dimensions");
        }

    }

    public void setVisible(boolean visibility) {
        window.setVisible(visibility);
    }

    public void setFullScreen(boolean fullScreen) {
        if (!graphicsDevice.isFullScreenSupported()) {
            System.err.println("ERROR: Not Supported!!!");
        }

        setDecorated(false);
        setResizable(false);
        graphicsDevice.setFullScreenWindow(window);

        if (graphicsDevice != null && graphicsDevice.isDisplayChangeSupported()) {
            try {
                graphicsDevice.setDisplayMode(getDisplayMode());
            } catch (Exception ex) {
                System.out.println("Problem setting the display mode");
            }
        }

        System.out.println(getDimension().width + " " + getDimension().height);

    }

    private void stop() {
        System.err.println("Exiting notSoSimpleGraphics");
        System.exit(0);
    }

    private DisplayMode getDisplayMode() {

        DisplayMode currentDM = graphicsDevice.getDisplayMode();

        return new DisplayMode(
                currentDM.getWidth(), currentDM.getHeight(), DisplayMode.BIT_DEPTH_MULTI, currentDM.getRefreshRate());
    }

    private void getGraphicsEnvironment() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        graphicsDevice = ge.getDefaultScreenDevice();
        currentDisplayMode = graphicsDevice.getDisplayMode();

        System.out.println(currentDisplayMode.getWidth() + "x" + currentDisplayMode.getHeight() +
                "x" + currentDisplayMode.getBitDepth() + " " + currentDisplayMode.getRefreshRate() + "Hz");
    }

    public void createAndShowGUI(String title, Dimension dimension, WindowBehaviour behaviourOnExit, boolean isDecorated) {

        getGraphicsEnvironment();

        window = new JFrame();

        java.awt.Canvas canvas = new java.awt.Canvas();

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
                stop();
            }
        });

        setTitle(title);
        setLocationRelativeTo(null);
        setVisible(true);
        requestFocus();

        window.createBufferStrategy(2);
        bs = window.getBufferStrategy();
    }

    public void run() {

    }


    public void setDecorated(boolean isDecorated) {
        window.setUndecorated(!isDecorated);
    }

    public boolean isDecorated() {
        return !window.isUndecorated();
    }

    public Color getBgColor() {
        return window.getBackground();
    }

    public void setBgColor(Color bgColor) {
        window.setBackground(bgColor);
    }

    public String getTitle() {
        return window.getTitle();
    }

    public void setTitle(String title) {
        window.setTitle(title);
    }

    public Dimension getDimension() {
        return window.getSize();
    }

    public void setDimension(Dimension dimension) {
        window.setSize(dimension);
    }

    public Point getLocation() {
        return window.getLocation();
    }

    public void setLocation(Point location) {
        window.setLocation(location);
    }

    public void setResizable(boolean resizable) {
        window.setResizable(resizable);
    }

    public void requestFocus() {
        window.requestFocus();
    }

    private void setLocationRelativeTo(Component component) {
        window.setLocationRelativeTo(null);
    }
}
