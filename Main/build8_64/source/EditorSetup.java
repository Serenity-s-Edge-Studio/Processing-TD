import Green.*;
import processing.core.*;
import g4p_controls.*;
import java.io.*;
import java.util.*;
//Scene for editor setup, this scene will generate the Editor class based on user input
public class EditorSetup extends Scene {
  //UI
  GCustomSlider marginXSlider; 
  GLabel label1; 
  GLabel mLabelX; 
  GLabel mLabelY; 
  GCustomSlider marginYSlider; 
  GLabel mDimensionsPixel; 
  GLabel mTileSize; 
  GCustomSlider tileSizeSlider; 
  GLabel mSize; 
  GLabel mTotalTiles; 
  GButton generateButton; 
  GButton loadButton; 
  GLabel cTitle;
  //Values
  int marginX, marginY, tileSize, mapHeight, mapWidth;
  public EditorSetup(Green engine) {
    super(engine);
  }
  @Override
    public void prepare() {
    createGUI();
    //cache initial values
    marginX = marginXSlider.getValueI();
    marginY = marginYSlider.getValueI();
    tileSize = tileSizeSlider.getValueI();
    //calculate curret map size
    updateMapSize();
    //play the editor song loop
    MusicManager.playEditor();
  }
  @Override
  //This function is called every frame by Green
  public void act(float deltaTime) {
  }
  //Event handler for marginXSlider
  public void updateMarginX(GCustomSlider source, GEvent event) { //_CODE_:marginXSilder:245145:
    System.out.println("marginXSilder - GCustomSlider >> GEvent." + event + " @ " + engine.getParent().millis());
    marginX = source.getValueI();
    updateMapSize();
  } //_CODE_:marginXSilder:245145:

  //Event handler for marginYSlider
  public void updateMarginY(GCustomSlider source, GEvent event) { //_CODE_:marginYSlider:250837:
    System.out.println("marginYSlider - GCustomSlider >> GEvent." + event + " @ " + engine.getParent().millis());
    marginY = source.getValueI();
    updateMapSize();
  } //_CODE_:marginYSlider:250837:

  //Event handler for tileSizeSlider
  public void updateTileSize(GCustomSlider source, GEvent event) { //_CODE_:tileSizeSlider:513064:
    System.out.println("tileSizeSlider - GCustomSlider >> GEvent." + event + " @ " + engine.getParent().millis());
    tileSize = source.getValueI();
    updateMapSize();
  } //_CODE_:tileSizeSlider:513064:

  //Event handler for generateMapButton
  //this will generate the map and load the editor
  public void generateMap(GButton source, GEvent event) { //_CODE_:generateButton:411971:
    System.out.println("generateButton - GButton >> GEvent." + event + " @ " + engine.getParent().millis());
    mapWidth = engine.getParent().sketchWidth() - (marginX*2);
    mapHeight = engine.getParent().sketchHeight() - (marginY*2);
    
    marginX += (mapWidth%tileSize)/2;
    marginY += (mapHeight%tileSize)/2;
    engine.loadWorld(new LevelEditor(engine, new Grid(marginX, marginY, tileSize, 512, 512)));
    disposeUI();
  } //_CODE_:generateButton:411971:

