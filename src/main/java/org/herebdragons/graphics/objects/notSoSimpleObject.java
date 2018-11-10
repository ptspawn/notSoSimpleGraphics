package org.herebdragons.graphics.objects;

import org.herebdragons.utils.UtilMath;

import java.awt.*;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

public abstract class notSoSimpleObject implements Drawable, Scalable, Rotatable, Movable {

    protected volatile double rotation;
    protected volatile Point2D anchor = new Point2D.Double(0, 0);

    //TODO
    protected notSoSimpleRenderingHints[] renderingHints;

    private Graphics g;

    notSoSimpleObject() {
    }

    public abstract void render(Graphics2D g);

    public abstract void moveTo(Point2D position);

    public abstract void moveTo(int x, int y);

    public abstract void moveTo(double x, double y);

    public abstract void move(Dimension velocity);

    public abstract void move(int x, int y);

    public abstract void move(double x, double y);

    public abstract Rectangle getBounds();

    public abstract Dimension getDimension();

    public abstract Point2D getPosition();

    public abstract Point2D getCenter();

    public Point2D getAnchor() {
        return anchor;
    }

    public void setAnchor(Point2D anchor) {
        this.anchor = anchor;
    }

    public void setAnchor(int x, int y){
        anchor.setLocation(x,y);
    }

    public void moveAnchor(Dimension movementVector) {
        anchor.setLocation(anchor.getX() + movementVector.getHeight(), anchor.getY() + movementVector.getHeight());
    }

    public void moveAnchor(int x, int y) {
        anchor.setLocation(anchor.getX() + x, anchor.getY() + y);
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public void rotate(double radians) {
        rotation = UtilMath.packRotation(rotation + radians);
    }

    public abstract void setDimension(Dimension newDimension);

    public abstract void scale(float scaleFactor);

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

                '}';
    }
}
