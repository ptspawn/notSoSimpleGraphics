package org.herebdragons.graphics.objects;

import java.awt.*;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

public class Polygon extends Shape {


    Polygon(int[] xPos, int[] yPos) {
        super();

        if (xPos.length != yPos.length)
            throw new IllegalArgumentException("Coordinate Arrays don't match in sizes");

        shape = new java.awt.Polygon(xPos, yPos, xPos.length);

        Rectangle bounds = super.getBounds();

        //dimension = new Dimension(bounds.width, bounds.height);
        //position = new Point(bounds.x, bounds.y);

    }



    @Override
    public void moveTo(Point position) {

    }

    @Override
    public void moveTo(Point2D position) {

    }

    @Override
    public void moveTo(int x, int y) {

    }

    @Override
    public void moveTo(double x, double y) {

    }

    @Override
    public void move(Dimension velocity) {

    }

    @Override
    public void move(int x, int y) {

    }

    @Override
    public void move(double x, double y) {

    }

    @Override
    public void move(float x, float y) {

    }

    @Override
    public Dimension getDimension() {
        return null;
    }

    @Override
    public Point getPosition() {
        return null;
    }

    @Override
    public Point getCenter() {
        return null;
    }

    @Override
    public double getRotation() {
        return 0;
    }

    @Override
    public void setRotation(double rotation) {

    }

    @Override
    public void rotate(double radians) {

    }

    @Override
    public void setDimension(Dimension newDimension) {

    }

    @Override
    public void scale(float scaleFactor) {

    }
}
