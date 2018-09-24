package org.herebdragons.graphics.canvas;

import org.herebdragons.graphics.enums.WindowBehaviour;
import org.herebdragons.graphics.objects.notSoSimpleObject;

import java.awt.*;

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
 *     Most of the canvas functionality will be here
 *     except for the rendering which is handled
 *     by the @link Renderer#</p>
 */
public class AbstractCanvas implements notSoSimpleCanvas{
    public void setVisible(boolean visibility) {

    }

    public void setFullScreen(boolean fullScreen) {

    }

    public void setDecorated(boolean isDecorated) {

    }

    public Color getBgColor() {
        return null;
    }

    public void setBgColor(Color bgColor) {

    }

    public String getTitle() {
        return null;
    }

    public void setTitle(String title) {

    }

    public Dimension getDimension() {
        return null;
    }

    public void setDimension(Dimension dimension) {

    }

    public Point getLocation() {
        return null;
    }

    public void setLocation(Point location) {

    }

    public WindowBehaviour getBehaviorOnExit() {
        return null;
    }

    public void setBehaviorOnExit(WindowBehaviour behaviorOnExit) {

    }

    public void setResizable(boolean resizable) {

    }

    public boolean isResizable() {
        return false;
    }

    public void requestFocus() {

    }

    public void close() {

    }

    public void init() {

    }

    public void setIcon(Image image) {

    }

    public void update() {

    }

    public void addObject(notSoSimpleObject object) {

    }

    public void hideObject(notSoSimpleObject object) {

    }

    public void destroyObject(notSoSimpleObject object) {

    }

    public void run() {

    }
}
