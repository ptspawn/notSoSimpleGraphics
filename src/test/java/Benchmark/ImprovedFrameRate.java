package Benchmark;

import org.herebdragons.utils.FrameRate.FrameRate;
import org.herebdragons.utils.Logger;

import java.text.DecimalFormat;

class ImprovedFrameRate extends FrameRate {

    private final long MAX_STATS_INTERVAL = 1000000000L;
    // record stats every 1 second (roughly)

    private int NO_DELAYS_PER_YIELD = 16;
  /* Number of frames with a delay of 0 ms before the animation thread yields
     to other running threads. */

    private int MAX_FRAME_SKIPS = 5;   // was 2;
    // no. of frames that can be skipped in any one animation loop
    // i.e the games state is updated but not rendered

    private static final int NUM_FPS = 10;
    // number of FPS values stored to get an average

    private long overSleepTime;
    private long prevStatsTime;
    private long excess;
    private long statsInterval;
    private long startTime;
    private int noDelays;
    private int framesSkipped;
    private int timeSpent;
    private long totalElapsedTime;

    private long frameCount = 0;
    private double fpsStore[];
    private long statsCount = 0;

    private long totalFramesSkipped = 0L;
    private double upsStore[];

    private DecimalFormat df = new DecimalFormat("0.##");  // 2 dp

    private Updatable callBack;

    public ImprovedFrameRate(int targetFPS, Updatable callBack) {
        super(targetFPS);
        this.callBack = callBack;

        Logger.debug("Creating FrameRate Object");

        if (targetFPS <= 0)
            throw new IllegalArgumentException("Target FPS must be grater than zero");

    }

    @Override
    public void initialize() {
        super.initialize();
        Logger.debug("Initializinf FrameRate");

        prevStatsTime = lastTime;
        startTime = lastTime;
        noDelays = 0;
        overSleepTime = 0;
        excess = 0;

        // initialise timing elements
        fpsStore = new double[NUM_FPS];
        upsStore = new double[NUM_FPS];
        for (int i = 0; i < NUM_FPS; i++) {
            fpsStore[i] = 0.0;
            upsStore[i] = 0.0;
        }
    }

    @Override
    public synchronized void calculate() {

        currentTime = System.nanoTime();

        delta = currentTime - lastTime;
        sleepTime = (cycleDuration - delta) - overSleepTime;
        lastTime = currentTime;

        frameCount++;
        updateCount++;

        Logger.debug("FrameRate calculating:\n" +
                "Current time:  " + currentTime + "\n" +
                "Delta:         " + delta + "\n" +
                "OverSleepTime: " + overSleepTime + "\n" +
                "Sleep time:    " + sleepTime + "\n" +
                "Lastime:       " + lastTime + "\n" +
                "FrameCOunt:    " + frameCount + "\n" +
                "UpdateCount:   " + updateCount + "\n");

    }

    public synchronized void sleep() {

        if (sleepTime > 0) {
            Logger.debug("Going to sleep for " + sleepTime);
            try {
                Thread.sleep(sleepTime / 1000000L);  // nano -> ms
            } catch (InterruptedException ex) {
                Logger.err("Problem Sleeping");
            }
            overSleepTime = (System.nanoTime() - currentTime) - sleepTime;
            Logger.debug("Updated OversleepTime: " + overSleepTime);
        } else {
            excess -= sleepTime;
            overSleepTime = 0L;

            Logger.debug("Not enough time to sleep\n" +
                    "Excess: " + excess + "\n");

            if (++noDelays >= NO_DELAYS_PER_YIELD) {
                Logger.debug("Yielding Thread\n" +
                        "DelayVount: " + noDelays);

                Thread.yield();
                noDelays = 0;
            }

        }

        lastTime = System.nanoTime();

        Logger.debug("Updating lasttime to " + lastTime);

        int skips = 0;
        while ((excess > cycleDuration) && (skips < MAX_FRAME_SKIPS)) {

            excess -= cycleDuration;

            Logger.debug("Skipping frames\n" +
                    "excess: " + excess + "\n" +
                    "and updating!");
            //call to gameupdate;
            callBack.update();

            skips++;
            frameCount++;

            Logger.debug("skips: " + skips);

        }
        framesSkipped += skips;


        if (debug) {
            Logger.debug("Storing Stats");
            storeStats();
        }
    }

    private void storeStats() {

        statsInterval += cycleDuration;

        if (statsInterval >= MAX_STATS_INTERVAL) {     // record stats every MAX_STATS_INTERVAL
            long timeNow = System.nanoTime();
            timeSpent = (int) ((timeNow - startTime) / 1000000000L);  // ns --> secs

            long realElapsedTime = timeNow - prevStatsTime;   // time since last stats collection
            totalElapsedTime += realElapsedTime;

            totalFramesSkipped += framesSkipped;

            double actualFPS = 0;     // calculate the latest FPS and UPS
            double actualUPS = 0;
            if (totalElapsedTime > 0) {
                actualFPS = (((double) frameCount / totalElapsedTime) * 1000000000L);
                actualUPS = (((double) (frameCount + totalFramesSkipped) / totalElapsedTime)
                        * 1000000000L);
            }

            // store the latest FPS and UPS
            fpsStore[(int) statsCount % NUM_FPS] = actualFPS;
            upsStore[(int) statsCount % NUM_FPS] = actualUPS;
            statsCount = statsCount + 1;

            double totalFPS = 0.0;     // total the stored FPSs and UPSs
            double totalUPS = 0.0;
            for (int i = 0; i < NUM_FPS; i++) {
                totalFPS += fpsStore[i];
                totalUPS += upsStore[i];
            }

            if (statsCount < NUM_FPS) { // obtain the average FPS and UPS
                framesPerSecond = totalFPS / statsCount;
                updatesPerSecond = totalUPS / statsCount;
            } else {
                framesPerSecond = totalFPS / NUM_FPS;
                updatesPerSecond = totalUPS / NUM_FPS;
            }

            framesSkipped = 0;
            prevStatsTime = timeNow;
            statsInterval = 0L;   // reset
        }
    }

    @Override
    public String getResult() {

        StringBuilder sb = new StringBuilder();
        sb.append("Frame Count/Loss: " + frameCount + " / " + totalFramesSkipped + "\n");
        sb.append("Average FPS: " + df.format(framesPerSecond) + "\n");
        sb.append("Average UPS: " + df.format(updatesPerSecond) + "\n");
        sb.append("Time Spent: " + timeSpent + " secs");
        return sb.toString();
    }

    public int getNumDeplayerPerYield() {
        return NO_DELAYS_PER_YIELD;
    }

    public void setNumDeplayerPerYield(int NO_DELAYS_PER_YIELD) {
        this.NO_DELAYS_PER_YIELD = NO_DELAYS_PER_YIELD;
    }

    public int getMaxFrameSkips() {
        return MAX_FRAME_SKIPS;
    }

    public void setMaxFrameSkips(int MAX_FRAME_SKIPS) {
        this.MAX_FRAME_SKIPS = MAX_FRAME_SKIPS;
    }
}
