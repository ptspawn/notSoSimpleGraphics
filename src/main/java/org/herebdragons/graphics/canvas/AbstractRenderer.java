package org.herebdragons.graphics.canvas;

import java.awt.*;
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
 *     will be owned by the @link AbstractCanvas implementation
 *     and will provide the skeleton for each of the renderers implemented</p>
 */
public abstract class AbstractRenderer implements Renderer{


    public void init() {

    }

    public abstract void render(Graphics g);

    public void close() {
        //testing
    }
}
