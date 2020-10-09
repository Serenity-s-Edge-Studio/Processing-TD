import Green.*;
import processing.core.*;
public class Level extends Scene{
  public Grid map;
  private Tile.type selectedTileType = Tile.type.Walkable;
  public Level(Green engine){
   super(engine);
  }
  @Override
  public void prepare(){
    map = new Grid(50, 50, 25, engine.getParent().sketchWidth(), engine.getParent().sketchHeight());
    addObject(map);
  }
  @Override
  public void act(float deltaTime){
    PApplet pEngine = Green.getInstance().getParent();
    //System.out.println("MouseX: " + pEngine.mouseX + " MouseY: " + pEngine.mouseY);
    pEngine.background(51);
    if (engine.isMouseButtonDown(PConstants.LEFT))
      if (map.getTileAtPosition(pEngine.mouseX, pEngine.mouseY) != null)
        map.SetTile(pEngine.mouseX, pEngine.mouseY, selectedTileType);
    if (engine.isKeyDown('1'))
      selectedTileType = Tile.type.Walkable;
    else if (engine.isKeyDown('2'))
      selectedTileType = Tile.type.Empty;
    else if (engine.isKeyDown('3'))
      selectedTileType = Tile.type.Mount;
    else if (engine.isKeyDown('4'))
      selectedTileType = Tile.type.Goal;
    else if (engine.isKeyDown('5'))
      selectedTileType = Tile.type.Start;
    
  }
}
