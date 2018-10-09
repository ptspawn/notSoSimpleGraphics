package org.herebdragons.graphics.canvas;

import org.herebdragons.Config;
import org.herebdragons.graphics.objects.Manager;
import org.herebdragons.graphics.objects.ObjectManager;
import org.herebdragons.utils.SystemManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * <center><h1><strong>AbstractRenderer class</strong></h1></center><br>
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
 * <p>Represents the <code>AbstractRenderer Renderer</code>.
 * The base of notSoSimpleGraphics<br>
 * And where all the graphics will be rendered.<br>
 * will be owned by the @link AbstractCanvas implementation
 * and will provide the skeleton for each of the renderers implemented</p>
 */
abstract class AbstractRenderer implements notSoSimpleRenderer {

    protected Dimension dimension;
    protected Manager objectManager;


    protected AbstractRenderer() {

    }

    public void init(notSoSimpleWindow window) {

    }

    public void init(notSoSimpleWindow window, CallBackable cb){

    }

    public abstract void render();

    public void close() {
        //testing
    }

    public void setObjectManager(Manager objManager) {
        this.objectManager = objManager;
    }

    public void setFullScreen(boolean fullScreen) {

    }

    public void resize(Dimension dimension) {
        this.dimension = dimension;
    }
}
