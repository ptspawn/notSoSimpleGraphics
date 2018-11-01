package utils.FrameRate;

import org.herebdragons.utils.FrameRate.FrameRate;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class FrameRateTest {

    final long NANOSECOND = 1000000000L;
    final int TARGET_FPS = 60;
    final int ERROR_MARGIN = 2;

    FrameRate fr;

    @Before
    public void testStartUp() {

        fr = new FrameRate(-1);
    }

    @Test
    public void creationTest() {
        fr = new FrameRate(TARGET_FPS);

        assertEquals("Cycle Duration wrong", NANOSECOND / TARGET_FPS, fr.getCycleDuration());
        assertEquals("Limited not correctly set", true, fr.isLimited());

        fr.setTargetFPS(-1);

        assertEquals("Cycle Duration should be zero", 0, fr.getCycleDuration());
        assertEquals("Limited not correctly set", false, fr.isLimited());
    }

    @Test
    public void initTest() {

        fr.setTargetFPS(-1);

        assertEquals("Cycle Duration for an uncontroled framerate must be zero", 0L, fr.getCycleDuration());
        assertEquals("Remaining for an uncontroled FrameRate must be zero", 0L, fr.getRemainingInCyle());

        fr.setTargetFPS(TARGET_FPS);
        fr = new FrameRate(TARGET_FPS);

        assertEquals("Wrong Cycle duration", NANOSECOND / TARGET_FPS, fr.getCycleDuration());

    }

    @Test(timeout = 3000L) //3 seconds timeout
    public void calculateTest() {

        long sleepTime;
        int FPS = 60;
        int OP_DURATION = 5;
        double ERROR = (1000 / (float) FPS - OP_DURATION) / (FPS / OP_DURATION);

        System.out.println("Acceptable Error: " + ERROR);

        int TIMES_TO_COUNT = 100;

        long accumulatedSleep = 0;

        fr.setTargetFPS(FPS);
        fr.initialize();

        for (int i = 0; i < TIMES_TO_COUNT; i++) {

            try {
                Thread.sleep(OP_DURATION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            fr.calculate();

            sleepTime = fr.getRemainingInCyle();
            accumulatedSleep += sleepTime;

        }

        double avg = (accumulatedSleep / (double) TIMES_TO_COUNT);


        System.out.println("Average wait time: " + avg +
                " vs expected wait time: " + (1000 / FPS - OP_DURATION));

        assertTrue("Average not what expected",
                Math.abs(avg - (1000 / (float) FPS - OP_DURATION)) <= ERROR);


    }

    @Test(timeout = 5000L)  //4 seconds timeout
    public void extendedCalculateTest() {

        long sleepTime;
        int FPS = 60;
        int OP_DURATION = 5;
        int NUMBER_CYCLES = 1;
        double ERROR = (1000 / (float) FPS - OP_DURATION) / FPS / 2;
        int TIMES_TO_COUNT = FPS * NUMBER_CYCLES;

        long accumulatedSleep = 0;
        long frameCount = 0;

        long initTimeStamp;
        long endTimeStamp;

        fr.setTargetFPS(FPS);
        fr.initialize();

        for (int i = 0; i < TIMES_TO_COUNT; i++) {

            initTimeStamp = System.nanoTime();
            System.out.println("\nStarting cycle at " + initTimeStamp);

            try {
                System.out.println("Going into render for " + OP_DURATION);
                Thread.sleep(OP_DURATION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            frameCount++;

            fr.calculate();

            sleepTime = fr.getRemainingInCyle();
            System.out.println("Sleep time: " + sleepTime +
                    " vs expected: " + (1000 / (float) FPS - OP_DURATION));
            accumulatedSleep += sleepTime;

            try {
                System.out.println("Sleeping for " + sleepTime);
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            endTimeStamp=System.nanoTime();

            System.out.println("Cycle ended at: " + endTimeStamp);
            System.out.println("\nCycle Duration: " +
                    (endTimeStamp-initTimeStamp) +
                    " vs " + NANOSECOND/FPS);


        }

        double avg = (accumulatedSleep / (double) TIMES_TO_COUNT);


        System.out.println("Average FPS: " + fr.getFramesPerSecond() +
                " vs expected FPS: " + TARGET_FPS);

        assertTrue("FPS Average not what expected",
                Math.abs(fr.getFramesPerSecond() - TARGET_FPS) <= 1);

        //System.out.println(fr.getResult());

    }

    private void updateCounter(long counter, long limit) {
        if (counter % 20 == 0)
            System.out.println("20 frames passed");

        if (counter % 60 == 0) {
            System.out.println("60 frames passed");
        }

        if (counter == limit * 3) {

            assertEquals("FrameRate was not the desired", limit,
                    fr.getFramesPerSecond());
            assertEquals("Update rate was not the same", limit, fr.getUpdatesPerSecond());

            System.out.println(fr.getResult());

            return;
        }
    }


}
