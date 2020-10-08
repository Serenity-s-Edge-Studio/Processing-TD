import Green.*;
import processing.core.*;
public class Level1 extends Scene{
  public Grid map;
  public Level1(Green engine, PImage image){
   super(engine);
  }
  @Override
  public void prepare(){
    map = new Grid(100, 100, 25, engine.getParent().sketchWidth(), engine.getParent().sketchHeight());
    addObject(map);
  }
  @Override
  public void act(float deltaTime){
    PApplet pEngine = Green.getInstance().getParent();
    System.out.println("MouseX: " + pEngine.pmouseX + " MouseY: " + pEngine.pmouseY);
    engine.getParent().background(51);
    map.DrawTileAtPos(pEngine.pmouseX, pEngine.pmouseY);
  }
}
