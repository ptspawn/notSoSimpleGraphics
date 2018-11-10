package org.herebdragons.utils;

import java.awt.*;

public class UtilMath {

    public static Dimension vectorTimesScalar(Dimension vector, float scalar) {

        //synchronized (vector) {

        Dimension s = new Dimension();
        s.setSize(vector.getWidth() * scalar, vector.getHeight() * scalar);

        return s;

        //}

    }

    public static double packRotation(double rotation) {
        if (rotation < 0) {
            rotation += 2 * Math.PI;
        }

        if (rotation > 2 * Math.PI) {
            rotation -= Math.PI;
        }

        return rotation;
    }

    public static int pack(int value, int lowerLimit, int upperLimit) {
        if (value < lowerLimit) {
            value = lowerLimit;
        }
        if (value > upperLimit) {
            value = upperLimit;
        }

        return value;
    }
}
