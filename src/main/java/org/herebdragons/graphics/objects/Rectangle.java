package org.herebdragons.graphics.objects;


import org.herebdragons.Config;
import org.herebdragons.utils.Logger;

import java.awt.*;

public class Rectangle extends notSoSimpleObject implements Fillable, Strokable {

    private Paint fillColor = Config.DEFAULT_FILL_COLOR;
    private Color strokeColor = Config.DEFAULT_STROKE_COLOR;
    private BasicStroke stroke = Config.DEFAULT_STROKE_TYPE;
    private java.awt.Rectangle rectangle;

    public Rectangle(Dimension dimension, Point position) {
        super(dimension, position);
    }

    public Rectangle(int xPos, int yPos, int height, int width) {
        super(new Dimension(width, height), new Point(xPos, yPos));
    }

    public void render(Graphics2D g2d) {
        Logger.log("Rendering rectangle" + this);

        rectangle = new java.awt.Rectangle(position.x, position.y,
                dimension.width, dimension.height);

        if (fillColor != null) {
            g2d.setPaint(fillColor);
            g2d.fill(rectangle);
        }
        if (stroke != null) {
            g2d.setPaint(strokeColor);
            g2d.setStroke(stroke);
            g2d.draw(rectangle);
        }

    }


    public void setFill(Paint color) {
        Logger.log("Fill color of " + this + " to " + color);
        fillColor = color;
    }


    public void setStroke(Color color, BasicStroke stroke) {
        Logger.log("Stroke color of " + this + " to " + color);
        strokeColor = color;
    }

    public Paint getFill() {
        return fillColor;
    }

    public Color getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    public BasicStroke getStroke() {
        return stroke;
    }

    public void setStroke(BasicStroke stroke) {
        this.stroke = stroke;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