  //Event handler for loadMap button
  //this will handle reading a file to then generate the map form that saved .json file
  public void loadMap(GButton source, GEvent event) { //_CODE_:loadButton:405479:
    System.out.println("loadButton - GButton >> GEvent." + event + " @ " + engine.getParent().millis());
    File file = new File(engine.getParent().sketchPath("data/saves/*.json"));
    engine.getParent().selectInput("Select saved map:", "loadMap", file, this);
  } //_CODE_:loadButton:405479:
  //The event to handle the player selecting a file from the dialog.
  public void loadMap(File selection){
    if (selection != null){
      String name = selection.getName();
      String extension = name.substring(name.lastIndexOf("."));
      if (extension.equals( ".json")){
        engine.loadWorld(new LevelEditor(engine, Grid.fromJSON(engine.getParent().loadJSONObject(selection.getAbsolutePath()))));
        disposeUI();
      }
    }
  }
  //the calculations that get the size of the map.
  public void updateMapSize(){
    mapWidth = engine.getParent().sketchWidth() - (marginX*2);
    mapHeight = engine.getParent().sketchHeight() - (marginY*2);
    
    int tilesX = mapWidth/tileSize; int tilesY = mapHeight/tileSize;
    mDimensionsPixel.setText("Map resolution (pixels): " + mapWidth + " x " + mapHeight);
    mSize.setText("Map size (tiles): " + tilesX + " x " + tilesY);
    mTotalTiles.setText("Total tiles: " + tilesX*tilesY);
  }
  // Create all the GUI controls.
  public void createGUI() {
    G4P.messagesEnabled(false);
    G4P.setGlobalColorScheme(GCScheme.BLUE_SCHEME);
    G4P.setMouseOverEnabled(false);
    marginXSlider = new GCustomSlider(engine.getParent(), 202, 155, 165, 41, "grey_blue");
    marginXSlider.setShowValue(true);
    marginXSlider.setShowLimits(true);
    marginXSlider.setLimits(56, 10, 100);
    marginXSlider.setNumberFormat(G4P.INTEGER, 0);
    marginXSlider.setOpaque(false);
    marginXSlider.addEventHandler(this, "updateMarginX");
    label1 = new GLabel(engine.getParent(), 242, 113, 80, 20);
    label1.setTextAlign(GAlign.CENTER, GAlign.MIDDLE);
    label1.setText("Margins:");
    label1.setOpaque(false);
    mLabelX = new GLabel(engine.getParent(), 201, 133, 80, 20);
    mLabelX.setTextAlign(GAlign.CENTER, GAlign.MIDDLE);
    mLabelX.setText("Margin X");
    mLabelX.setOpaque(false);
    mLabelY = new GLabel(engine.getParent(), 201, 195, 80, 20);
    mLabelY.setTextAlign(GAlign.CENTER, GAlign.MIDDLE);
    mLabelY.setText("Margin Y");
    mLabelY.setOpaque(false);
    marginYSlider = new GCustomSlider(engine.getParent(), 201, 215, 165, 40, "grey_blue");
    marginYSlider.setShowValue(true);
    marginYSlider.setShowLimits(true);
    marginYSlider.setLimits(56, 10, 100);
    marginYSlider.setNumberFormat(G4P.INTEGER, 0);
    marginYSlider.setOpaque(false);
    marginYSlider.addEventHandler(this, "updateMarginY");
    mDimensionsPixel = new GLabel(engine.getParent(), 201, 255, 195, 20);
    mDimensionsPixel.setText("Map resolution (pixels):");
    mDimensionsPixel.setOpaque(false);
    mTileSize = new GLabel(engine.getParent(), 201, 275, 80, 20);
    mTileSize.setTextAlign(GAlign.CENTER, GAlign.MIDDLE);
    mTileSize.setText("Tile Size");
    mTileSize.setOpaque(false);
    tileSizeSlider = new GCustomSlider(engine.getParent(), 201, 296, 166, 40, "grey_blue");
    tileSizeSlider.setShowValue(true);
    tileSizeSlider.setShowLimits(true);
    tileSizeSlider.setLimits(25, 1, 100);
    tileSizeSlider.setNumberFormat(G4P.INTEGER, 0);
    tileSizeSlider.setOpaque(false);
    tileSizeSlider.addEventHandler(this, "updateTileSize");
    mSize = new GLabel(engine.getParent(), 201, 337, 200, 20);
    mSize.setText("Map size (tiles):");
    mSize.setOpaque(false);
    mTotalTiles = new GLabel(engine.getParent(), 201, 358, 200, 20);
    mTotalTiles.setText("Total tiles:");
    mTotalTiles.setOpaque(false);
    generateButton = new GButton(engine.getParent(), 255, 379, 80, 30);
    generateButton.setText("Generate");
    generateButton.addEventHandler(this, "generateMap");
    loadButton = new GButton(engine.getParent(), 230, 44, 109, 30);
    loadButton.setText("Load saved map");
    loadButton.addEventHandler(this, "loadMap");
    cTitle = new GLabel(engine.getParent(), 243, 92, 80, 20);
    cTitle.setTextAlign(GAlign.CENTER, GAlign.MIDDLE);
    cTitle.setText("Custom map:");
    cTitle.setOpaque(false);
  }
  //dispose of all the GUI controls
  void disposeUI() {
    marginXSlider.dispose(); 
    label1.dispose(); 
    mLabelX.dispose(); 
    mLabelY.dispose(); 
    marginYSlider.dispose(); 
    mDimensionsPixel.dispose(); 
    mTileSize.dispose(); 
    tileSizeSlider.dispose(); 
    mSize.dispose(); 
    mTotalTiles.dispose(); 
    generateButton.dispose(); 
    cTitle.dispose();
    loadButton.dispose();
  }
}
