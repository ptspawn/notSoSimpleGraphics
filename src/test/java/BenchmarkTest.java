import org.herebdragons.Config;
import org.herebdragons.graphics.canvas.Canvas;
import org.herebdragons.graphics.canvas.CanvasFactory;
import org.herebdragons.graphics.enums.RendererType;
import org.herebdragons.graphics.enums.WindowBehaviour;
import org.herebdragons.graphics.objects.ObjectManager;
import org.herebdragons.graphics.objects.Rectangle;
import org.herebdragons.graphics.objects.Text;
import org.herebdragons.input.notSoSimpleKeyboardListener;
import org.herebdragons.input.notSoSimpleMouseListener;
import org.herebdragons.utils.FrameRate;
import org.herebdragons.utils.Logger;
import org.herebdragons.utils.SystemManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class BenchmarkTest {

    private static volatile boolean running;
    private static final int PASSES = 5;
    private static final int PASSES_TO_IGNORE = 2;
    private static Text text;
    private static Rectangle rectangle;
    private static final FrameRate frameRate = new impFrameRate(-1);
    private static notSoSimpleKeyboardListener keyInput;
    private static notSoSimpleMouseListener mouseInput;


    public static void main(String[] args) {

        DisplayMode[] dl = SystemManager.getAvailableGraphicsMode();

        Map<String, Integer> results = new HashMap<String, Integer>();

        boolean fullScreen = false;

        Logger.setLogging(false);
        frameRate.setDebug(true);

        //Alternating FullScreen
        for (int j = 0; j < 2; j++) {
            switch (j) {
                case 0:
                    fullScreen = false;
                    break;
                case 1:
                    fullScreen = true;
                    break;
            }

            //Alternating DisplayModes for when in fullscreen mode

            if (fullScreen) {
                for (int i = 0; i < RendererType.values().length - 2; i++) {  //Minus two to skip JavaFX and openGL for now

                    CanvasFactory.setRenderer(RendererType.values()[i]);

                    if (!fullScreen) {

                        for (DisplayMode dm : dl) {

                            dm = SystemManager.convertDisplayMode(dm);

                            runTest(fullScreen, results, dm);

                        }

                    } else {
                        runTest(fullScreen, results, SystemManager.getCurrentDisplayMode());
                    }
                }
            }
        }
    }

    private static void runTest(boolean fullScreen, Map<String, Integer> results, DisplayMode dm) {

        Logger.log("Benchmark test started for\n" +
                "Renderer: " + CanvasFactory.getRenderer().name() + "\n" +
                "Display Mode: " + dm.toString() + "\n" +
                (fullScreen ? "Fullscreen" : "Windowed") +
                "\n");

        Canvas canvas = createTestObjects(fullScreen);

        Thread threadCanvas = new Thread(canvas);
        threadCanvas.run();

        Thread gameThread = launchGameThread(canvas);

        gameThread.start();

        runBenchmark(results, dm);

        close(gameThread, threadCanvas);
    }

    private static Canvas createTestObjects(boolean fullScreen) {

        Canvas canvas;

        if (fullScreen) {
            canvas = CanvasFactory.createCanvas(Config.LIBRARY_NAME);
        } else {
            canvas = CanvasFactory.createCanvas(Config.LIBRARY_NAME, Config.DEFAULT_DIMENSION, WindowBehaviour.EXIT_ON_CLOSE, false);
        }

        keyInput=new notSoSimpleKeyboardListener();

        canvas.addKeyListener(keyInput);

        canvas.setObjectManager(new ObjectManager());

        text = new Text(new Dimension(100, 30), new Point(30, 30), Config.LIBRARY_NAME);
        rectangle = new Rectangle(new Dimension(100, 100), new Point(100, 100));

        canvas.addObject(text);
        canvas.addObject(rectangle);

        return canvas;

    }

    private static Thread launchGameThread(final Canvas canvas) {
        Thread gameThread = new Thread(new Runnable() {
            public void run() {

                frameRate.initialize();

                Logger.log("Starting game loop");

                running = true;

                while (running) {

                    //Input Phase
                    getuserInput(canvas);

                    //update Cycle
                    rectangle.move(1, 0);
                    if (rectangle.getPosition().x > canvas.getDimension().width) {
                        rectangle.moveTo(rectangle.getDimension().width * -1, rectangle.getPosition().y);
                    }

                    canvas.update();
                    frameRate.calculate();

                    try {
                        Thread.sleep(frameRate.getRemainingInCyle());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

        return gameThread;
    }

    private static void getuserInput(Canvas canvas) {

        if (keyInput != null) {

            keyInput.poll();

            if (keyInput.keyDown(KeyEvent.VK_ESCAPE)) {
                canvas.close();
            }
        }

        if (mouseInput != null) {
            //
        }

    }

    private static void runBenchmark(Map<String, Integer> results, DisplayMode dm) {
        int correctedValue;

        for (int i = 0; i < PASSES; i++) {

            try {
                Thread.sleep(1000);
                if (i >= PASSES_TO_IGNORE - 1) {
                    correctedValue = frameRate.getFramesPerSecond();
                    results.put(dm.toString(),
                            (correctedValue + results.get(dm.toString())));
                    continue;
                }

                //puts the PASSES_TO_IGNORE in the map for reference
                results.put(dm.toString(), frameRate.getFramesPerSecond());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //Averaging
        results.put(dm.toString(), results.get(dm.toString()) / (PASSES - PASSES_TO_IGNORE));
    }

    private static void close(Thread gameThread, Thread threadCanvas) {
        try {
            gameThread.join();
            threadCanvas.join();
        } catch (InterruptedException e) {
            Logger.err("Caught Interrupted Exception " + e.getMessage());
        }
    }

    private static void tick(int fps) {
        text.setText("FPS: " + fps);
    }

    private static class impFrameRate extends FrameRate {
        private impFrameRate(int targetFPS) {
            super(targetFPS);
        }

        @Override
        public void calculate() {
            currentTime = System.nanoTime();
            delta += currentTime - lastTime;
            lastTime = currentTime;
            frameCount++;
            incrementUpdate();
            if (delta > 1e9) {
                delta -= 1e9;
                framesPerSecond = frameCount;
                updatesPerSecond = updateCount;
                frameCount = updateCount = 0;

                if (debug) {
                    System.out.println(getResult());
                    tick(getFramesPerSecond());
                }
            }
        }
    }


}
