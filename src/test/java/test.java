import org.herebdragons.graphics.canvas.CanvasFactory;
import org.herebdragons.graphics.canvas.notSoSimpleCanvas;
import org.herebdragons.graphics.canvas.notSoSimpleRunnable;
import org.herebdragons.graphics.enums.RendererType;
import org.herebdragons.graphics.enums.ThreadBehaviour;
import org.herebdragons.graphics.objects.Rectangle;
import org.herebdragons.input.notSoSimpleKeyboardListener;
import org.herebdragons.utils.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * <center><h1><strong>test class</strong></h1></center><br>
 *
 * <center>@author Tiago Venceslau &lt;ptspawn@gmail.com&gt;</center><br>
 *
 * <center>https://github.com/ptspawn/notSoSimpleGraphics}</center><br>
 *
 * <center>created by tvenceslau in Sep/2018</center>
 *
 * <center>under the Apache 2.0 license as stated in @see <a href="https://www.apache.org/licenses/LICENSE-2.0.txt">LICENSE</a></center>
 *
 * <h2>Description:</h2><br>
 * <p>Represents the <code>test class</code><br>.
 */
public class test implements notSoSimpleRunnable {

    private static notSoSimpleCanvas canvas;
    private static notSoSimpleKeyboardListener keyInput;

    public static void main(String[] args) {

        Logger.setLogging(true);

        CanvasFactory.setDefaultThreading(ThreadBehaviour.AUTO);

        boolean fullScreen = false;

        if (fullScreen) {
            canvas = CanvasFactory.createCanvas("teste", RendererType.JAVA_2D);
        } else {
            canvas = CanvasFactory.createCanvas("teste", new Dimension(1280, 720),
                    RendererType.JAVA_2D);
        }

        keyInput = new notSoSimpleKeyboardListener();

        canvas.addKeyListener(keyInput);

        Rectangle rect = new Rectangle(50, 50, 720 - 150, 1280 - 150);

        rect.setFill(Color.RED);

        canvas.addObject(rect);

        CanvasFactory.startCanvas(canvas, new test());

    }


    @Override
    public void run() {

        Rectangle rectangle = new Rectangle(200, 200, 100, 100);
        rectangle.setStroke(Color.BLACK, new BasicStroke(2));
        rectangle.setFill(Color.BLUE);

        canvas.addObject(rectangle);

        canvas.update();


        while (true) {
            keyInput.poll();

            canvas.update();

            if (keyInput.keyDown(KeyEvent.VK_ESCAPE))
                System.exit(0);


            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
