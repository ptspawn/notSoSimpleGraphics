package org.herebdragons.graphics.objects;

import org.herebdragons.Config;
import org.herebdragons.utils.Logger;

import java.awt.*;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

abstract class Shape extends notSoSimpleObject implements Fillable, Strokable{

    protected Paint fillColor = Config.DEFAULT_FILL_COLOR;
    protected Color strokeColor = Config.DEFAULT_STROKE_COLOR;
    protected BasicStroke stroke = Config.DEFAULT_STROKE_TYPE;

    protected java.awt.Shape shape;

    Shape(){
        super();
    }

    @Override
    public void render(Graphics2D g) {
        Logger.log("Rendering rectangle" + this);

        Graphics2D g2 = (Graphics2D)g.create();

       /* if (rotation != 0) {
            g2.rotate(rotation, position.x + dimension.width / 2, position.y + dimension.height / 2);
        } else {
            g2.rotate(0,position.x,position.y);
        }*/

        if (fillColor != null) {
            g2.setPaint(fillColor);
            g2.fill(shape);
        }
        if (stroke != null) {
            g2.setPaint(strokeColor);
            g2.setStroke(stroke);
            g2.draw(shape);
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

        this.fillColor=color;

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

    public Rectangle getBounds(){
        return shape.getBounds();
    }

    @Override
    public Point2D getCenter() {

        return new Point2D.Double(shape.getBounds().getCenterX(),shape.getBounds().getCenterY());
    }

//    @Override
//    public void setRotation(double rotation) {
//        super.setRotation(rotation);
//        applyRotation();
//    }
//
//    @Override
//    public void rotate(double radians) {
//        super.rotate(radians);
//        applyRotation();
//    }
//
//    private void applyRotation(){
//        AffineTransform at = new AffineTransform();
//        at.rotate(rotation, getPosition().getX() + anchor.getX(), getPosition().getY() + anchor.getY());
//        shape = at.createTransformedShape(shape.getBounds());
//
//    }

    @Override
    public void setRenderingHints(notSoSimpleRenderingHints[] hints) {
        super.setRenderingHints(hints);
    }

    @Override
    public notSoSimpleRenderingHints[] getRenderingHints() {
        return super.getRenderingHints();
    }
}
