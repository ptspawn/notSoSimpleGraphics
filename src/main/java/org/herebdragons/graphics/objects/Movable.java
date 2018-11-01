package org.herebdragons.graphics.objects;

import java.awt.*;

public interface Movable {

    void moveTo(Point position);

    void move(Dimension vector);

    void move(int x, int y);

    void move(float x, float y);


}
