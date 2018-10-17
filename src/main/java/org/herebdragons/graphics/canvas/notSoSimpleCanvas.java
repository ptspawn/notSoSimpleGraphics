package org.herebdragons.graphics.canvas;

import org.herebdragons.graphics.enums.WindowBehaviour;
import org.herebdragons.graphics.objects.notSoSimpleObjectManager;
import org.herebdragons.graphics.objects.notSoSimpleObject;
import org.herebdragons.input.notSoSimpleKeyboardListener;
import org.herebdragons.input.notSoSimpleMouseListener;

import java.awt.*;

/**
 * <center><h1><strong>notSoSimpleCanvas interface</strong></h1></center><br>
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
 * <p>Represents the <code>notSoSimpleCanvas interface</code>.
 * The base of notSoSimpleGraphics
 * And where all the graphics will be displayed</p>
 */
public interface notSoSimpleCanvas extends Runnable {

    void setVisible(boolean visibility);

    void setFullScreen(boolean fullScreen);

    void setDecorated(boolean isDecorated);

    boolean isDecorated();

    boolean isFullscreen();

    Paint getBgColor();

    void setBgColor(Paint bgColor);

    String getTitle();

    void setTitle(String title);

    Dimension getDimension();

    void setDimension(Dimension dimension);

    Point getLocation();

    void setLocation(Point location);

    WindowBehaviour getBehaviorOnExit();

    void setBehaviorOnExit(WindowBehaviour behaviorOnExit);

    void setLocationRelativeTo(Component component);

    void requestFocus();

    void close();

    void setIcon(Image image);

    void update();

    void addObject(notSoSimpleObject object);

    void hideObject(notSoSimpleObject object);

    void destroyObject(notSoSimpleObject object);

    void addKeyListener(notSoSimpleKeyboardListener keyInput);

    void addMouseListener(notSoSimpleMouseListener mouseInput);

    void setObjectManager(notSoSimpleObjectManager objectManager);

    notSoSimpleRenderer getRenderer();

    void setRenderer(notSoSimpleRenderer renderer);

    notSoSimpleWindow getWindow();

    boolean isReady();

    void setReadyCallback(notSoSimpleRunnable callBack);

}
