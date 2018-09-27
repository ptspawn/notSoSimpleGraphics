package org.herebdragons.graphics.objects;

import java.awt.*;

public interface Strokable {

    BasicStroke getStroke();


    void setStroke(BasicStroke stroke);

    Color getStrokeColor();

    void setStroke(Color color, BasicStroke stroke);

    void setStrokeColor(Color strokeColor);

}
