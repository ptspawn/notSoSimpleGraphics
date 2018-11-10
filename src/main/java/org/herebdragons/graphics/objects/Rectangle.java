package org.herebdragons.graphics.objects;


import org.herebdragons.utils.UtilMath;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Rectangle extends Shape {

    private Rectangle2D castShapeRef;

    public Rectangle(Dimension dimension, Point position) {
        super();
        shape = new Rectangle2D.Double(position.x, position.y, dimension.width, dimension.height);
        anchor = new Point2D.Double(dimension.width / 2.0, dimension.height / 2.0);
        castShapeRef = castShape(shape);
    }

    public Rectangle(int xPos, int yPos, int width, int height) {
        super();
        shape = new java.awt.Rectangle(xPos, yPos, width, height);
        anchor = new Point2D.Double(xPos + width / 2.0, yPos + height / 2.0);
        castShapeRef = castShape(shape);
    }


    @Override
    public void render(Graphics2D g2d) {

        super.render(g2d);

    }

    @Override
    public void moveTo(Point2D position) {
        castShapeRef.setRect(position.getX(), position.getY(), getDimension().getWidth(), getDimension().getHeight());
    }

    @Override
    public void moveTo(int x, int y) {
        castShapeRef.setRect(x, y, getDimension().getWidth(), getDimension().getHeight());
    }

    @Override
    public void moveTo(double x, double y) {
        castShapeRef.setRect(x, y, getDimension().getWidth(), getDimension().getHeight());
    }


    @Override
    public void moveTo(Point position) {

    }

    @Override
    public void move(Dimension velocity) {
        move(velocity.getWidth(), velocity.getHeight());
    }

    @Override
    public void move(int x, int y) {
        castShapeRef.setRect((int) getPosition().getX() + x, (int) getPosition().getY() + y,
                getDimension().width, getDimension().height);

    }

    @Override
    public void move(double x, double y) {
        castShapeRef.setRect(getPosition().getX() + x, getPosition().getY() + y,
                getDimension().getWidth(), getDimension().getHeight());
    }

    @Override
    public Dimension getDimension() {
        Dimension dim = new Dimension();
        dim.setSize(castShapeRef.getWidth(), castShapeRef.getHeight());
        return dim;
    }


    @Override
    public java.awt.Rectangle getBounds() {

        return shape.getBounds();
    }

    @Override
    public Point2D getPosition() {
        return new Point2D.Double(castShapeRef.getX(), castShapeRef.getY());
    }

    @Override
    public void setDimension(Dimension newDimension) {
        castShapeRef.setRect(castShapeRef.getX(), castShapeRef.getY(),
                newDimension.getWidth(), newDimension.getHeight());
    }

    @Override
    public void scale(float scaleFactor) {
        //TODO - REMEMBER WE HAVE ANCHOR POINTS NOW

    }

    @Override
    public void setRotation(double rotation) {
        super.setRotation(rotation);
        applyRotation();
    }

    @Override
    public void rotate(double radians) {
        super.rotate(radians);
        applyRotation();
    }

    private void applyRotation(){
        AffineTransform at = new AffineTransform();
        at.rotate(rotation, getPosition().getX() + anchor.getX(), getPosition().getY() + anchor.getY());
        shape = at.createTransformedShape(shape.getBounds());

    }

    private Rectangle2D castShape(java.awt.Shape shape) {
        return (Rectangle2D) shape;
    }

    @Override
    public String toString() {

        java.awt.Rectangle bounds = castShapeRef.getBounds();

        return "Rectangle@" + bounds.x + ":" + bounds.getBounds().y + " | h:" + bounds.height + " w:" + bounds.width;
    }


}
