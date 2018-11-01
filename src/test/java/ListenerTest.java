import org.herebdragons.graphics.canvas.*;
import org.herebdragons.graphics.enums.RendererType;
import org.herebdragons.input.notSoSimpleKeyboardListener;
import org.herebdragons.utils.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class ListenerTest implements notSoSimpleRunnable {

    private static JFrame window;
    private static notSoSimpleKeyboardListener keyInput;
    private static notSoSimpleCanvas canvas;
    private static notSoSimpleWindow jwindow;

    public static void main(String[] args) {

//        jwindow=new Jwindow(SystemManager.getGc());
//        keyInput=new notSoSimpleKeyboardListener();
//
//        jwindow.setSize(1300,700);
//        jwindow.setVisible(true);
//
//        jwindow.addKeyListener(keyInput);

        keyInput=new notSoSimpleKeyboardListener();

        canvas=CanvasFactory.createCanvas("Listener test", RendererType.JAVA_2D);

        canvas.addKeyListener(keyInput);

        ListenerTest test = new ListenerTest();

        CanvasFactory.startCanvas(canvas, test);

    }

    @Override
    public void run() {

        Logger.err("Entering run");
        while (true){

            keyInput.poll();

            //canvas.update();

            if (keyInput.keyDown(KeyEvent.VK_ESCAPE)){
                System.exit(0);
            }

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
