package org.herebdragons.graphics.canvas;

import org.herebdragons.utils.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.Canvas;
import java.awt.image.BufferedImage;

/**
 * <center><h1><strong>JFrameCanvas class</strong></h1></center><br>
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
 * <p>Represents the <code>JFrameCanvas class</code><br>.
 */
class JFrameCanvas extends AbstractCanvas {

    private Canvas canvas = new Canvas();

    JFrameCanvas(Dimension size) {
        super(size);
        //Graphics2D g2g = ((Graphics2D)new BufferedImage(10,10).getGraphics())
    }

    @Override
    public void run() {
        Logger.log("creating new window");
        Jwindow window = new Jwindow();
        super.setWindow(window);
        super.run();
    }
}
