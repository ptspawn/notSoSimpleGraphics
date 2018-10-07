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
class Jwindow extends JFrame implements notSoSimpleWindow {

    private boolean isReady = false;
    private notSoSimpleRenderer renderer;

    @Override
    public void repaint(long l, int i, int i1, int i2, int i3) {
        Logger.log("Entering complex repaint");
        //super.repaint(l,i,i1,i2,i3);

        if (renderer != null) {
            Logger.err("Calling Render from complex repaint");
            renderer.render();
        }
    }

    @Override
    public void repaint() {
        Logger.log("Entering repaint");
        super.repaint();
        if (renderer != null) {
            Logger.err("Calling Render from simple repaint");
            renderer.render();
        }
    }

    @Override
    public void repaint(long l) {
        Logger.log("Entering long Repaint");
        super.repaint(l);
        if (renderer != null) {
            Logger.err("Calling Render from long repaint");
            renderer.render();
        }
    }

    @Override
    public void paint(Graphics graphics) {
        Logger.log("Entering Paint");
        super.paint(graphics);
        if (renderer != null) {
            Logger.err("Calling Render from Paint");
            renderer.render();
        }
    }

    @Override
    public void addNotify() {
        super.addNotify();

        Logger.log("Entering Notify");
        isReady = true;
        if (renderer !=null){
            Logger.err("Calling Render from Notify");
            renderer.render();
        }

    }

    @Override
    public void paintAll(Graphics graphics) {

        Logger.log("Entering PaintAll");

        if (renderer != null) {
            Logger.err("Calling Render from paintAll");
            renderer.render();
        }
    }

    public boolean isReady() {
        return isReady;
    }

    public notSoSimpleRenderer getRenderer() {
        return renderer;
    }

    public void setRenderer(notSoSimpleRenderer renderer) {
        this.renderer = renderer;
    }
}

