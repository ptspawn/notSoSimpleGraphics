package org.herebdragons.graphics.objects;



import java.awt.*;

public class Rectangle extends Shape {

    public Rectangle(Dimension dimension, Point position) {
        super(dimension, position);
        shape = new java.awt.Rectangle(position.x, position.y, dimension.width, dimension.height);
    }

    public Rectangle(int xPos, int yPos, int height, int width) {
        super(new Dimension(width, height), new Point(xPos, yPos));
        shape = new java.awt.Rectangle(position.x, position.y, dimension.width, dimension.height);
    }

    @Override
    public void render(Graphics2D g2d) {

        shape = new java.awt.Rectangle(position.x, position.y, dimension.width, dimension.height);
        super.render(g2d);

    }

    @Override
    public String toString() {

        return "Rectangle@" + position.x + ":" + position.y + " | h:" + dimension.height + " w:" + dimension.width;
    }
}
