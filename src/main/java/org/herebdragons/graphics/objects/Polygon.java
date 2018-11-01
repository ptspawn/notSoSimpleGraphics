package org.herebdragons.graphics.objects;

import java.awt.*;

public class Polygon extends Shape {


    Polygon(int[] xPos, int[] yPos) {
        super();

        if (xPos.length != yPos.length)
            throw new IllegalArgumentException("Coordinate Arrays don't match in sizes");

        shape = new java.awt.Polygon(xPos, yPos, xPos.length);
        dimension = new Dimension(shape.getBounds().width, shape.getBounds().height);
        position = new Point(shape.getBounds().x, shape.getBounds().y);

    }

    @Override
    public void render(Graphics2D g) {

    }
}
