package org.herebdragons.graphics.objects;

import org.herebdragons.graphics.canvas.notSoSimpleCanvas;
import org.herebdragons.graphics.objects.notSoSimpleObject;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class ObjectManager implements Manager {

    protected List<notSoSimpleObject> objects = new LinkedList<notSoSimpleObject>();

    public ObjectManager() {
    }

    public void addObject(notSoSimpleObject object) {
        objects.add(object);
    }

    public void hideObject(notSoSimpleObject object) {
        objects.remove(object);
    }

    public void render(Graphics g) {
        for (notSoSimpleObject object : objects) {
            object.render(g);
        }
    }
}
