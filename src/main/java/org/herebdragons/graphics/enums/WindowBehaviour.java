package org.herebdragons.graphics.enums;

public enum WindowBehaviour {
    DO_NOTHING_ON_CLOSE(0),
    HIDE_ON_CLOSE(1),
    DISPOSE_ON_CLOSE(2),
    EXIT_ON_CLOSE(3);

    private int behaviourIndex; //mimics the ones in javax.swing.WindowConstants

    WindowBehaviour(int behaviourIndex) {
        this.behaviourIndex = behaviourIndex;
    }

    public int getIndex() {
        return behaviourIndex;
    }
}
