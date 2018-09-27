package org.herebdragons.graphics.canvas;


import org.herebdragons.utils.Logger;


import java.awt.*;

class OtherName {


    //private javafx.embed.swing.JFXPanel canvas;
    //private javafx.scene.canvas.Canvas canvas;

    //setIgnoreRepaint(true);

    //JVM parameters:
    // -Dsun.java2d.opengl=True  - Enable openGL accelaration for swing components


//        System.setProperty("sun.java2d.opengl", "true");




/*
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
*/

    public void update() {


    }


    public void run() {

    }


}
