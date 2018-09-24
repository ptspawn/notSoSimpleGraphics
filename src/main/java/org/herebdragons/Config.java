package org.herebdragons;

import org.herebdragons.graphics.enums.CanvasType;

import java.awt.*;

public class Config {
    public static final int MIN_SIZE = 10;
    public static final int BUFFERING=3;
    public static final Color DEFAULT_FILL_COLOR = Color.RED;
    public static final Color DEFAULT_BG_COLOR = Color.BLACK;
    public static final Color DEFAULT_STROKE_COLOR = Color.BLUE;
    public static final int DEFAULT_STROKE_THINKNESS = 5;
    public static final String LIBRARY_NAME = "notSoSimpleGraphics";
    public static final Dimension DEFAULT_DIMENSION = new Dimension(1280, 800);
    public static final CanvasType DEFAULT_RENDERING = CanvasType.JAVA;
}
