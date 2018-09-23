package org.herebdragons.utils;

public class FrameRate {
   
   private String frameRate;
   private long lastTime;
   private long delta;
   private int frameCount;

   public void initialize() {
      lastTime = System.nanoTime();
      frameRate = "FPS 0";
   }
   
   public void calculate() {
      long current = System.nanoTime();
      delta += current - lastTime;
      lastTime = current;
      frameCount++;
      if( delta > 1000 ) {
         delta -= 1000;
         frameRate = String.format( "FPS %s", frameCount );
         frameCount = 0;
      }
   }
   
   public String getFrameRate() {
      return frameRate;
   }
}
