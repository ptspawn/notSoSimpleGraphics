package org.herebdragons.graphics.canvas;

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
class Jwindow extends JFrame implements notSoSimpleWindow {

    private boolean isReady = false;

    @Override
    public void repaint(long l, int i, int i1, int i2, int i3) {
        //super.repaint();
        System.err.println("Called complex Repaint");
    }

    @Override
    public void repaint() {
        System.err.println("Called simple Repaint");
    }

    @Override
    public void repaint(long l) {
        System.err.println("Called long Repaint");
    }

    @Override
    public void paint(Graphics graphics) {
        //super.paint(graphics);
        System.err.println("Called Paint");
    }

    @Override
    public void addNotify() {
        super.addNotify();
        System.err.println("Called Notify");
        isReady = true;
    }

    @Override
    public void paintAll(Graphics graphics) {
        System.err.println("Called PaintAll");
    }

    public boolean isReady() {
        return isReady;
    }
}

