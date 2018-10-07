package org.herebdragons.graphics.canvas;

import org.herebdragons.Config;
import org.herebdragons.graphics.enums.WindowBehaviour;
import org.herebdragons.graphics.objects.Manager;
import org.herebdragons.graphics.objects.notSoSimpleObject;
import org.herebdragons.input.notSoSimpleKeyboardListener;
import org.herebdragons.input.notSoSimpleMouseListener;
import org.herebdragons.utils.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.security.InvalidParameterException;

/**
 * <center><h1><strong>AbstractCanvas class</strong></h1></center><br>
 *
 * <center>@author Tiago Venceslau &lt;ptspawn@gmail.com&gt;</center><br>
 *
 * <center>https://github.com/ptspawn/notSoSimpleGraphics}</center><br>
 *
 * <center>created by tvenceslau in set/2018</center>
 *
 * <center>under the Apache 2.0 license as stated in @see <a href="https://www.apache.org/licenses/LICENSE-2.0.txt">LICENSE</a></center>
 *
 * <h2>Description:</h2><br>
 * <p>Represents the <code>AbstractCanvas canvas</code>.
 * The base of notSoSimpleGraphics<br>
 * And where all the graphics will be displayed.<br>
 * Most of the canvas functionality will be here
 * except for the rendering which is handled
 * by the @link Renderer#</p>
 */
public abstract class AbstractCanvas implements notSoSimpleCanvas {

    private notSoSimpleWindow window;
    private WindowBehaviour behaviorOnExit;
    protected boolean isDecorated;
    private String title;
    protected Dimension dimension;
    private boolean fullScreenMode = false;
    private boolean isReady = false;
    private Paint bgColor = Config.DEFAULT_BG_COLOR;

    private KeyListener keyInput;
    private MouseListener mouseInput;

    private Manager objectManager;
    protected notSoSimpleRenderer renderer;

    AbstractCanvas(Dimension size) {
        dimension = size;

        if (size == null) {
            dimension = new Dimension(800, 600); //Config.MIN_SIZE, Config.MIN_SIZE);
            fullScreenMode = true;
        }

        if (dimension.height <= 0 || dimension.width <= 0) {
            throw new InvalidParameterException("Invalid Dimensions");
        }
    }

    public void run() {

        if (mouseInput != null) {
            window.addMouseListener(mouseInput);
            Logger.log("Adding " + keyInput);
        }

        if (keyInput != null)
            window.addKeyListener(keyInput);

        setLocationRelativeTo(null);

        Logger.log("starting frame");

        setDimension(dimension);
        setDecorated(isDecorated);

        Logger.log("dimension set");

        window.setTitle(title);
        window.setResizable(false);
        window.setIgnoreRepaint(true);
        window.setBackground(Color.RED);

        Logger.log("Set windows visibility on");

        window.addWindowListener(new WindowAdapter() {

            @Override
            public void windowOpened(WindowEvent windowEvent) {
                super.windowOpened(windowEvent);
                renderer.init(window);
                Logger.log("Window Ready" + windowEvent.paramString());
                isReady = true;
            }

            @Override
            public void windowClosing(WindowEvent windowEvent) {
                close();
            }
        });


        Logger.log("Set windows visibility on");

        //JUST FOR JAVAFX

        //final GraphicsContext gc = canvas.getGraphicsContext2D();

        //

        //canvas.createBufferStrategy(Config.BUFFERING);
        //bs = canvas.getBufferStrategy();

        setVisible(true);
        requestFocus();

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

    public void addMouseListener(notSoSimpleMouseListener mouseInput) {
        if (this.mouseInput != null)
            throw new IllegalStateException("There is already a mouseListener");

        this.mouseInput = mouseInput;

        if (window != null) {
            window.addMouseListener(mouseInput);
        }
    }

    public void setVisible(boolean visibility) {
        window.setVisible(visibility);
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

    public boolean isFullscreen() {
        return fullScreenMode;
    }

    public void setFullScreen(boolean fullscreen) {
        fullScreenMode = true;
    }

    public Paint getBgColor() {

        return bgColor;
    }

    public void setBgColor(Paint bgColor) {
        this.bgColor = bgColor;

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

    public void requestFocus() {
        if (window == null)
            return;
        window.requestFocus();
    }

    public void setObjectManager(Manager objectManager) {
        this.objectManager = objectManager;
    }

    public void setLocationRelativeTo(Component component) {
        if (window == null)
            return;
        window.setLocationRelativeTo(component);
    }

    public void close() {

        switch (behaviorOnExit) {
            case DO_NOTHING_ON_CLOSE:
                break;
            case HIDE_ON_CLOSE:
                setVisible(false);
                break;
            case EXIT_ON_CLOSE:
                window.dispose();
                Logger.err("Exiting notSoSimpleGraphics");
                System.exit(0);
                break;
            case DISPOSE_ON_CLOSE:
                window.dispose();
                break;
        }

    }

    public void setIcon(Image image) {
        if (window == null)
            return;

        window.setIconImage(image);
    }

    public void update() {

        //if (renderer != null) {
            renderer.render();
        //}
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

    public void setRenderer(notSoSimpleRenderer renderer) {
        this.renderer = renderer;
    }

    public notSoSimpleRenderer getRenderer() {
        return renderer;
    }

    public Manager getObjectManager() {
        return objectManager;
    }

    protected void setWindow(notSoSimpleWindow window) {
        this.window = window;
    }

    public notSoSimpleWindow getWindow() {
        return window;
    }

    public boolean isReady() {
        System.out.println(isReady);
        return isReady;
    }

    @Override
    public String toString() {
        return "Canvas{" +
                "behaviorOnExit=" + behaviorOnExit +
                ", isDecorated=" + isDecorated +
                ", title='" + title + '\'' +
                ", dimension=" + dimension +
                ", fullScreenMode=" + fullScreenMode +
                ", bgColor=" + bgColor +
                ", objectManager=" + objectManager +
                '}';

    }
}