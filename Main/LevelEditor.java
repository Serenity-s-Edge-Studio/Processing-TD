import Green.*;
import processing.core.*;
import g4p_controls.*;
public class LevelEditor extends Scene {
  public Grid map;
  GImageButton pathButton; 
  GImageButton saveMapButton; 
  GImageButton clearMapButton; 
  GLabel num1; 
  GLabel num2; 
  GImageButton tileButton2; 
  GImageButton mountButton; 
  GLabel num3; 
  GLabel num4; 
  GImageButton goalButton; 
  GLabel num5; 
  GImageButton spawnTileButton;
  GImageButton exit;
  private Tile.type selectedTileType = Tile.type.Walkable;
  public LevelEditor(Green engine) {
    super(engine);
  }
  public LevelEditor(Green engine, Grid map) {
    super(engine);
    this.map = map;
  }
  @Override
    public void prepare() {
    if (map == null)
      map = new Grid(50, 50, 25, engine.getParent().sketchWidth(), engine.getParent().sketchHeight());
    addObject(map);
    createGUI();
  }
  @Override
    public void act(float deltaTime) {
    PApplet pEngine = Green.getInstance().getParent();
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
  public void setTilePath(GImageButton source, GEvent event) { //_CODE_:pathButton:322211:
    System.out.println("imgButton3 - GImageButton >> GEvent." + event + " @ " + engine.getParent().millis());
    selectedTileType = Tile.type.Walkable;
  } //_CODE_:pathButton:322211:

  public void saveMap(GImageButton source, GEvent event) { //_CODE_:saveMapButton:670195:
    System.out.println("imgButton4 - GImageButton >> GEvent." + event + " @ " + engine.getParent().millis());
    engine.getParent().saveJSONObject(map.toJSON(), "data/saves/test.json");
  } //_CODE_:saveMapButton:670195:

  public void clearMap(GImageButton source, GEvent event) { //_CODE_:clearMapButton:453486:
    System.out.println("imgButton5 - GImageButton >> GEvent." + event + " @ " + engine.getParent().millis());
    map.clear();
  } //_CODE_:clearMapButton:453486:

  public void setTileEmpty(GImageButton source, GEvent event) { //_CODE_:tileButton2:750225:
    System.out.println("imgButton6 - GImageButton >> GEvent." + event + " @ " + engine.getParent().millis());
    selectedTileType = Tile.type.Empty;
  } //_CODE_:tileButton2:750225:

  public void setTileMount(GImageButton source, GEvent event) { //_CODE_:mountButton:663354:
    System.out.println("imgButton7 - GImageButton >> GEvent." + event + " @ " + engine.getParent().millis());
    selectedTileType = Tile.type.Mount;
  } //_CODE_:mountButton:663354:

  public void setTileGoal(GImageButton source, GEvent event) { //_CODE_:goalButton:345046:
    System.out.println("imgButton8 - GImageButton >> GEvent." + event + " @ " + engine.getParent().millis());
    selectedTileType = Tile.type.Goal;
  } //_CODE_:goalButton:345046:

  public void setTileSpawn(GImageButton source, GEvent event) { //_CODE_:spawnTileButton:353297:
    System.out.println("imgButton9 - GImageButton >> GEvent." + event + " @ " + engine.getParent().millis());
    selectedTileType = Tile.type.Start;
  } //_CODE_:spawnTileButton:353297:
  public void exitEditor(GImageButton source, GEvent event) { //_CODE_:exit:439938:
    System.out.println("exit - GImageButton >> GEvent." + event + " @ " + engine.getParent().millis());
    engine.loadWorld(new MainMenu(engine));
    disposeUI();
  } //_CODE_:exit:439938:
  public void createGUI() {
    G4P.messagesEnabled(false);
    G4P.setGlobalColorScheme(GCScheme.BLUE_SCHEME);
    G4P.setMouseOverEnabled(false);
    pathButton = new GImageButton(engine.getParent(), 137, 0, 42, 42, new String[] { "Path_Tile.png", "Path_Tile.png", "Path_Tile.png" } );
    pathButton.addEventHandler(this, "setTilePath");
    saveMapButton = new GImageButton(engine.getParent(), 0, 469, 127, 42, new String[] { "Save_Map_Button.png", "Save_Map_Button.png", "Save_Map_Button.png" } );
    saveMapButton.addEventHandler(this, "saveMap");
    clearMapButton = new GImageButton(engine.getParent(), 0, 0, 127, 42, new String[] { "Clear_Map_Button.png", "Clear_Map_Button.png", "Clear_Map_Button.png" } );
    clearMapButton.addEventHandler(this, "clearMap");
    num1 = new GLabel(engine.getParent(), 127, 0, 10, 45);
    num1.setText("1");
    num1.setOpaque(false);
    num2 = new GLabel(engine.getParent(), 179, 0, 10, 45);
    num2.setText("2");
    num2.setOpaque(false);
    tileButton2 = new GImageButton(engine.getParent(), 189, 0, 42, 42, new String[] { "Sand Tile.png", "Sand Tile.png", "Sand Tile.png" } );
    tileButton2.addEventHandler(this, "setTileEmpty");
    mountButton = new GImageButton(engine.getParent(), 241, 0, 42, 42, new String[] { "Mount Tile.png", "Mount Tile.png", "Mount Tile.png" } );
    mountButton.addEventHandler(this, "setTileMount");
    num3 = new GLabel(engine.getParent(), 231, 0, 10, 45);
    num3.setText("3");
    num3.setOpaque(false);
    num4 = new GLabel(engine.getParent(), 282, 0, 10, 45);
    num4.setText("4");
    num4.setOpaque(false);
    goalButton = new GImageButton(engine.getParent(), 292, 0, 45, 45, new String[] { "Goal Tile.png", "Goal Tile.png", "Goal Tile.png" } );
    goalButton.addEventHandler(this, "setTileGoal");
    num5 = new GLabel(engine.getParent(), 337, 0, 10, 42);
    num5.setText("5");
    num5.setOpaque(false);
    spawnTileButton = new GImageButton(engine.getParent(), 347, 0, 45, 45, new String[] { "Spawn Tile.png", "Spawn Tile.png", "Spawn Tile.png" } );
    spawnTileButton.addEventHandler(this, "setTileSpawn");
    exit = new GImageButton(engine.getParent(), 130, 469, new String[] { "Exit_Button.png", "Exit_Button.png", "Exit_Button.png" } );
    exit.addEventHandler(this, "exitEditor");
  }
  public void disposeUI() {
    pathButton.dispose(); 
    saveMapButton.dispose(); 
    clearMapButton.dispose(); 
    num1.dispose(); 
    num2.dispose(); 
    tileButton2.dispose(); 
    mountButton.dispose(); 
    num3.dispose(); 
    num4.dispose(); 
    goalButton.dispose(); 
    num5.dispose(); 
    spawnTileButton.dispose();
    exit.dispose();
  }
}
