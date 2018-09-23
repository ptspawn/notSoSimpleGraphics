package org.herebdragons.graphics.objects;

import java.awt.*;

public interface Movable {

    public void moveTo(Point position);

    public void move(Dimension vector);
}
