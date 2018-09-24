package org.herebdragons.graphics.canvas;

import java.awt.*;

public abstract class AbstractRenderer implements Renderer{


    public void init() {

    }

    public abstract void render(Graphics g);

    public void close() {

    }
}
