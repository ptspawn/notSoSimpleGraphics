package org.herebdragons.graphics.objects;

import java.awt.*;

public class Rectangle extends notSoSimpleObject {

    Rectangle(Dimension dimension, Point position) {
        super(dimension, position);
    }

    public void render(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(position.x, position.y, position.x + dimension.width,
                position.y + dimension.height);
    }
}
