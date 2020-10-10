import Green.*;
import processing.core.*;
public class Level extends Scene{
  public Grid map;
  public Level(Green engine){
   super(engine);
  }
  public Level(Green engine, Grid map){
    super(engine);
    this.map = map;
  }
  @Override
  public void prepare(){
    if (map == null)
      map = new Grid(50, 50, 25, engine.getParent().sketchWidth(), engine.getParent().sketchHeight());
    addObject(map);
  }
  @Override
  public void act(float deltaTime){
    PApplet pEngine = Green.getInstance().getParent();
    //System.out.println("MouseX: " + pEngine.mouseX + " MouseY: " + pEngine.mouseY);
    pEngine.background(51);
  }
}
