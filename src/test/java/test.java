import org.herebdragons.graphics.canvas.CanvasFactory;
import org.herebdragons.graphics.canvas.notSoSimpleCanvas;
import org.herebdragons.graphics.enums.RendererType;
import org.herebdragons.graphics.enums.ThreadBehaviour;
import org.herebdragons.graphics.objects.Rectangle;
import org.herebdragons.utils.Logger;
import org.herebdragons.utils.SystemManager;

import java.awt.*;

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
public class test {

    public static void main(String[] args) {

        Logger.setLogging(true);

        CanvasFactory.setDefaultThreading(ThreadBehaviour.AUTO);

        notSoSimpleCanvas canvas = CanvasFactory.createCanvas("teste", new Dimension(2000, 1000),
                RendererType.JAVA_2D);

        //canvas.setDecorated(true);

        Rectangle rect = new Rectangle(400, 300, 200, 200);
        rect.setFill(Color.RED);

        canvas.addObject(rect);

        CanvasFactory.startCanvas(canvas);


        /*while(!canvas.isReady()) {

            System.out.println("wainting for canvas to be ready");
            //canvas.update();
        }*/
        /*try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        canvas.setVisible(true);

        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //canvas.update();

*/

        System.out.println("this is the end");

    }
}
