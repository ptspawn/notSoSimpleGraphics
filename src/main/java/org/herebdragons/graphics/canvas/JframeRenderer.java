package org.herebdragons.graphics.canvas;

import org.herebdragons.utils.Logger;

import java.awt.*;

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


    public void setFullscreen(boolean fullscreen) {
        DisplayMode dispMode = null;

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
        }
    }

    public void render() {
        Logger.log("Entering Update method from Canvas");

        /*if (!isReady) {
            Logger.log("Window not ready yet");
            return;
        }*/

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


}
