package org.herebdragons.utils.FrameRate;

import org.herebdragons.utils.Logger;

public class FrameRate {


    private static long recordingInterval = 1000000000L;
    // private static long MAX_STATS_INTERVAL = 1000L;
    // record stats every 1 second (roughly)

    protected double framesPerSecond;
    protected double updatesPerSecond;
    protected long lastTime;
    protected long delta;
    protected long cycleDuration;
    protected long currentTime;
    protected long sleepTime;

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

    public final void incrementUpdate() {
        updateCount++;
    }


    public void calculate() {
        currentTime = System.nanoTime();
        delta = currentTime - lastTime;
        sleepTime = cycleDuration - delta;
        lastTime = currentTime;

        frameCount++;
        updateCount++;
        if (delta > recordingInterval) {
            delta -= recordingInterval;
            framesPerSecond = frameCount;
            updatesPerSecond = updateCount;
            frameCount = updateCount = 0;

            if (debug)
                System.out.println(getResult());
        }
    }

    public final long getRemainingInCyle() {
        if (!limited) {
            Logger.log("No FPS restrictions in place");
            return 0L;
        }

        Logger.log(sleepTime + " miliseconds free");
        return sleepTime;
    }


    public long getCycleDuration() {
        return cycleDuration;
    }

    public int getUpdatesPerSecond() {
        return (int)updatesPerSecond;
    }

    public String getResult() {
        return "notSoSimpleFrameRate { " + String.format("FPS %s", framesPerSecond)
                + " | " + String.format("UPS %s", updatesPerSecond) + " }";
    }

    public int getFramesPerSecond() {
        return (int)framesPerSecond;
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

