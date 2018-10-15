package org.herebdragons.graphics.canvas;

import org.herebdragons.Config;
import org.herebdragons.utils.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferStrategy;
import java.lang.reflect.InvocationTargetException;

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

    private boolean ready;

    JframeRenderer(notSoSimpleCanvas jcanvas) {
        this.jcanvas = jcanvas;
    }

    @Override
    public synchronized void init(notSoSimpleWindow window) {

        if (!(window instanceof Jwindow))
            throw new IllegalArgumentException("Window not instance of Jwindow");

        this.window = (Jwindow) window;

        if (jcanvas.isFullscreen()) {

            SystemManager.goFullScreen(this.window);

            this.window.createBufferStrategy(Config.BUFFERING);

        } else {

            this.canvas = new Canvas();

            canvas.setIgnoreRepaint(true);

            this.window.add(canvas);

            canvas.setSize(this.window.getSize());

            canvas.createBufferStrategy(Config.BUFFERING);

        }

        bs = jcanvas.isFullscreen() ? this.window.getBufferStrategy() : canvas.getBufferStrategy();

        Logger.log("Got a Buffering Strategy - " + bs);

        ready = true;

        Logger.log("Renderer Ready");
    }

    public void render() {

        Logger.log("Entering Update method from Canvas");

        do {
            do {
                Graphics2D g2d = null;
                try {

                    Logger.log("Started Rendering");
                    g2d = (Graphics2D) bs.getDrawGraphics();

                    if (bs.contentsLost() || bs.contentsRestored())
                        continue;

                    fillBackground(g2d);

                    objectManager.render(g2d);

                    Logger.log("Finished Rendering");

                } catch (Exception e) {
                    Logger.err("Exception in Rendering" + e.getMessage());
                } finally {
                    if (g2d != null) {
                        //Toolkit.getDefaultToolkit().sync();
                        g2d.dispose();
                    }
                }

                Logger.log("BufferStrategy contents Restored: " + bs.contentsRestored());

            } while (bs.contentsRestored());

            Logger.log("BufferStrategy contents lost: " + bs.contentsLost());

            bs.show();

        } while (bs.contentsLost());

        Logger.log("Renderer painted to window");
    }


    private void fillBackground(Graphics2D g2d) {
        g2d.setPaint(jcanvas.getBgColor());
        g2d.fillRect(0, 0, jcanvas.getDimension().width, jcanvas.getDimension().height);
    }

    @Override
    public void close() {
        Logger.log("Called JFrame Renderer Close");
        //Finish up
        super.close();
    }

    public boolean isReady() {
        return ready;
    }
}
