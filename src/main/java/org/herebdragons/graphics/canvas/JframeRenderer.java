package org.herebdragons.graphics.canvas;

import org.herebdragons.Config;
import org.herebdragons.utils.Logger;

import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * <center><h1><strong>JframeRenderer class</strong></h1></center><br>
 *
 * <center>@author Tiago Venceslau &lt;ptspawn@gmail.com&gt;</center><br>
 *
 * <center>https://github.com/ptspawn/notSoSimpleGraphics}</center><br>
 *
 * <center>created by tvenceslau in {Sep}/{2018}</center>
 *
 * <center>under the Apache 2.0 license as stated in @see <a href="https://www.apache.org/licenses/LICENSE-2.0.txt">LICENSE</a></center>
 *
 * <h2>Description:</h2><br>
 * <p>Represents the <code>JframeRenderer class</code><br>.
 */
class JframeRenderer extends AbstractRenderer {

    private notSoSimpleCanvas jcanvas;
    private Jwindow window;
    private Canvas canvas;
    private BufferStrategy bs;
    private Graphics2D g2d;

    public JframeRenderer(notSoSimpleCanvas jcanvas) {
        this.jcanvas = jcanvas;
    }

    @Override
    public void init(notSoSimpleWindow window) {

        if (!(window instanceof Jwindow))
            throw new IllegalArgumentException("Window not instance of Jwindow");

        this.window = (Jwindow) window;

        this.window.setLocationRelativeTo(null);

        this.canvas = new Canvas();

        this.window.add(canvas);

        canvas.setSize(this.window.getSize());

        this.window.setUndecorated(false);
        this.window.setIgnoreRepaint(true);

        this.window.setVisible(true);

        canvas.createBufferStrategy(Config.BUFFERING);

        do {
            bs = canvas.getBufferStrategy();
        } while (bs == null);



    //g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    public void setFullscreen(boolean fullscreen) {
      /*  DisplayMode dispMode = null;

        if (!fullscreen) {
            //setDecorated(isDecorated);
            //setResizable(isResizable);
            //setLocationRelativeTo(null);

            graphicsDevice.setFullScreenWindow(null);
            dispMode = currentDisplayMode;

        } else {

            if (!graphicsDevice.isFullScreenSupported()) {
                Logger.err("ERROR: Not Supported!!!");
            }

            //setDecorated(false);
            //setResizable(false);
            //graphicsDevice.setFullScreenWindow(window);
            dispMode = gameDisplayMode;
        }


        if (graphicsDevice != null && graphicsDevice.isDisplayChangeSupported()) {
            try {
                graphicsDevice.setDisplayMode(dispMode);
            } catch (Exception ex) {
                Logger.err("Problem setting the display mode\n" + ex.getMessage());
            }
        }*/
    }

    public void render() {
        Logger.log("Entering Update method from Canvas");

      //  do {
            //do {
                Graphics2D g2d = null;
                try {

                    Logger.log("Started Rendering");

                    g2d = (Graphics2D) canvas.getGraphics();
                    g2d.setPaint(jcanvas.getBgColor());
                    g2d.fill(canvas.getBounds());

                    objectManager.render(g2d);

                    Logger.log("Finished Rendering");

                } catch (Exception e) {
                    Logger.err("Exception " + e.getMessage());
                } finally {
                    if (g2d != null) {
                        g2d.dispose();
                    }
                }

            //} while (!bs.contentsRestored());

            //bs.show();  //

       // } while (bs.contentsLost());

    }

    @Override
    public void close() {

        //Finish up
        super.close();
    }
}
