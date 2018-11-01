package org.herebdragons.graphics.objects;

import org.herebdragons.utils.Logger;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class ObjectManager implements notSoSimpleObjectManager {

    protected List<notSoSimpleObject> objects = new LinkedList<notSoSimpleObject>();

    public ObjectManager() {
    }

    public void addObject(notSoSimpleObject object) {
        objects.add(object);
    }

    public void hideObject(notSoSimpleObject object) {
        objects.remove(object);
    }

    public void render(Graphics2D g) {
        Logger.log("Rendering in " + this);

        for (notSoSimpleObject object : objects) {
            object.render(g);
        }

    }

    @Override
    public String toString() {
        return "ObjectManager{" +
                "objects=" + objects.size() +
                '}';
    }
}
