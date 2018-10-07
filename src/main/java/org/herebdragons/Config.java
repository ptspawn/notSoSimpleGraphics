package org.herebdragons;

import org.herebdragons.graphics.enums.RendererType;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.Color;

import org.herebdragons.graphics.enums.ThreadBehaviour;
import org.herebdragons.graphics.enums.WindowBehaviour;
import org.herebdragons.graphics.objects.*;
import org.herebdragons.graphics.canvas.*;


/**
 * <center><h1><strong>Config class</strong></h1></center><br>
 *
 * <center>@author Tiago Venceslau &lt;ptspawn@gmail.com&gt;</center><br>
 *
 * <center>https://github.com/ptspawn/notSoSimpleGraphics}</center><br>
 *
 * <center>created by tvenceslau in set/2018</center>
 *
 * <center>under the Apache 2.0 license as stated in @see <a href="https://www.apache.org/licenses/LICENSE-2.0.txt">LICENSE</a></center>
 *
 * <h2>Description:</h2><br>
 * <p>A Utility class containing all the configurations required</p>
 */
public class Config {
    private Config() {
    }

    public static final boolean DEFAULT_WINDOW_DECORATION = true;

    public static final WindowBehaviour DEFAULT_BEHAVIOUR_ON_EXIT = WindowBehaviour.EXIT_ON_CLOSE;

    /**
     * The {@link int} represents the minimum possible size of the window (in square form)
     *
     * @see
     * @see
     */
    public static final ThreadBehaviour DEFAULT_THREAD_BEHAVIOUR = ThreadBehaviour.AUTO;

    /**
     * The {@link int} represents the minimum possible size of the window (in square form)
     *
     * @see
     * @see
     */
    public static final int DEFAULT_THREADS = 2;   //UPDATE JAVADOCS

    /**
     * The {@link int} represents the minimum possible size of the window (in square form)
     *
     * @see AbstractCanvas#setDimension(Dimension)
     * @see Dimension
     */
    public static final int MIN_SIZE = 10;

    /**
     * The {@link int} represents how many buffers should be user in the @link BufferStrategy
     *
     * @see BufferStrategy#BufferStrategy()
     * @see notSoSimpleRenderer#render
     */
    public static final int BUFFERING = 2;

    /**
     * The {@link Color} represents the default fill color for {@link Fillable}.
     *
     * @see Fillable#fill(Color)
     */
    public static final Color DEFAULT_FILL_COLOR = Color.RED;

    /**
     * The {@link Color} represents the default Fill color for the {@link Fillable}.
     *
     * @see Fillable#fill(Color)
     */
    public static final Color DEFAULT_BG_COLOR = Color.YELLOW;

    /**
     * The {@link Color} represents the default stroke color for the {@link Strokable}.
     *
     * @see Strokable#stroke(Color, int)
     */
    public static final Color DEFAULT_STROKE_COLOR = Color.BLUE;

    /**
     * The {@link int} represents the default stroke thickness for the {@link Strokable}.
     *
     * @see Strokable#stroke(Color, int)
     */
    public static final int DEFAULT_STROKE_THINKNESS = 5;

    public static final BasicStroke DEFAULT_STROKE_TYPE = new BasicStroke(DEFAULT_STROKE_THINKNESS, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND);
    /**
     * The {@link String} is self explanatory
     */
    public static final String LIBRARY_NAME = "notSoSimpleGraphics";

    /**
     * The {@link int} represents the default dimension of the window
     *
     * @see AbstractCanvas#setDimension(Dimension)
     * @see Dimension
     */
    public static Dimension DEFAULT_DIMENSION = new Dimension(1280, 800);

    /**
     * The {@link RendererType} represents the default {@link RendererType}
     *
     * @see RendererType
     * @see notSoSimpleRenderer
     */
    public static RendererType DEFAULT_RENDERER = RendererType.JAVA_2D;
}
