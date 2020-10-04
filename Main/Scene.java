import Green.*;
import processing.core.*;
public abstract class Scene extends World{
  public Green engine;
  public Scene(Green engine){
   super();
   this.engine = engine;
  }
  public Scene(Green engine, PImage image){
   super(image);
   this.engine = engine;
  }
}
