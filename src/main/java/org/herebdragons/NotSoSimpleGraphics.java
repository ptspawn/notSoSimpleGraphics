package org.herebdragons;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class NotSoSimpleGraphics extends JFrame {

    public NotSoSimpleGraphics() {

        initUI();
    }

    private void initUI() {

        //super.add(new Board());

        super.setSize(250, 200);

        super.setTitle("Application");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        NotSoSimpleGraphics ex = new NotSoSimpleGraphics();
        ex.setVisible(true);

        //EventQueue.invokeLater()


        //       () -> {
        //   notSoSimpleGraphics ex = new notSoSimpleGraphics();
        //   ex.setVisible(true);

    }
}
