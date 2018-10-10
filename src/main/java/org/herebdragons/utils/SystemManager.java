package org.herebdragons.utils;

import org.herebdragons.graphics.canvas.Jwindow;
import org.herebdragons.graphics.canvas.notSoSimpleWindow;

import javax.swing.*;
import java.awt.*;

public class SystemManager {

    private static GraphicsDevice gd;
    private static GraphicsConfiguration gc;
    private static GraphicsEnvironment ge;
    private static DisplayMode dm;

    private static int screenWidth, screenHeight;

    private SystemManager(){}

    private static void getDevices() {
        if (ge == null)
            ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

        if (gd == null)
            gd = ge.getDefaultScreenDevice();

        if (gc == null)
            gc = gd.getDefaultConfiguration();

        if (dm == null)
            dm = gd.getDisplayMode();

        screenWidth = gc.getBounds().width;
        screenHeight = gc.getBounds().height;

    }

    public static String[] getFonts() {
        if (ge == null)
            getDevices();

        return ge.getAvailableFontFamilyNames();
    }

    public static GraphicsConfiguration getBestGraphicsConfiguration() {
        ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        gd = ge.getDefaultScreenDevice();
        return gd.getBestConfiguration(new GraphicsConfigTemplate() {
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

    public static void printGraphicsCfg(GraphicsConfiguration gc) {

        Logger.setLogging(true);
        Logger.log("Backbuffer accel: " + gc.getBufferCapabilities().getBackBufferCapabilities().isAccelerated());
        Logger.log("Backbuffer volat: " + gc.getBufferCapabilities().getBackBufferCapabilities().isTrueVolatile());
        Logger.log("Frontbuffer accel: " + gc.getBufferCapabilities().getFrontBufferCapabilities().isAccelerated());
        Logger.log("Frontbuffer volat: " + gc.getBufferCapabilities().getFrontBufferCapabilities().isTrueVolatile());
        Logger.log("MultiBuffer avail: " + gc.getBufferCapabilities().isMultiBufferAvailable());
        Logger.log("Page Flipping: " + gc.getBufferCapabilities().isPageFlipping());
        Logger.log("Requires Full Screen: " + gc.getBufferCapabilities().isFullScreenRequired());

        Logger.log("Img Volatile: " + gc.getImageCapabilities().isTrueVolatile());
        Logger.log("Img Accelerated: " + gc.getImageCapabilities().isAccelerated());

        Logger.log("Available Accel Mem: " + gc.getDevice().getAvailableAcceleratedMemory());
        Logger.setLogging(false);

    }

    public static void goFullScreen(notSoSimpleWindow window) throws IllegalStateException{
        if (gd==null)
            getDevices();

        if(!gd.isFullScreenSupported())
            throw new IllegalStateException("Full-screen exclusive mode not supported by the current Graphics Device");

        if (window instanceof Jwindow)
            gd.setFullScreenWindow(((Jwindow)window));

    }


    public static DisplayMode[] getAvailableGraphicsModes() {
        if (ge == null)
            getDevices();

        return ge.getDefaultScreenDevice().getDisplayModes();
    }

    public static DisplayMode getCurrentDisplayMode() {

        if (dm == null)
            getDevices();

        return new DisplayMode(
                dm.getWidth(), dm.getHeight(), dm.getBitDepth() == -1 ? DisplayMode.BIT_DEPTH_MULTI : dm.getBitDepth(), dm.getRefreshRate());
    }

    public static DisplayMode convertDisplayMode(DisplayMode dm) {

        return new DisplayMode(
                dm.getWidth(), dm.getHeight(), dm.getBitDepth() == -1 ? DisplayMode.BIT_DEPTH_MULTI : dm.getBitDepth(), dm.getRefreshRate());
    }

    public static GraphicsDevice getGd() {
        if (gd == null)
            getDevices();
        return gd;
    }

    public static Dimension getScreenDim() {
        if (screenWidth == 0 || screenHeight == 0)
            throw new IllegalStateException("Your graphics are not set up yet");
        return new Dimension(screenWidth, screenHeight);
    }

    public static GraphicsConfiguration getGc() {
        return gc;
    }

    public static GraphicsEnvironment getGe() {
        return ge;
    }

    public static DisplayMode getDm() {
        return dm;
    }
}
