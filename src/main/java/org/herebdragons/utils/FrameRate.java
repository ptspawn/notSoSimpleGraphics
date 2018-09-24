package org.herebdragons.utils;

public class FrameRate {

    protected int framesPerSecond;
    protected int updatesPerSecond;
    protected long lastTime;
    protected long delta;
    protected long cycleDuration;
    protected long currentTime;


    protected int frameCount;
    protected int updateCount;

    protected boolean debug;


    protected boolean limited;

    public FrameRate(int targetFPS) {

        if (targetFPS <= 0) {
            limited = false;
            return;
        }

        cycleDuration = 1000000000 / targetFPS;
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
        currentTime = System.nanoTime();
        delta += currentTime - lastTime;
        lastTime = currentTime;
        frameCount++;
        incrementUpdate();
        if (delta > 1e9) {
            delta -= 1e9;
            framesPerSecond = frameCount;
            updatesPerSecond = updateCount;
            frameCount = updateCount = 0;

            if (debug)
                System.out.println(getResult());
        }
    }


    public long getRemainingInCyle() {
        if (!limited) {
            Logger.log("No FPS restrictions in place");
            return 0;
        }

        long remaining = (lastTime + cycleDuration - currentTime) / 1000000;

        Logger.log(remaining + " miliseconds free");
        return remaining;
    }


    public int getUpdatesPerSecond() {
        return updatesPerSecond;
    }


    public String getResult() {
        return "notSoSimpleFrameRate { " + String.format("FPS %s", framesPerSecond)
                + " | " + String.format("UPS %s", updatesPerSecond) + " }";
    }

    public int getFramesPerSecond(){
        return framesPerSecond;
    }

    public void setTargetFPS(int targetFPS) {
        cycleDuration = 1000000000 / targetFPS;
        limited = true;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public boolean isLimited() {
        return limited;
    }

    public void setLimited(boolean limited) {
        this.limited = limited;
    }

    @Override
    public String toString() {
        return "FrameRate{" +
                "framesPerSecond=" + framesPerSecond +
                ", updatesPerSecond=" + updatesPerSecond +
                ", lastTime=" + lastTime +
                ", delta=" + delta +
                ", cycleDuration=" + cycleDuration +
                ", currentTime=" + currentTime +
                ", frameCount=" + frameCount +
                ", updateCount=" + updateCount +
                ", limited=" + limited +
                '}';
    }
}

