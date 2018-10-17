package org.herebdragons.graphics.objects;

import java.awt.*;

public interface notSoSimpleObjectManager {

    void addObject(notSoSimpleObject object);

    void hideObject(notSoSimpleObject object);

    void render(Graphics2D g);

}
