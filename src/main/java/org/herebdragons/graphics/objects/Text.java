package org.herebdragons.graphics.objects;

import org.herebdragons.utils.Logger;

import java.awt.*;

public class Text extends notSoSimpleObject {

    private String text;

    public Text(Dimension dimension, Point position, String text) {
        super(dimension, position);
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
        g2d.drawString(text, position.x, position.y);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        Logger.log("Setting Text");
        this.text = text;
    }

    @Override
    public String toString() {
        return "Text{" +
                "text='" + text + '\'' +
                ", dimension=" + dimension +
                ", position=" + position +
                ", rotation=" + rotation +
                '}';
    }
}
