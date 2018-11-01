package org.herebdragons.utils;

import java.awt.*;

public class DisplayModeWrapper {

    private DisplayMode dm;

    public DisplayModeWrapper(DisplayMode dm) {
        this.dm = dm;
    }


    public boolean equals(Object obj) {

        if (!(obj instanceof DisplayModeWrapper))
            return false;

        DisplayModeWrapper other = (DisplayModeWrapper) obj;

        if (dm.getWidth() != other.dm.getWidth())
            return false;
        if (dm.getHeight() != other.dm.getHeight())
            return false;
        if (dm.getRefreshRate() != other.dm.getRefreshRate())
            return false;
        if (dm.getBitDepth() != other.dm.getBitDepth())
            return false;

        return true;
    }

    public String toString() {
        return "" + dm.getWidth() + " x " + dm.getHeight() +
                "x" + dm.getBitDepth() + " " + dm.getRefreshRate() + "Hz";
    }

}
