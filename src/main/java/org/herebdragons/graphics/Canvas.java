package org.herebdragons.graphics;

import org.herebdragons.graphics.enums.WindowBehaviour;

import javax.swing.*;

import java.awt.EventQueue;
import javax.swing.JFrame;


public class Canvas extends JFrame {

    private WindowBehaviour behaviourOnExit;

    private Canvas() {
   }

    public static Canvas init(String title, WindowBehaviour behaviourOnExit) {

        //super.add(new Board());

        final Canvas canvas = new Canvas();

        canvas.setSize(500, 500);

        canvas.setTitle(title);
        canvas.setDefaultCloseOperation(behaviourOnExit.getIndex());
        canvas.setLocationRelativeTo(null);

        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                canvas.setVisible(true);
            }
        });

        return canvas;
    }


}
