package org.herebdragons.graphics.objects;

import java.awt.*;
import java.awt.geom.Point2D;

public class Polygon extends Shape {

    private java.awt.Polygon castShapeRef;

    Polygon(int[] xPos, int[] yPos) {
        super();

        if (xPos.length != yPos.length)
            throw new IllegalArgumentException("Coordinate Arrays don't match in sizes");

        shape = new java.awt.Polygon(xPos, yPos, xPos.length);

        castShapeRef = (java.awt.Polygon) shape;

        anchor.setLocation(getBounds().getCenterX(), getBounds().getCenterY());

    }

    @Override
    public void moveTo(Point position) {

        moveTo(position.x,position.y);

    }

    @Override
    public void moveTo(Point2D position) {

        //TODO this should work in doubles
        //TODO for now only in Ints

        moveTo(position.getX(),position.getY());

    }

    @Override
    public void moveTo(int x, int y) {

        Point currentPos = getPosition();

        castShapeRef.translate(currentPos.x - x, currentPos.y - y);
    }

    @Override
    public void moveTo(double x, double y) {

        //TODO not implemented a double version;
        moveTo(x,y);

    }

    @Override
    public void move(Dimension velocity) {

        move(velocity.getWidth(),velocity.getHeight());
    }

    @Override
    public void move(int x, int y) {
        castShapeRef.translate(x,y);
    }

    @Override
    public void move(double x, double y) {
        //Will this make a circular reference?
        //will i need to cast this?
        move(x,y);
    }



    @Override
    public Dimension getDimension() {
        return getBounds().getSize();
    }

    @Override
    public Point getPosition() {
        return getBounds().getLocation();
    }

    @Override
    public void setRotation(double rotation) {

        super.setRotation(rotation);
    }

    @Override
    public void rotate(double radians) {
        super.rotate(rotation);

    }

    @Override
    public void setDimension(Dimension newDimension) {
        //TODO
    }

    @Override
    public void scale(float scaleFactor) {
        //TODO
    }
}
