package org.herebdragons.utils.FrameRate;

public class notSoSimpleFrameRate {

    private static int updateCount = 0;
    private static int renderCount = 0;


    private static final int MAX_FRAME_SKIPS = 5;
    // no. of frames that can be skipped in any one animation loop
    // i.e the games state is updated but not rendered

    private static final int NO_DELAYS_PER_YIELD = 2;

    private long period;

    public notSoSimpleFrameRate(){
        period = 1000000000 / 60;
    }

    public void run( )
/* Repeatedly update, render, sleep so loop takes close
   to period nsecs. Sleep inaccuracies are handled.
   The timing calculation use the Java 3D timer.
   Overruns in update/renders will cause extra updates
   to be carried out so UPS ~== requested FPS
*/
    {
        long beforeTime, afterTime, timeDiff, sleepTime;
        long overSleepTime = 0L;
        int noDelays = 0;
        long excess = 0L;
        beforeTime = System.nanoTime();
        boolean running;

        running = true;
        while(running) {
            gameUpdate( );
            gameRender( );
            //paintScreen( );
            afterTime = System.nanoTime();
            timeDiff = afterTime - beforeTime;
            sleepTime = (period - timeDiff) - overSleepTime;
            if (sleepTime > 0) {   // some time left in this cycle
                sleep(sleepTime/1000000L);  // nano -> ms

                overSleepTime = (System.nanoTime() - afterTime) - sleepTime;
            }
            else {    // sleepTime <= 0; frame took longer than the period
                excess -= sleepTime;  // store excess time value
                overSleepTime = 0L;
                if (++noDelays >= NO_DELAYS_PER_YIELD) {
                    Thread.yield();   // give another thread a chance to run
                    noDelays = 0;
                }
            }
            beforeTime = System.nanoTime();
    /* If frame animation is taking too long, update the game state
       without rendering it, to get the updates/sec nearer to
       the required FPS. */
            int skips = 0;
            while((excess > period) && (skips < MAX_FRAME_SKIPS)) {
                excess -= period;
                gameUpdate( );      // update state but don't render
                skips++;
            }
        }
        System.exit(0);
    } // end of run(

    private void gameUpdate(){
        System.out.println("update #" + ++updateCount);
    }

    private void gameRender(){
        System.out.println("starting render #" + ++renderCount);
        sleep(200);
        System.out.println("finished render #" + renderCount);
    }

    private void sleep(long milis){
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            System.err.println("Interrupted Exception");
        }
    }
}
