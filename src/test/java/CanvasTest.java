import org.herebdragons.config.Config;
import org.herebdragons.graphics.canvas.Canvas;
import org.herebdragons.graphics.canvas.CanvasFactory;
import org.herebdragons.graphics.enums.WindowBehaviour;
import org.herebdragons.graphics.objects.ObjectManager;
import org.herebdragons.graphics.objects.Rectangle;
import org.herebdragons.graphics.objects.Text;
import org.herebdragons.utils.FrameRate;
import org.herebdragons.utils.Logger;

import java.awt.*;

public class CanvasTest {

    private static volatile boolean running;

    public static void main(String[] args) {


        Logger.setLogging(false);

        final FrameRate frameRate = new FrameRate(60);
        frameRate.setDebug(false);

        final Canvas canvas;

        if (true) {
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


    }

}

