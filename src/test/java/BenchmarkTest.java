import org.herebdragons.Config;
import org.herebdragons.graphics.canvas.Canvas;
import org.herebdragons.graphics.canvas.CanvasFactory;
import org.herebdragons.graphics.enums.RendererType;
import org.herebdragons.graphics.enums.WindowBehaviour;
import org.herebdragons.graphics.objects.ObjectManager;
import org.herebdragons.graphics.objects.Rectangle;
import org.herebdragons.graphics.objects.Text;
import org.herebdragons.utils.FrameRate;
import org.herebdragons.utils.Logger;
import org.herebdragons.utils.SystemManager;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class BenchmarkTest {

    private static volatile boolean running;
    private static final int PASSES = 5;
    private static final int PASSES_TO_IGNORE = 2;
    private static Text text;
    private static Rectangle rectangle;

    public static void main(String[] args) {

        DisplayMode[] dl = SystemManager.getAvailableGraphicsMode();

        Map<String, Integer> results = new HashMap<String, Integer>();

        boolean fullScreen = false;

        Logger.setLogging(true);

        for (int i = 0; i < RendererType.values().length - 2; i++) {  //Minus two to skip JavaFX and openGL for now

            CanvasFactory.setRenderer(RendererType.values()[i]);

        }


        for (DisplayMode dm : dl) {

            dm = SystemManager.convertDisplayMode(dm);

            Logger.log("Benchmark test started for\n" +
                    "Renderer: " + CanvasFactory.getRenderer().name() + "\n" +
                    "Display Mode: " + dm.toString());

            final FrameRate frameRate = new impFrameRate(-1);
            frameRate.setDebug(true);

            final Canvas canvas;

            if (fullScreen) {
                canvas = CanvasFactory.createCanvas(Config.LIBRARY_NAME);
            } else {
                canvas = CanvasFactory.createCanvas(Config.LIBRARY_NAME, Config.DEFAULT_DIMENSION, WindowBehaviour.EXIT_ON_CLOSE, false);
            }

            canvas.setObjectManager(new ObjectManager());

            text = new Text(new Dimension(100, 30), new Point(30, 30), Config.LIBRARY_NAME);
            rectangle = new Rectangle(new Dimension(100, 100), new Point(100, 100));

            canvas.addObject(text);
            canvas.addObject(rectangle);

            Thread threadCanvas = new Thread(canvas);
            threadCanvas.run();

            Thread gameThread = new Thread(new Runnable() {
                public void run() {

                    frameRate.initialize();

                    Logger.log("Starting game loop");

                    running = true;

                    while (running) {

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

            gameThread.start();

            boolean benchMark = true;
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

            //Math
            results.put(dm.toString(), results.get(dm.toString()) / (PASSES - PASSES_TO_IGNORE));

            try {
                gameThread.join();
            } catch (InterruptedException e) {
                Logger.err("Caught Interrupted Exception " + e.getMessage());
            }

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
