package Benchmark;

import org.herebdragons.Config;
import org.herebdragons.graphics.canvas.CanvasFactory;
import org.herebdragons.graphics.canvas.SystemManager;
import org.herebdragons.graphics.canvas.notSoSimpleCanvas;
import org.herebdragons.graphics.canvas.notSoSimpleRunnable;
import org.herebdragons.graphics.enums.RendererType;
import org.herebdragons.graphics.objects.Rectangle;
import org.herebdragons.graphics.objects.Text;
import org.herebdragons.input.notSoSimpleKeyboardListener;
import org.herebdragons.input.notSoSimpleMouseListener;
import org.herebdragons.utils.FrameRate.FrameRate;
import org.herebdragons.utils.Logger;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BenchmarkTest implements notSoSimpleRunnable, Updatable {

    private static volatile boolean running;

    private static final int PASSES = 5;
    private static final int PASSES_TO_IGNORE = 2;
    private static final int NO_DELAYS_PER_YIELD = 5;
    private static final int MAX_FRAME_SKIPS = 5;
    private static int framesSkipped;

    private static Text text;
    private static Rectangle rotatingRectangle;
    private static Rectangle horzSlidingRect;
    private static Rectangle vertSlidingRect;


    private static ImpFrameRate frameRate;
    private static notSoSimpleKeyboardListener keyInput;
    private static notSoSimpleMouseListener mouseInput;

    private static notSoSimpleCanvas canvas;

    private static DisplayMode currentDisplayMode;
    private static final Map<String, Integer> overallResults = new HashMap<String, Integer>();
    private static final Map<Integer, Integer> results = new HashMap<>();

    private final static List<Rectangle> horizontalList = new ArrayList<Rectangle>();
    private final static List<Rectangle> verticalList = new ArrayList<Rectangle>();


    private static final TestMode MODE = TestMode.NORMAL;

    private enum TestMode {
        NORMAL(100),
        HARDCORE(100);

        private int NUM_OBJECTS;

        TestMode(int halfObjects) {
            NUM_OBJECTS = halfObjects;
        }

        public int getNumObjects() {
            return NUM_OBJECTS;
        }
    }

    private BenchmarkTest() {

        DisplayMode[] dl = SystemManager.getAvailableGraphicsModes();

        boolean fullScreen = true;

        Logger.setLogging(false);
        Logger.setDebugging(false);

        frameRate = new ImpFrameRate(60,this);
        frameRate.setDebug(true);

        canvas = createTestObjects(fullScreen);

        CanvasFactory.startCanvas(canvas, this);

    }

    public static void main(String[] args) {

        new BenchmarkTest();

    }

    public void run() {
        frameRate.initialize();

        Logger.log("Starting game loop");

        running = true;

        while (running) {

            //Input Phase
            getuserInput(canvas);

            //update Cycle
            update();

            //Render Cycle
            canvas.update();

            frameRate.calculate();

            try {
                Thread.sleep(frameRate.getRemainingInCyle());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //frameRate.sleep();

        }

        System.out.println(frameRate.getResult());

        canvas.close();

    }

    public void update() {
        for (int i = 0; i < MODE.getNumObjects(); i++) {

            (horzSlidingRect = horizontalList.get(i)).move(1, 0);
            horzSlidingRect.rotate( (2 * Math.PI) / 360);
            if (horzSlidingRect.getPosition().getX() >= canvas.getDimension().width) {
                horzSlidingRect.moveTo(0 - horzSlidingRect.getDimension().height, horzSlidingRect.getPosition().getY());
            }

            (vertSlidingRect = verticalList.get(i)).move(0, -1);
            vertSlidingRect.rotate(-(2 * Math.PI) / 360);
            if (vertSlidingRect.getPosition().getY() + vertSlidingRect.getDimension().height <= 0) {
                vertSlidingRect.moveTo(vertSlidingRect.getPosition().getX(), canvas.getDimension().height);
            }

        }

        rotatingRectangle.rotate((2 * Math.PI) / 360);

    }

    private void runTest(boolean fullScreen, Map<String, Integer> results, DisplayMode dm) {

        Logger.log("Benchmark test started for\n" +
                "Renderer: " + CanvasFactory.getRenderer().name() + "\n" +
                "Display Mode: " + dm.toString() + "\n" +
                (fullScreen ? "Fullscreen" : "Windowed") +
                "\n");

        notSoSimpleCanvas canvas = createTestObjects(fullScreen);


        //runBenchmark(results, dm);

        //close(gameThread, threadCanvas);
    }

    private notSoSimpleCanvas createTestObjects(boolean fullScreen) {

        notSoSimpleCanvas canvas;


        if (fullScreen) {
            canvas = CanvasFactory.createCanvas(Config.LIBRARY_NAME, RendererType.JAVA_2D);
        } else {
            canvas = CanvasFactory.createCanvas(Config.LIBRARY_NAME, Config.DEFAULT_DIMENSION, RendererType.JAVA_2D);
        }

        keyInput = new notSoSimpleKeyboardListener();

        canvas.addKeyListener(keyInput);


        text = new Text(new Dimension(100, 30), new Point(30, 30), Config.LIBRARY_NAME);

        rotatingRectangle = new Rectangle(new Dimension(300, 300),
                new Point(canvas.getDimension().width / 2 - 150, canvas.getDimension().height / 2 - 150));
        GradientPaint redtowhite = new GradientPaint(500, 0, Color.RED, 1280 - 500, 0, Color.WHITE);
        rotatingRectangle.setFill(redtowhite);
        float[] dash2 = {3f, 4f, 10f};
        BasicStroke stroke2 = new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 20f, dash2, 10);
        rotatingRectangle.setStroke(Color.black, stroke2);
        canvas.addObject(rotatingRectangle);

        Rectangle randomRectangle;
        float[] dash;
        BasicStroke stroke;

        for (int i = 0; i < MODE.getNumObjects(); i++) {

            randomRectangle = new Rectangle(new Dimension(100, 100),
                    new Point((int) (Math.random() * canvas.getDimension().width), (int) (Math.random() * canvas.getDimension().height)));

            if (MODE == TestMode.HARDCORE) {

                randomRectangle.setFill(new Color((int) (Math.random() * 255), (int) (Math.random() * 255),
                        (int) (Math.random() * 255), (int) (Math.random() * 255) / 2));

                dash = new float[]{(int) (Math.random() * 5) + 1, (int) (Math.random() * 5) + 1};

                stroke = new BasicStroke((int) (Math.random() * 5), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                        (float) (Math.random() * 15), dash, (int) (Math.random() * 100));

                randomRectangle.setStroke(new Color((int) (Math.random() * 255), (int) (Math.random() * 255),
                        (int) (Math.random() * 255), (int) (Math.random() * 255) / 2), stroke);
            } else {
                randomRectangle.setFill(new Color((int) (Math.random() * 255), (int) (Math.random() * 255),
                        (int) (Math.random() * 255), 255));//(int) (Math.random() * 255) / 2));
            }

            canvas.addObject(randomRectangle);
            horizontalList.add(randomRectangle);

            randomRectangle = new Rectangle(new Dimension(100, 100),
                    new Point((int) (Math.random() * canvas.getDimension().width), (int) (Math.random() * canvas.getDimension().height)));

            if (MODE == TestMode.HARDCORE) {

                randomRectangle.setFill(new Color((int) (Math.random() * 255), (int) (Math.random() * 255),
                        (int) (Math.random() * 255), (int) (Math.random() * 255) / 2));

                dash = new float[]{(int) (Math.random() * 5) + 1, (int) (Math.random() * 5) + 1};

                stroke = new BasicStroke((int) (Math.random() * 5), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                        (float) (Math.random() * 15), dash, (int) (Math.random() * 100));

                randomRectangle.setStroke(new Color((int) (Math.random() * 255), (int) (Math.random() * 255),
                        (int) (Math.random() * 255), (int) (Math.random() * 255) / 2), stroke);
            } else {
                randomRectangle.setFill(new Color((int) (Math.random() * 255), (int) (Math.random() * 255),
                        (int) (Math.random() * 255), 255));//(int) (Math.random() * 255) / 2));
            }

            canvas.addObject(randomRectangle);
            verticalList.add(randomRectangle);

        }

        canvas.addObject(text);

        return canvas;

    }

    private static void getuserInput(notSoSimpleCanvas canvas) {

        if (keyInput != null) {

            keyInput.poll();

            if (keyInput.keyDown(KeyEvent.VK_ESCAPE)) {
                running = false;
            }
        }

        if (mouseInput != null) {
            //
        }

    }

//    private void runBenchmark(Map<String, Integer> results, DisplayMode dm) {
//        int correctedValue;
//
//        for (int i = 0; i < PASSES; i++) {
//
//            try {
//                Thread.sleep(1000);
//                if (i >= PASSES_TO_IGNORE - 1) {
//                    correctedValue = frameRate.getFramesPerSecond();
//                    results.put(dm.toString(),
//                            (correctedValue + results.get(dm.toString())));
//                    continue;
//                }
//
//                //puts the PASSES_TO_IGNORE in the map for reference
//                results.put(dm.toString(), frameRate.getFramesPerSecond());
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//        //Averaging
//        results.put(dm.toString(), results.get(dm.toString()) / (PASSES - PASSES_TO_IGNORE));
//    }


    private void tick(int fps) {
        text.setText(frameRate.getResult());
    }

    private class ImpFrameRate extends FrameRate {

        private ImpFrameRate(int targetFPS, Updatable callBack) {
            super(targetFPS);
        }

        @Override
        public void calculate() {
            currentTime = System.nanoTime();
            delta = currentTime - lastTime;
            lastTime = currentTime;

            deltaMeasurements += delta;

            System.out.println("Cycle duration: " + cycleDuration);

            sleepTime = cycleDuration - delta + roundingError;
            roundingError = sleepTime % NANO_TO_MILI;

            sleepTime = sleepTime / NANO_TO_MILI;                   //nano to mili

            System.out.println("sleepTime: " + sleepTime);

            if (sleepTime < 0) {
                sleepTime = 0;
            }

            frameCount++;
            updateCount++;
            if (deltaMeasurements > recordingInterval) {
                deltaMeasurements -= recordingInterval;
                framesPerSecond = frameCount;
                updatesPerSecond = updateCount;
                frameCount = updateCount = 0;


                if (debug) {
                    tick(0);
                }
            }

        }
    }
}



