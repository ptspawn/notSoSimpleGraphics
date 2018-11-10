package org.herebdragons.graphics.objects;

import org.herebdragons.utils.Logger;

import java.awt.*;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

public class Text extends notSoSimpleObject {

    private String text;

    public Text(Dimension dimension, Point position, String text) {
        super();
        this.text = text;
        Logger.log("created this");
    }

    public void render(Graphics2D g2d) {
        Logger.log("Rendering " + this);
        g2d.setColor(Color.GREEN);

        g2d.setFont(new Font("Serif", Font.BOLD, 20));
        FontMetrics fm = g2d.getFontMetrics();
//        int x = img.getWidth() - fm.stringWidth(s) - 5;
//        int y = fm.getHeight();
        //g2d.drawString(text, position.x, position.y);
    }

    @Override
    public void moveTo(Point2D position) {

    }

    @Override
    public void moveTo(Point position) {

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        Logger.log("Setting Text");
        this.text = text;
    }

    @Override
    public Rectangle getBounds() {
        return null; //new Rectangle(position.x,position.y,dimension.width,dimension.height);
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
    public void setDimension(Dimension newDimension) {

    }

    @Override
    public void scale(float scaleFactor) {

    }

    @Override
    public String toString() {
        return "Text{" +
                "text='" + text + '\'' +

                ", rotation=" + rotation +
                '}';
    }
}
