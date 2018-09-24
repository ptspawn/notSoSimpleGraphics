package org.herebdragons.graphics.canvas;

import java.awt.*;

public interface Renderer {
    public void init();
    public void render(Graphics g);
    public void close();
}
