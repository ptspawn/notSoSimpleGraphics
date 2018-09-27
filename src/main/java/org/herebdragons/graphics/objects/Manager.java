package org.herebdragons.graphics.objects;

import java.awt.*;

public interface Manager {

    void addObject(notSoSimpleObject object);

    void hideObject(notSoSimpleObject object);

    void render(Graphics2D g);

}
