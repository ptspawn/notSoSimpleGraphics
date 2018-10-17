package org.herebdragons.graphics.canvas;

import org.herebdragons.graphics.objects.notSoSimpleObjectManager;

/**
 * <center><h1><strong>Renderer interface</strong></h1></center><br>
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
 * <p>Represents the <code>Renderer interface</code>.
 * The base of notSoSimpleGrapics
 * And where all the graphics will be rendered
 * regardless of the implementation</p>
 */
public interface notSoSimpleRenderer {

    void init(notSoSimpleWindow window);

    void setObjectManager(notSoSimpleObjectManager objManager);

    void render();

    void close();

    boolean isReady();

   }
