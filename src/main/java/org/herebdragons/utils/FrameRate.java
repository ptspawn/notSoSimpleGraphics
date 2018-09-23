package org.herebdragons.utils;

public class FrameRate {

    private int framesPerSecond;
    private int updatesPerSecond;
    private long lastTime;
    private long delta;

    private int targetFPS;
    private int frameCount;
    private int updateCount;

    private boolean limited;

    public FrameRate() {

    }

    public FrameRate(int targetFPS) {
        this.targetFPS = targetFPS;
        limited = true;
    }

    public void initialize() {
        lastTime = System.nanoTime();
        framesPerSecond = 0;
        updatesPerSecond = 0;
    }

    public void incrementUpdate() {
        updateCount++;
    }


    public void calculate() {
        long current = System.nanoTime();
        delta += current - lastTime;
        lastTime = current;
        frameCount++;
        incrementUpdate();
        if (delta > 1e9) {
            delta -= 1e9;
            framesPerSecond = frameCount;
            updatesPerSecond = updateCount;
            frameCount = updateCount = 0;
        }
    }

    public long getRemainingInCyle(){
        return 0;
    }


    public int getUpdatesPerSecond() {
        return updatesPerSecond;
    }

    @Override
    public String toString() {
        return "notSoSimpleFrameRate { " + String.format("FPS %s", framesPerSecond)
                + " | " + String.format("UPS %s", updatesPerSecond) + " }";
    }

    public int getFramesPerSecond() {
        return framesPerSecond;
    }
}
