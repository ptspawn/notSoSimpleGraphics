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

        Graphics2D g2 = (Graphics2D)g2d.create();

        rectangle = new java.awt.Rectangle(position.x, position.y,
                dimension.width, dimension.height);
        if (rotation != 0) {
            g2.rotate(rotation, position.x + dimension.width / 2, position.y + dimension.height / 2);
        } else {
            g2.rotate(0,position.x,position.y);
        }
        if (fillColor != null) {
            g2.setPaint(fillColor);
            g2.fill(rectangle);
        }
        if (stroke != null) {
            g2.setPaint(strokeColor);
            g2.setStroke(stroke);
            g2.draw(rectangle);
        }

        g2.dispose();

    }

    public void setFill(Paint color) {
        Logger.log("Fill color of " + this + " to " + color);
//        if (color instanceof GradientPaint){
//            GradientPaint gradient = (GradientPaint)color; //new GradientPaint(0,0,Color.RED,100, 0,Color.WHITE);
//
//            fillColor=new GradientPaint(position.x + ((GradientPaint) color).getPoint1().getX(),
//                    position.y+((GradientPaint) color).getPoint1().getY(),
//                    ((GradientPaint) color).getColor1(),
//                    position.x+((GradientPaint) color).getPoint2().getX(),
//                    position.y+((GradientPaint) color).getPoint2().getY(),
//                    ((GradientPaint) color).getColor2());
//        }

        fillColor = color;
    }

    public void setStroke(Color color, BasicStroke stroke) {
        Logger.log("Stroke color of " + this + " to " + color);
        strokeColor = color;
        this.stroke = stroke;
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
