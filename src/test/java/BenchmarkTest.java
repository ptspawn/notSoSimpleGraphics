import org.herebdragons.Config;
import org.herebdragons.graphics.canvas.Canvas;
import org.herebdragons.graphics.canvas.CanvasFactory;
import org.herebdragons.graphics.enums.CanvasType;
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

    public static void main(String[] args) {

        DisplayMode[] dl = SystemManager.getAvailableGraphicsMode();

        Map<String, Integer> results = new HashMap<String, Integer>();

        boolean fullScreen = false;

        Logger.setLogging(false);

        for (int i = 0; i < CanvasType.values().length - 1; i++) {  //Minus one to skip openGL for now



        }


        for (DisplayMode dm : dl) {

            dm = SystemManager.convertDisplayMode(dm);

            final FrameRate frameRate = new FrameRate(-1);
            frameRate.setDebug(true);

            final Canvas canvas;

            if (fullScreen) {
                canvas = CanvasFactory.createCanvas(Config.LIBRARY_NAME);
            } else {
                canvas = CanvasFactory.createCanvas(Config.LIBRARY_NAME, Config.DEFAULT_DIMENSION, WindowBehaviour.EXIT_ON_CLOSE, false);
            }

            canvas.setObjectManager(new ObjectManager());

            canvas.addObject(new Text(new Dimension(100, 30), new Point(30, 30), Config.LIBRARY_NAME));
            canvas.addObject(new Rectangle(new Dimension(100, 100), new Point(100, 100)));

            Thread threadCanvas = new Thread(canvas);
            threadCanvas.run();

            //canvas.update();

            Thread gameThread = new Thread(new Runnable() {
                public void run() {

                    frameRate.initialize();

                    Logger.log("Starting game loop");

                    running = true;

                    while (running) {

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

            for (int i = 0; i < PASSES; i++) {

                try {
                    Thread.sleep(1000);
                    if (i >= PASSES_TO_IGNORE - 1) {
                        correctedValue = frameRate.getFramePerSecond();
                        results.put(dm.toString(),
                                (correctedValue + results.get(dm.toString())));
                        continue;
                    }

                    //puts the PASSES_TO_IGNORE in the map for reference
                    results.put(dm.toString(), frameRate.getFramePerSecond());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //Math
            results.put(dm.toString(), results.get(dm.toString()) / (PASSES - PASSES_TO_IGNORE));

            gameThread.interrupt();

        }

    }

}
