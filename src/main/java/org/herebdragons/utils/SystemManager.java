package org.herebdragons.utils;

import java.awt.*;

public class SystemManager {

    private static GraphicsDevice graphicsDevice;
    private static GraphicsConfiguration graphicsConfig;
    private static GraphicsEnvironment ge;
    private static DisplayMode dm;

    public static String[] getFonts() {
        if (ge == null)
            getDevices();

        return ge.getAvailableFontFamilyNames();
    }

    public static GraphicsConfiguration getGraphicsConfiguration() {
        ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        graphicsDevice = ge.getDefaultScreenDevice();
        return graphicsDevice.getDefaultConfiguration();
    }

    public static GraphicsConfiguration getBestGraphicsConfiguration() {
        ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        graphicsDevice = ge.getDefaultScreenDevice();
        return graphicsDevice.getBestConfiguration(new GraphicsConfigTemplate() {
            @Override
            public GraphicsConfiguration getBestConfiguration(GraphicsConfiguration[] graphicsConfigurations) {
                for (GraphicsConfiguration gc : graphicsConfigurations) {

                    int result = 0;
                    if (gc.getBufferCapabilities().isMultiBufferAvailable())
                        result++;


                }

                return null; ///////////////
            }

            @Override
            public boolean isGraphicsConfigSupported(GraphicsConfiguration graphicsConfiguration) {
                boolean result = false;

                if (!graphicsConfiguration.getBufferCapabilities().getBackBufferCapabilities().isAccelerated())
                    return false;
                if (!graphicsConfiguration.getBufferCapabilities().getBackBufferCapabilities().isTrueVolatile())
                    return false;


                return result;
            }

        });
    }

    public static boolean checkCfg(GraphicsConfiguration gc) {
        System.setProperty("Dsun.java2d.opengl", "True");
        Logger.setLogging(true);
        Logger.err(System.getProperty("Dsun.java2d.opengl"));
        Logger.log("Backbuffer accel: " + gc.getBufferCapabilities().getBackBufferCapabilities().isAccelerated());
        Logger.log("Backbuffer volat: " + gc.getBufferCapabilities().getBackBufferCapabilities().isTrueVolatile());
        Logger.log("Frontbuffer accel: " + gc.getBufferCapabilities().getFrontBufferCapabilities().isAccelerated());
        Logger.log("Frontbuffer volat: " + gc.getBufferCapabilities().getFrontBufferCapabilities().isTrueVolatile());
        Logger.log("MultiBuffer avail: " + gc.getBufferCapabilities().isMultiBufferAvailable());
        Logger.log("Page Flipping: " + gc.getBufferCapabilities().isPageFlipping());
        Logger.log("Requires Full Screen: " + gc.getBufferCapabilities().isFullScreenRequired());

        Logger.log("Img Volatile: " + gc.getImageCapabilities().isTrueVolatile());
        Logger.log("Img Accelarated: " + gc.getImageCapabilities().isAccelerated());

        Logger.log("Available Accel Mem: " + gc.getDevice().getAvailableAcceleratedMemory());
        Logger.setLogging(false);
        return true; ///////////
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

    public static DisplayMode convertDisplayMode(DisplayMode dm) {

        return new DisplayMode(
                dm.getWidth(), dm.getHeight(), DisplayMode.BIT_DEPTH_MULTI, dm.getRefreshRate());
    }

    public static GraphicsDevice getGraphicsDevice() {
        if (graphicsDevice == null)
            getDevices();
        return graphicsDevice;
    }
}
