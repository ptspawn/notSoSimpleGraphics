import org.herebdragons.Config;
import org.herebdragons.graphics.canvas.Canvas;
import org.herebdragons.graphics.canvas.CanvasFactory;
import org.herebdragons.graphics.enums.CanvasType;
import org.herebdragons.graphics.enums.WindowBehaviour;
import org.herebdragons.graphics.objects.ObjectManager;
import org.herebdragons.graphics.objects.Rectangle;
import org.herebdragons.graphics.objects.Text;
import org.herebdragons.graphics.objects.notSoSimpleObject;
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
    private static notSoSimpleObject text;
    private static notSoSimpleObject square;

    public static void main(String[] args) {

        DisplayMode[] dl = SystemManager.getAvailableGraphicsMode();

        Map<String, Integer> results = new HashMap<String, Integer>();

        boolean fullScreen = false;

        Logger.setLogging(true);

        for (int i = 0; i < 2; i++) {
            switch (i) {
                case 0:
                    fullScreen = false;
                    break;
                case 1:
                    fullScreen = true;
                    break;
            }

            for (int e = 0; e < CanvasType.values().length - 1; e++) {  //Minus one to skip openGL for now

                CanvasFactory.setRenderingMethod(CanvasType.values()[e]);

                for (DisplayMode dm : dl) {

                    dm = SystemManager.convertDisplayMode(dm);

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
                    square = new Rectangle(new Dimension(100, 100), new Point(100, 100));

                    canvas.addObject(text);
                    canvas.addObject(square);

                    Thread gameThread = new Thread(new Runnable() {
                        public void run() {

                            frameRate.initialize();
                            canvas.run();

                            Logger.log("Starting game loop");

                            running = true;

                            while (running) {

                                //update
                                square.move(1, 0);

                                if (square.getPosition().x >= canvas.getDimension().width) {
                                    square.moveTo(-square.getDimension().width, square.getPosition().y);
                                }

                                //render
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

                    boolean benckMark = true;
                    int correctedValue;

                    for (int j = 0; j < PASSES; j++) {

                        try {
                            Thread.sleep(1000);
                            if (j >= PASSES_TO_IGNORE - 1) {
                                correctedValue = frameRate.getFramePerSecond();
                                results.put(dm.toString(),
                                        (correctedValue + results.get(dm.toString())));
                                continue;
                            }

                            //puts the PASSES_TO_IGNORE in the map for reference
                            results.put(dm.toString(), frameRate.getFramePerSecond());

                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }

                    //Math
                    results.put(dm.toString(), results.get(dm.toString()) / (PASSES - PASSES_TO_IGNORE));


                    try {
                        gameThread.join();
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                }
            }
        }

    }

    private static void tick(int fps) {

        ((Text)text).setText("FPS: " + fps);

    }

    private static class impFrameRate extends FrameRate {

        private impFrameRate(int frameCap) {
            super(frameCap);

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
                    BenchmarkTest.tick(getFramePerSecond());
                }
            }

        }
    }


}
