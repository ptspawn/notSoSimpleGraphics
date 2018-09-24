package org.herebdragons.graphics.canvas;

import org.herebdragons.Config;
import org.herebdragons.graphics.enums.RendererType;
import org.herebdragons.graphics.enums.WindowBehaviour;
import org.herebdragons.graphics.objects.Manager;
import org.herebdragons.graphics.objects.notSoSimpleObject;
import org.herebdragons.input.notSoSimpleKeyboardListener;
import org.herebdragons.input.notSoSimpleMouseListener;
import org.herebdragons.utils.Logger;
import org.herebdragons.utils.SystemManager;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.security.InvalidParameterException;

public class Canvas implements notSoSimpleCanvas {

    private JFrame window;
    //private javafx.embed.swing.JFXPanel canvas;
    //private javafx.scene.canvas.Canvas canvas;

    private java.awt.Canvas canvas;
    private WindowBehaviour behaviorOnExit;
    private boolean isDecorated;
    private boolean isResizable;
    private volatile boolean isReady;
    private String title;
    private Dimension dimension;
    private boolean fullScreenMode = false;
    private Color bgColor = Config.DEFAULT_BG_COLOR;

    private KeyListener keyInput;
    private MouseListener mouseInput;

    private GraphicsDevice graphicsDevice;
    private DisplayMode currentDisplayMode;
    private DisplayMode gameDisplayMode;
    private BufferStrategy bs;

    private Manager objectManager;

    //JVM parameters:
    // -Dsun.java2d.opengl=True  - Enable openGL accelaration for swing components


    Canvas(Dimension size, RendererType type) {

        dimension = size;

        if (size == null) {
            dimension = new Dimension(800, 600); //Config.MIN_SIZE, Config.MIN_SIZE);
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
            setLocationRelativeTo(null);

            graphicsDevice.setFullScreenWindow(null);
            dispMode = currentDisplayMode;

        } else {

            if (!graphicsDevice.isFullScreenSupported()) {
                Logger.err("ERROR: Not Supported!!!");
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
                Logger.err("Problem setting the display mode\n" + ex.getMessage());
            }
        }

    }

    public void close() {

        switch (behaviorOnExit) {
            case EXIT_ON_CLOSE:
                Logger.err("Exiting notSoSimpleGraphics");
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


    private void getGraphicsEnvironment() {
        graphicsDevice = SystemManager.getGraphicsDevice();
        currentDisplayMode = SystemManager.getCurrentDisplayMode();
        gameDisplayMode = currentDisplayMode;

    }

    public void init() {

        Logger.log("creating new window");

        window = new JFrame();
        canvas = new java.awt.Canvas();
        window.add(canvas);

        //Input

        if (mouseInput!=null)
            window.addMouseListener(mouseInput);

        System.out.println("Adding " + keyInput);

        if (keyInput!=null)
            window.addKeyListener(keyInput);

        Logger.log("starting frame");

        if (fullScreenMode) {
            setFullScreen(true);
        } else {
            setDimension(dimension);
            //canvas.setSize(dimension);
            setDecorated(isDecorated);
            setLocationRelativeTo(null);
        }

        Logger.log("dimension set");

        canvas.setBackground(bgColor);



        window.setTitle(title);

        setVisible(true);

        Logger.log("Set windows visibility on");

        requestFocus();


        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                close();
            }
        });

        Logger.log("Set windows visibility on");

        requestFocus();

        //JUST FOR JAVAFX

        //final GraphicsContext gc = canvas.getGraphicsContext2D();

        //

        canvas.createBufferStrategy(Config.BUFFERING);
        bs = canvas.getBufferStrategy();

        Logger.log("Created Buffer Strategy");

        isReady = true;

    }

    public void setIcon(Image image) {
        if (window == null)
            return;

        window.setIconImage(image);
    }

    public void update() {

        Logger.log("Entering Update method from Canvas");

        if (!isReady) {
            Logger.log("Window not ready yet");
            return;
        }

        do {
            do {
                Graphics g = null;
                try {

                    Logger.log("Started Rendering");

                    g = bs.getDrawGraphics();
                    g.setColor(bgColor);
                    g.clearRect(0, 0, dimension.width, dimension.height);
                    g.fillRect(0, 0, dimension.width, dimension.height);

                    objectManager.render(g);

                    Logger.log("Finished Rendering");

                } catch (Exception e) {
                    Logger.err("Exception " + e.getMessage());
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
        Logger.log("Adding object to the Canvas");
        System.out.println("Adding object " + object);
        objectManager.addObject(object);
    }

    public void hideObject(notSoSimpleObject object) {
        Logger.log("removing object from the Canvas");
        objectManager.hideObject(object);
    }

    public void destroyObject(notSoSimpleObject object) {
        objectManager.hideObject(object);
    }

    public void addKeyListener(notSoSimpleKeyboardListener keyInput) {

        if (this.keyInput != null)
            throw new IllegalStateException("There is already a KeyListener");

        System.out.println("adding keyListener to frame");

        this.keyInput = keyInput;

        if (window != null) {
            window.addKeyListener(keyInput);
        }
    }

    public void addMouseListener(MouseListener mouseInput) {

        if (this.mouseInput != null)
            throw new IllegalStateException("There is already a mouseListener");

        this.mouseInput = mouseInput;

        if (window != null) {
            window.addMouseListener(mouseInput);
        }
    }

    private synchronized void render(Graphics g) {
        update();
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

        return bgColor;
    }

    public void setBgColor(Color bgColor) {
        this.bgColor = bgColor;

        if (window == null)
            return;

        window.setBackground(bgColor);

        if (canvas == null)
            return;

        canvas.setBackground(bgColor);
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

        if (dimension == null) {
            fullScreenMode = true;
            dimension = new Dimension(Config.MIN_SIZE, Config.MIN_SIZE);
        }

        if (fullScreenMode)
            return;

        this.dimension = dimension;
        if (window == null)
            return;
        window.setSize(dimension);
        if (canvas == null)
            return;
        canvas.setSize(dimension);
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

    @Override
    public String toString() {
        return "Canvas{" +
                "behaviorOnExit=" + behaviorOnExit +
                ", isDecorated=" + isDecorated +
                ", isResizable=" + isResizable +
                ", isReady=" + isReady +
                ", title='" + title + '\'' +
                ", dimension=" + dimension +
                ", fullScreenMode=" + fullScreenMode +
                ", bgColor=" + bgColor +
                ", bs=" + bs +
                ", objectManager=" + objectManager +
                '}';
    }
}
