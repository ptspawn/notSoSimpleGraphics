package org.herebdragons.graphics.objects;


import org.herebdragons.Config;
import org.herebdragons.utils.Logger;

import java.awt.*;

public class Rectangle extends notSoSimpleObject implements Fillable, Strokable {

    private Color fillColor = Config.DEFAULT_FILL_COLOR;
    private Color strokeColor = Config.DEFAULT_STROKE_COLOR;
    private int strokeThickness = Config.DEFAULT_STROKE_THINKNESS;

    public Rectangle(Dimension dimension, Point position) {
        super(dimension, position);
    }

    public Rectangle(int xPos, int yPos, int height, int width) {
        super(new Dimension(width, height), new Point(xPos, yPos));
    }

    public void render(Graphics g) {
        Logger.log("Rendering rectangle" + this);
        g.setColor(fillColor);
        g.fillRect(position.x, position.y, position.x + dimension.width,
                position.y + dimension.height);
    }


    public void fill(Color color) {
        Logger.log("Fill color of " + this + " to " + color);
        fillColor = color;
    }

    public void stroke(Color color, int thickness) {
        Logger.log("Stroke color of " + this + " to " + color + " and thinkness " + thickness);
        strokeColor = color;
        strokeThickness = thickness;
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
