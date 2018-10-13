package org.herebdragons.graphics.canvas;

import org.herebdragons.utils.Logger;

import javax.swing.*;
import java.awt.*;


/**
 * <center><h1><strong>Jwindow class</strong></h1></center><br>
 *
 * <center>@author Tiago Venceslau &lt;ptspawn@gmail.com&gt;</center><br>
 *
 * <center>https://github.com/ptspawn/notSoSimpleGraphics}</center><br>
 *
 * <center>created by tvenceslau in Sep/2018</center>
 *
 * <center>under the Apache 2.0 license as stated in @see <a href="https://www.apache.org/licenses/LICENSE-2.0.txt">LICENSE</a></center>
 *
 * <h2>Description:</h2><br>
 * <p>Represents the <code>Jwindow class</code><br>.
 */
public class Jwindow extends JFrame implements notSoSimpleWindow {

    private boolean isReady = false;
    private boolean isFullscreen;

    Jwindow(GraphicsConfiguration gc){
        super(gc);
    }

    @Override
    public void repaint(long l, int i, int i1, int i2, int i3) {
        Logger.log("Entering complex repaint");
    }

    @Override
    public void repaint() {
        Logger.log("Entering repaint");
    }

    @Override
    public void repaint(long l) {
        Logger.log("Entering long Repaint");
    }

    @Override
    public void paint(Graphics graphics) {
        Logger.log("Entering Paint");
    }

    @Override
    public void addNotify() {
        Logger.log("Entering Notify");
        isReady = true;
    }

    public void setFullScreen(boolean isFullscreen){
        this.isFullscreen=isFullscreen;
    }

    public boolean isFullscreen(){
        return isFullscreen;
    }

    @Override
    public void paintAll(Graphics graphics) {
        Logger.log("Entering PaintAll");
    }

    public boolean isReady() {
        return isReady;
    }

}

