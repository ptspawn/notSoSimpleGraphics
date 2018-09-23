package org.herebdragons.utils;

public class FrameRate {

    private int framesPerSecond;
    private int updatesPerSecond;
    private long lastTime;
    private long delta;
    private long cycleDuration;
    private long currentTime;


    private int frameCount;
    private int updateCount;


    private boolean limited;

   public FrameRate(int targetFPS) {

       if(targetFPS<=0){
           limited=false;
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
        }
    }

    public long getRemainingInCyle() {
        if (!limited)
            return 0;

        return (lastTime + cycleDuration - currentTime) / 1000000;
    }


    public int getUpdatesPerSecond() {
        return updatesPerSecond;
    }


    public String getResult() {
        return "notSoSimpleFrameRate { " + String.format("FPS %s", framesPerSecond)
                + " | " + String.format("UPS %s", updatesPerSecond) + " }";
    }

    public void setTargetFPS(int targetFPS) {
        cycleDuration = 1000000000 / targetFPS;
        limited = true;
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

