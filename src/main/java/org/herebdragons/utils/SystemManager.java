package org.herebdragons.utils;

import java.awt.*;

public class SystemManager {

    private static GraphicsDevice graphicsDevice;
    private static GraphicsEnvironment ge;
    private static DisplayMode dm;

    public static String[] getFonts() {
        if (ge == null)
            getDevices();

        return ge.getAvailableFontFamilyNames();
    }

    private static void getDevices() {
        if (ge == null)
            ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

        if (graphicsDevice == null)
            graphicsDevice = ge.getDefaultScreenDevice();

        if (dm == null)
            dm = graphicsDevice.getDisplayMode();
    }

    public static DisplayMode[] getAvailableGraphicsMode() {
        if (ge == null)
            getDevices();

        return ge.getDefaultScreenDevice().getDisplayModes();
    }

    public static DisplayMode getCurrentDisplayMode() {

        if (dm == null)
            getDevices();

        return new DisplayMode(
                dm.getWidth(), dm.getHeight(), DisplayMode.BIT_DEPTH_MULTI, dm.getRefreshRate());
    }

    public static GraphicsDevice getGraphicsDevice() {
        if (graphicsDevice == null)
            getDevices();
        return graphicsDevice;
    }
}
