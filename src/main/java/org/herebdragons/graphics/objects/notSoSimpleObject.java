package org.herebdragons.graphics.objects;

import org.herebdragons.utils.UtilMath;

import java.awt.*;

public abstract class notSoSimpleObject implements Drawable, Scalable, Rotatable, Movable {

    volatile protected Dimension dimension;
    volatile protected Point position;
    volatile protected float rotation;

    //TODO
    protected notSoSimpleRenderingHints[] renderingHints;

    private Graphics g;

    notSoSimpleObject(Dimension dimension, Point position) {
        this.position = position;
        this.dimension = dimension;
    }

    notSoSimpleObject(){};

    public abstract void render(Graphics2D g);

    public void moveTo(Point position) {
        this.position = position;
    }

    public void moveTo(int x, int y) {
        this.position.setLocation(x, y);

    }

    public void move(Dimension velocity) {
        position.move(velocity.width, velocity.height);
    }

    public void move(int x, int y) {

        position.setLocation(position.x + x, position.y + y);
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

    public Point getCenter() {

        //TODO: doesnt account for rotation
        return new Point(position.x + dimension.width / 2, position.y + dimension.height / 2);
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
    public void setRenderingHints(notSoSimpleRenderingHints[] hints) {
        //TODO
    }

    @Override
    public notSoSimpleRenderingHints[] getRenderingHints() {
        //TODO
        return new notSoSimpleRenderingHints[0];
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
