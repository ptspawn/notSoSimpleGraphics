import org.herebdragons.Config;
import org.herebdragons.graphics.canvas.CanvasFactory;
import org.herebdragons.graphics.canvas.notSoSimpleCanvas;
import org.herebdragons.graphics.canvas.notSoSimpleRenderer;
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

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class BenchmarkTest implements Runnable{

    private static volatile boolean running;

    private static final int PASSES = 5;
    private static final int PASSES_TO_IGNORE = 2;
    private static final int NO_DELAYS_PER_YIELD = 5;


    private static Text text;
    private static Rectangle slidingRectangle;
    private static Rectangle rotatingRectangle;


    private static final FrameRate frameRate = new impFrameRate(-1);
    private static notSoSimpleKeyboardListener keyInput;
    private static notSoSimpleMouseListener mouseInput;

    private static notSoSimpleCanvas canvas;


    public static void main(String[] args) {

        //DisplayMode[] dl = SystemManager.getAvailableGraphicsMode();

        Map<String, Integer> results = new HashMap<String, Integer>();

        boolean fullScreen = false;

        Logger.setLogging(false);
        frameRate.setDebug(true);

        canvas = CanvasFactory.createCanvas(Config.LIBRARY_NAME +
                " - Benchmark test", new Dimension(2000, 1000), RendererType.JAVA_2D);

        canvas.setBgColor(Color.BLUE);

        Rectangle rect = new Rectangle(50, 50, 300, 300);

        canvas.addObject(rect);

        CanvasFactory.startCanvas(canvas);

        SwingUtilities.invokeLater(new BenchmarkTest());



        /*//Alternating FullScreen
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
        }*/
    }

    public void run(){
        long beforeTime, afterTime, timeDiff, sleepTime;
        long overSleepTime = 0L;
        int noDelays = 0;
        long excess = 0L;
        Graphics g;

        frameRate.initialize();
        gameStartTime = System.nanoTime();
        prevStatsTime = gameStartTime;
        beforeTime = gameStartTime;

        running = true;

        while(running) {
            gameUpdate();
            gameRender();   // render the game to a buffer
            paintScreen();  // draw the buffer on-screen

            frameRate.calculate();

            afterTime = System.nanoTime();
            timeDiff = afterTime - beforeTime;
            sleepTime = (frameRate.getCycleDuration() - timeDiff) - overSleepTime;

            if (sleepTime > 0) {   // some time left in this cycle
                try {
                    Thread.sleep(sleepTime/1000000L);  // nano -> ms
                }
                catch(InterruptedException ex){}
                overSleepTime = (System.nanoTime()- afterTime) - sleepTime;
            }
            else {    // sleepTime <= 0; the frame took longer than the period
                excess -= sleepTime;  // store excess time value
                overSleepTime = 0L;

                if (++noDelays >= NO_DELAYS_PER_YIELD) {
                    Thread.yield();   // give another thread a chance to run
                    noDelays = 0;
                }
            }

            beforeTime = System.nanoTime();

      /* If frame animation is taking too long, update the game state
         without rendering it, to get the updates/sec nearer to
         the required FPS. */
            int skips = 0;
            while((excess > period) && (skips < MAX_FRAME_SKIPS)) {
                excess -= period;
                gameUpdate();    // update state but don't render
                skips++;
            }
            framesSkipped += skips;

            storeStats();
        }

    }

    private static void runTest(boolean fullScreen, Map<String, Integer> results, DisplayMode dm) {

        Logger.log("Benchmark test started for\n" +
                "Renderer: " + CanvasFactory.getRenderer().name() + "\n" +
                "Display Mode: " + dm.toString() + "\n" +
                (fullScreen ? "Fullscreen" : "Windowed") +
                "\n");

        notSoSimpleCanvas canvas = createTestObjects(fullScreen);


        runBenchmark(results, dm);

        //close(gameThread, threadCanvas);
    }

    private static notSoSimpleCanvas createTestObjects(boolean fullScreen) {

        notSoSimpleCanvas canvas;

        if (fullScreen) {
            canvas = CanvasFactory.createCanvas(Config.LIBRARY_NAME, RendererType.JAVA_2D);
        } else {
            canvas = CanvasFactory.createCanvas(Config.LIBRARY_NAME, Config.DEFAULT_DIMENSION, RendererType.JAVA_2D);
        }

        keyInput = new notSoSimpleKeyboardListener();

        canvas.addKeyListener(keyInput);

        canvas.setObjectManager(new ObjectManager());

        text = new Text(new Dimension(100, 30), new Point(30, 30), Config.LIBRARY_NAME);
        slidingRectangle = new Rectangle(new Dimension(100, 100), new Point(100, 100));

        canvas.addObject(text);
        canvas.addObject(slidingRectangle);

        return canvas;

    }

    private static Thread launchGameThread(final notSoSimpleCanvas canvas) {
        Thread gameThread = new Thread(new Runnable() {
            public void run() {

                frameRate.initialize();

                Logger.log("Starting game loop");

                running = true;

                while (running) {

                    //Input Phase
                    getuserInput(canvas);

                    //update Cycle
                    slidingRectangle.move(1, 0);
                    if (slidingRectangle.getPosition().x > canvas.getDimension().width) {
                        slidingRectangle.moveTo(slidingRectangle.getDimension().width * -1, slidingRectangle.getPosition().y);
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

    private static void getuserInput(notSoSimpleCanvas canvas) {

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
