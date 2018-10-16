package org.herebdragons.graphics.objects;

import org.herebdragons.utils.UtilMath;

import java.awt.*;

public abstract class notSoSimpleObject implements Drawable, Scalable, Rotatable, Movable {

    protected Dimension dimension;
    protected Point position;


    protected float rotation;
    private Graphics g;

    notSoSimpleObject(Dimension dimension, Point position) {
        this.position = position;
        this.dimension = dimension;
    }

    public abstract void render(Graphics2D g);

    public void moveTo(Point position) {
        this.position = position;
    }

    public void moveTo(int x, int y) {
        this.position.x = x;
        this.position.y = y;
    }

    public void move(Dimension velocity) {
        position.move(velocity.width, velocity.height);
    }

    public void move(int x, int y) {
        position.x+=x;
        position.y+=y;
        //position.setLocation(position.x + x, position.y + y);
    }

    public void move(float x, float y) {
        position.setLocation(position.getX() + x, position.getY() + y);
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

    public void setRotation(float rotation) {
        this.rotation = UtilMath.packRotation(rotation);
    }

    public void rotate(float radians) {
        rotation = UtilMath.packRotation(rotation += radians);
    }

    public void setDimension(Dimension newDimension) {
        dimension = newDimension;
    }

    public void scale(float scaleFactor) {
        dimension = UtilMath.vectorTimesScalar(dimension, scaleFactor);

    }

    @Override
    public String toString() {
        return "notSoSimpleObject{" +
                "dimension=" + dimension +
                ", position=" + position +
                ", rotation=" + rotation +
                ", g=" + g +
                '}';
    }
}
