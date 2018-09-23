package org.herebdragons.graphics.objects;

import java.awt.*;

public abstract class notSoSimpleObject implements Drawable, Scalable, Rotatable, Movable {

    protected Dimension dimension;
    protected Point position;
    protected float rotation;

    notSoSimpleObject(Dimension dimension, Point position) {
        this.position = position;
        this.dimension = dimension;
    }

    public abstract void render(Graphics g);

    public void moveTo(Point position) {
        this.position = position;
    }

    public void move(Dimension vector) {
        position = new Point(position.x + vector.width, position.y + vector.height);
    }

    public Dimension getDimension() {
        return dimension;
    }

    public Point getPosition() {
        return position;
    }

    public float getRotation() {
        return rotation;
    }


    public void rotate(float radians) {

    }

    public void scale(Dimension newDimension) {

    }
}
