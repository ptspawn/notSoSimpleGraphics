package org.herebdragons.graphics.canvas;

import org.herebdragons.graphics.enums.WindowBehaviour;
import org.herebdragons.graphics.objects.notSoSimpleObject;

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

    public void setVisible(boolean visibility);

    public void setFullScreen(boolean fullScreen);

    public void setDecorated(boolean isDecorated);

    public Color getBgColor();

    public void setBgColor(Color bgColor);

    public String getTitle();

    public void setTitle(String title);

    public Dimension getDimension();

    public void setDimension(Dimension dimension);

    public Point getLocation();

    public void setLocation(Point location);

    public WindowBehaviour getBehaviorOnExit();

    public void setBehaviorOnExit(WindowBehaviour behaviorOnExit);

    public void setResizable(boolean resizable);

    public boolean isResizable();

    public void requestFocus();

    public void close();

    public void init();

    public void setIcon(Image image);

    public void update();

    public void addObject(notSoSimpleObject object);

    public void hideObject(notSoSimpleObject object);

    public void destroyObject(notSoSimpleObject object);

}
