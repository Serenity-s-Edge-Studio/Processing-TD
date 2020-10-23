import Green.*;

import processing.sound.*;
import processing.core.*;
import java.util.*;
public class MusicManager{
  public static boolean initialized = false;
  public static SoundFile seashells;
  public static SoundFile letsGoo;
  public static SoundFile sunset;
  public static SoundFile sharkMixtape;
  public static Timer queue;
  
  public static void init(){
    if (initialized == false){
      PApplet pEngine = Green.getInstance().getParent();
      seashells = new SoundFile(pEngine, "Seashells.wav");
      letsGoo = new SoundFile(pEngine, "LetsGoo.wav");
      sunset = new SoundFile(pEngine, "Sunset.wav");
      sharkMixtape = new SoundFile(pEngine, "SharkMixtape.wav");
      initialized = true;
      queue = new Timer();
    }
  }
  public static void playMenu(){
    if (!seashells.isPlaying()){
      seashells.loop();
    }
    letsGoo.stop();
    sunset.stop();
    sharkMixtape.stop();
  }
  public static void levelMusic(){
    seashells.stop();
    letsGoo.stop();
    sunset.stop();
    sharkMixtape.stop();
    sunset.loop();
  }
  public static void playSunset(){
    sharkMixtape.stop();
    sunset.play();
    queue.schedule(new TimerTask(){
      @Override
      public void run(){
        playShark();
      }
    }, (long)sunset.duration()*1000L);
  }
  public static void playShark(){
    sunset.stop();
    sharkMixtape.play();
    queue.schedule(new TimerTask(){
      @Override
      public void run(){
        playSunset();
      }
    }, (long)sharkMixtape.duration()*1000L);
  }
  public static void playShark(boolean triggered){
    if (triggered) playShark();
  }
  public static void playEditor(){
    letsGoo.stop();
    sunset.stop();
    sharkMixtape.stop();
    seashells.stop();
    letsGoo.loop();
  }
}
