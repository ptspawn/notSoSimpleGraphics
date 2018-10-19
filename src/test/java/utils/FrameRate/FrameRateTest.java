package utils.FrameRate;

import org.herebdragons.utils.FrameRate.FrameRate;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FrameRateTest {

    FrameRate fr;

    @Before
    public void testStartUp() {

        fr = new FrameRate(-1);
    }

    @Test
    public void initTest() {

        fr.setTargetFPS(-1);

        assertEquals("Cycle Duration for an uncontroled framerate must be zero", 0L, fr.getCycleDuration());
        assertEquals("Remaining for an uncontroled FrameRate must be zero", 0L, fr.getRemainingInCyle());


        int TARGET_FR = 60;
        fr.setTargetFPS(TARGET_FR);
        fr = new FrameRate(TARGET_FR);

        assertEquals("Wrong Cycle duration", (long) (1e9 / TARGET_FR), fr.getCycleDuration());


    }

    @Test
    public void restrictedTest() {

        int TARGET_FPS = 60;
        int ERROR_MARGIN = 0;

        fr.setTargetFPS(TARGET_FPS);

        fr.initialize();

        boolean running = true;
        int opDuration = 10;
        long totalOpDuration = 0;
        long cycleDuration = fr.getCycleDuration();
        long expectedOutcome;
        //Run operation that lasts incrementally longer

        while (running) {

            //fake Get Input

            //Fake update

            //Fake Render

            try {
                Thread.sleep(opDuration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            fr.calculate();

            totalOpDuration += opDuration;

            expectedOutcome = (long) (cycleDuration * 1e-6) - opDuration;
            expectedOutcome = expectedOutcome > 0 ? expectedOutcome : 0;

            assertEquals("Remaining in cycle should be equal to " +
                            "cycleDur: " + cycleDuration + " opDur: " +
                            +opDuration, expectedOutcome,
                    fr.getRemainingInCyle(), ERROR_MARGIN);

            opDuration *= 2;

            if (fr.getRemainingInCyle() == 0)
                running = false;

        }

    }


}
