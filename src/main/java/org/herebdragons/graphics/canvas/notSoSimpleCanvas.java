package org.herebdragons.graphics.canvas;

import org.herebdragons.graphics.enums.WindowBehaviour;
import org.herebdragons.graphics.objects.notSoSimpleObject;

import java.awt.*;

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
