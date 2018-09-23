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

    public void render(Graphics g) {
        Logger.log("Rendering " + this);
        g.setColor(Color.GREEN);
        g.drawString(text, position.x, position.y);
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
