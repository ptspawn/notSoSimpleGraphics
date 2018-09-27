package org.herebdragons.graphics.canvas;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;

/**
 * <center><h1><strong>notSoSimpleWindow interface</strong></h1></center><br>
 *
 * <center>@author Tiago Venceslau &lt;ptspawn@gmail.com&gt;</center><br>
 *
 * <center>https://github.com/ptspawn/notSoSimpleGraphics}</center><br>
 *
 * <center>created by tvenceslau in {Sep}/{2018}</center>
 *
 * <center>under the Apache 2.0 license as stated in @see <a href="https://www.apache.org/licenses/LICENSE-2.0.txt">LICENSE</a></center>
 *
 * <h2>Description:</h2><br>
 * <p>Represents the <code>notSoSimpleWindow interface</code><br>.
 */

interface notSoSimpleWindow {

    String getName();

    void setName(String s);

    void addKeyListener(KeyListener keyListener);

    boolean isVisible();

    boolean isDoubleBuffered();

    void setLocationRelativeTo(Component component);

    Point getLocation();

    void setIconImage(Image image);

    BufferStrategy getBufferStrategy();

    void setSize(Dimension dimension);

    void setSize(int width, int height);

    Dimension getSize(Dimension dimension);

    Point getLocation(Point point);

    void setLocation(int i, int i1);

    void setLocation(Point point);

    Dimension getSize();

    void setVisible(boolean b);

    void addWindowListener(WindowListener windowListener);

    void createBufferStrategy(int i);

    void addMouseListener(MouseListener mouseListener);

    void addMouseMotionListener(MouseMotionListener mouseMotionListener);

    void addMouseWheelListener(MouseWheelListener mouseWheelListener);

    void requestFocus();

    String getTitle();

    void setTitle(String s);

    Image getIconImage();

    boolean isResizable();

    void setResizable(boolean b);

    void setUndecorated(boolean b);

    boolean isUndecorated();

    void setBackground(Color color);
}


