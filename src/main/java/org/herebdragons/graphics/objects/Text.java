package org.herebdragons.graphics.objects;

import java.awt.*;

public class Text extends notSoSimpleObject {

    private String text;

    public Text(Dimension dimension, Point position, String text) {
        super(dimension, position);
        this.text = text;
    }

    public void render(Graphics g) {
        g.setColor(Color.GREEN);
        g.drawString(text, position.x, position.y);
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
