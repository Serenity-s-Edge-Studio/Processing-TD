import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import shiffman.box2d.*; 
import Green.*; 
import g4p_controls.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Main extends PApplet {







public Green engine;
private MainMenu menu;
public void setup()
{
    
    print("Starting game engine");
    engine = new Green(this);
    menu = new MainMenu(engine);
    engine.loadWorld(menu);
    loadTileResources();
}
public void draw(){
  background(51);
  engine.handleAct();
  engine.handleDraw();
  engine.handleInput();
}
public void mousePressed(){
  engine.handleMouseDown(mouseButton);
}
public void mouseReleased(){
  engine.handleMouseUp(mouseButton);
}
public void mouseMoved(){
  engine.handleMousePosition(pmouseX, pmouseY, mouseX, mouseY);
}
public void keyPressed(){
  engine.handleKeyDown(key, keyCode);
}
public void keyReleased(){
  engine.handleKeyUp(key, keyCode);
}
public void loadTileResources(){
  Tile.sprites = new PImage[5];
  Tile.sprites[0] = loadImage("Path_Tile.png");
  Tile.sprites[1] = loadImage("Sand Tile.png");
  Enemy.sprites = new PImage[1];
  Enemy.sprites[0] = loadImage("Enemy_Shark.png");
}
/* =========================================================
 * ====                   WARNING                        ===
 * =========================================================
 * The code in this tab has been generated from the GUI form
 * designer and care should be taken when editing this file.
 * Only add/edit code inside the event handlers i.e. only
 * use lines between the matching comment tags. e.g.

 void myBtnEvents(GButton button) { //_CODE_:button1:12356:
     // It is safe to enter your event code here  
 } //_CODE_:button1:12356:
 
 * Do not rename this tab!
 * =========================================================
 */

public void loadGame(GImageButton source, GEvent event) { //_CODE_:playButton:897102:
  println("imgButton1 - GImageButton >> GEvent." + event + " @ " + millis());
} //_CODE_:playButton:897102:

public void instructionScene(GImageButton source, GEvent event) { //_CODE_:instructionsButton:264639:
  println("instructionsButton - GImageButton >> GEvent." + event + " @ " + millis());
} //_CODE_:instructionsButton:264639:

public void exitGame(GImageButton source, GEvent event) { //_CODE_:quitButton:914607:
  println("quitButton - GImageButton >> GEvent." + event + " @ " + millis());
} //_CODE_:quitButton:914607:

public void launchEditor(GImageButton source, GEvent event) { //_CODE_:editorButton:249830:
  println("imgButton1 - GImageButton >> GEvent." + event + " @ " + millis());
} //_CODE_:editorButton:249830:


public void updateMarginX(GCustomSlider source, GEvent event) { //_CODE_:marginXSilder:245145:
  println("marginXSilder - GCustomSlider >> GEvent." + event + " @ " + millis());
} //_CODE_:marginXSilder:245145:

public void updateMarginY(GCustomSlider source, GEvent event) { //_CODE_:marginYSlider:250837:
  println("marginYSlider - GCustomSlider >> GEvent." + event + " @ " + millis());
} //_CODE_:marginYSlider:250837:

public void updateTileSize(GCustomSlider source, GEvent event) { //_CODE_:tileSizeSlider:513064:
  println("tileSizeSlider - GCustomSlider >> GEvent." + event + " @ " + millis());
} //_CODE_:tileSizeSlider:513064:

public void generateMap(GButton source, GEvent event) { //_CODE_:generateButton:411971:
  println("generateButton - GButton >> GEvent." + event + " @ " + millis());
} //_CODE_:generateButton:411971:

public void loadMap(GButton source, GEvent event) { //_CODE_:loadButton:405479:
  println("loadButton - GButton >> GEvent." + event + " @ " + millis());
} //_CODE_:loadButton:405479:

public void setFile(GDropList source, GEvent event) { //_CODE_:fileList:797367:
  println("dropList1 - GDropList >> GEvent." + event + " @ " + millis());
} //_CODE_:fileList:797367:

synchronized public void win_draw3(PApplet appc, GWinData data) { //_CODE_:window2:515693:
  appc.background(230);
} //_CODE_:window2:515693:

public void setTilePath(GImageButton source, GEvent event) { //_CODE_:pathButton:322211:
  println("imgButton3 - GImageButton >> GEvent." + event + " @ " + millis());
} //_CODE_:pathButton:322211:

public void saveMap(GImageButton source, GEvent event) { //_CODE_:saveMapButton:670195:
  println("imgButton4 - GImageButton >> GEvent." + event + " @ " + millis());
} //_CODE_:saveMapButton:670195:

public void clearMap(GImageButton source, GEvent event) { //_CODE_:clearMapButton:453486:
  println("imgButton5 - GImageButton >> GEvent." + event + " @ " + millis());
} //_CODE_:clearMapButton:453486:

public void setTileEmpty(GImageButton source, GEvent event) { //_CODE_:tileButton2:750225:
  println("imgButton6 - GImageButton >> GEvent." + event + " @ " + millis());
} //_CODE_:tileButton2:750225:

public void setTileMount(GImageButton source, GEvent event) { //_CODE_:mountButton:663354:
  println("imgButton7 - GImageButton >> GEvent." + event + " @ " + millis());
} //_CODE_:mountButton:663354:

public void setTileGoal(GImageButton source, GEvent event) { //_CODE_:goalButton:345046:
  println("imgButton8 - GImageButton >> GEvent." + event + " @ " + millis());
} //_CODE_:goalButton:345046:

public void setTileSpawn(GImageButton source, GEvent event) { //_CODE_:spawnTileButton:353297:
  println("imgButton9 - GImageButton >> GEvent." + event + " @ " + millis());
} //_CODE_:spawnTileButton:353297:

public void exitEditor(GImageButton source, GEvent event) { //_CODE_:exit:439938:
  println("exit - GImageButton >> GEvent." + event + " @ " + millis());
} //_CODE_:exit:439938:

synchronized public void win_draw1(PApplet appc, GWinData data) { //_CODE_:window3:574836:
  appc.background(230);
} //_CODE_:window3:574836:

public void startWave(GImageButton source, GEvent event) { //_CODE_:startWaveButton:956681:
  println("startWaveButton - GImageButton >> GEvent." + event + " @ " + millis());
} //_CODE_:startWaveButton:956681:

public void imgButton1_click1(GImageButton source, GEvent event) { //_CODE_:imgButton1:819052:
  println("imgButton1 - GImageButton >> GEvent." + event + " @ " + millis());
} //_CODE_:imgButton1:819052:



// Create all the GUI controls. 
// autogenerated do not edit
public void createGUI(){
  G4P.messagesEnabled(false);
  G4P.setGlobalColorScheme(GCScheme.BLUE_SCHEME);
  G4P.setMouseOverEnabled(false);
  surface.setTitle("Sketch Window");
  playButton = new GImageButton(this, 204, 108, new String[] { "Start_Button.png", "Start_Button.png", "Start_Button.png" } );
  playButton.addEventHandler(this, "loadGame");
  instructionsButton = new GImageButton(this, 204, 256, new String[] { "Instructions_Button.png", "Instructions_Button.png", "Instructions_Button.png" } );
  instructionsButton.addEventHandler(this, "instructionScene");
  quitButton = new GImageButton(this, 204, 348, new String[] { "Exit_Button.png", "Exit_Button.png", "Exit_Button.png" } );
  quitButton.addEventHandler(this, "exitGame");
  editorButton = new GImageButton(this, 204, 179, new String[] { "Start_Button.png", "Start_Button.png", "Start_Button.png" } );
  editorButton.addEventHandler(this, "launchEditor");
  window1 = GWindow.getWindow(this, "Window title", 0, 0, 512, 512, JAVA2D);
  window1.noLoop();
  window1.setActionOnClose(G4P.KEEP_OPEN);
  window1.addDrawHandler(this, "win_draw1");
  marginXSilder = new GCustomSlider(window1, 306, 267, 165, 41, "grey_blue");
  marginXSilder.setShowValue(true);
  marginXSilder.setShowLimits(true);
  marginXSilder.setLimits(50, 10, 100);
  marginXSilder.setNumberFormat(G4P.INTEGER, 0);
  marginXSilder.setOpaque(false);
  marginXSilder.addEventHandler(this, "updateMarginX");
  label1 = new GLabel(window1, 242, 113, 80, 20);
  label1.setTextAlign(GAlign.CENTER, GAlign.MIDDLE);
  label1.setText("Margins:");
  label1.setOpaque(false);
  mLabelX = new GLabel(window1, 201, 133, 80, 20);
  mLabelX.setTextAlign(GAlign.CENTER, GAlign.MIDDLE);
  mLabelX.setText("Margin X");
  mLabelX.setOpaque(false);
  mLabelY = new GLabel(window1, 201, 195, 80, 20);
  mLabelY.setTextAlign(GAlign.CENTER, GAlign.MIDDLE);
  mLabelY.setText("Margin Y");
  mLabelY.setOpaque(false);
  marginYSlider = new GCustomSlider(window1, 201, 215, 165, 40, "grey_blue");
  marginYSlider.setShowValue(true);
  marginYSlider.setShowLimits(true);
  marginYSlider.setLimits(50, 10, 100);
  marginYSlider.setNumberFormat(G4P.INTEGER, 0);
  marginYSlider.setOpaque(false);
  marginYSlider.addEventHandler(this, "updateMarginY");
  mDimensionsPixel = new GLabel(window1, 201, 255, 195, 20);
  mDimensionsPixel.setText("Map resolution (pixels):");
  mDimensionsPixel.setOpaque(false);
  mTileSize = new GLabel(window1, 201, 275, 80, 20);
  mTileSize.setTextAlign(GAlign.CENTER, GAlign.MIDDLE);
  mTileSize.setText("Tile Size");
  mTileSize.setOpaque(false);
  tileSizeSlider = new GCustomSlider(window1, 201, 296, 166, 40, "grey_blue");
  tileSizeSlider.setShowValue(true);
  tileSizeSlider.setShowLimits(true);
  tileSizeSlider.setLimits(25, 1, 100);
  tileSizeSlider.setNumberFormat(G4P.INTEGER, 0);
  tileSizeSlider.setOpaque(false);
  tileSizeSlider.addEventHandler(this, "updateTileSize");
  mSize = new GLabel(window1, 201, 337, 200, 20);
  mSize.setText("Map size (tiles):");
  mSize.setOpaque(false);
  mTotalTiles = new GLabel(window1, 201, 358, 200, 20);
  mTotalTiles.setText("Total tiles:");
  mTotalTiles.setOpaque(false);
  generateButton = new GButton(window1, 255, 379, 80, 30);
  generateButton.setText("Generate");
  generateButton.addEventHandler(this, "generateMap");
  loadButton = new GButton(window1, 230, 44, 109, 30);
  loadButton.setText("Load saved map");
  loadButton.addEventHandler(this, "loadMap");
  cTitle = new GLabel(window1, 383, 61, 80, 20);
  cTitle.setTextAlign(GAlign.CENTER, GAlign.MIDDLE);
  cTitle.setText("Custom map:");
  cTitle.setOpaque(false);
  togGroup1 = new GToggleGroup();
  togGroup2 = new GToggleGroup();
  fileList = new GDropList(window1, 74, 79, 398, 385, 10, 10);
  fileList.setItems(loadStrings("list_797367"), 0);
  fileList.addEventHandler(this, "setFile");
  window2 = GWindow.getWindow(this, "Window title", 0, 0, 512, 512, JAVA2D);
  window2.noLoop();
  window2.setActionOnClose(G4P.KEEP_OPEN);
  window2.addDrawHandler(this, "win_draw3");
  pathButton = new GImageButton(window2, 137, 0, 42, 42, new String[] { "Path_Tile.png", "Path_Tile.png", "Path_Tile.png" } );
  pathButton.addEventHandler(this, "setTilePath");
  saveMapButton = new GImageButton(window2, 4, 466, 127, 42, new String[] { "Save_Map_Button.png", "Save_Map_Button.png", "Save_Map_Button.png" } );
  saveMapButton.addEventHandler(this, "saveMap");
  clearMapButton = new GImageButton(window2, 0, 0, 127, 42, new String[] { "Clear_Map_Button.png", "Clear_Map_Button.png", "Clear_Map_Button.png" } );
  clearMapButton.addEventHandler(this, "clearMap");
  num1 = new GLabel(window2, 127, 0, 10, 45);
  num1.setText("1");
  num1.setOpaque(false);
  num2 = new GLabel(window2, 179, 0, 10, 45);
  num2.setText("2");
  num2.setOpaque(false);
  tileButton2 = new GImageButton(window2, 189, 0, 42, 42, new String[] { "Sand Tile.png", "Sand Tile.png", "Sand Tile.png" } );
  tileButton2.addEventHandler(this, "setTileEmpty");
  mountButton = new GImageButton(window2, 241, 0, 42, 42, new String[] { "Mount Tile.png", "Mount Tile.png", "Mount Tile.png" } );
  mountButton.addEventHandler(this, "setTileMount");
  num3 = new GLabel(window2, 231, 0, 10, 45);
  num3.setText("3");
  num3.setOpaque(false);
  num4 = new GLabel(window2, 282, 0, 10, 45);
  num4.setText("4");
  num4.setOpaque(false);
  goalButton = new GImageButton(window2, 292, 0, 45, 45, new String[] { "Goal Tile.png", "Goal Tile.png", "Goal Tile.png" } );
  goalButton.addEventHandler(this, "setTileGoal");
  num5 = new GLabel(window2, 337, 0, 10, 42);
  num5.setText("5");
  num5.setOpaque(false);
  spawnTileButton = new GImageButton(window2, 347, 0, 45, 45, new String[] { "Spawn Tile.png", "Spawn Tile.png", "Spawn Tile.png" } );
  spawnTileButton.addEventHandler(this, "setTileSpawn");
  exit = new GImageButton(window2, 130, 466, new String[] { "Exit_Button.png", "Exit_Button.png", "Exit_Button.png" } );
  exit.addEventHandler(this, "exitEditor");
  window3 = GWindow.getWindow(this, "Window title", 0, 0, 512, 512, JAVA2D);
  window3.noLoop();
  window3.setActionOnClose(G4P.KEEP_OPEN);
  window3.addDrawHandler(this, "win_draw1");
  startWaveButton = new GImageButton(window3, 383, 468, new String[] { "Start_Wave_Button.png", "Start_Wave_Button.png", "Start_Wave_Button.png" } );
  startWaveButton.addEventHandler(this, "startWave");
  waveCount = new GLabel(window3, 276, 473, 107, 40);
  waveCount.setText("Wave number:");
  waveCount.setOpaque(false);
  enemyCount = new GLabel(window3, 128, 470, 146, 40);
  enemyCount.setText("Remaining enemies: ");
  enemyCount.setOpaque(false);
  imgButton1 = new GImageButton(window3, 0, 468, new String[] { "Exit_Button.png", "Exit_Button.png", "Exit_Button.png" } );
  imgButton1.addEventHandler(this, "imgButton1_click1");
  scoreText = new GLabel(window3, 0, 0, 80, 40);
  scoreText.setText("Score: 0");
  scoreText.setOpaque(false);
  moneyText = new GLabel(window3, 431, 0, 80, 40);
  moneyText.setTextAlign(GAlign.CENTER, GAlign.MIDDLE);
  moneyText.setText("Money: $0");
  moneyText.setOpaque(false);
  multiplierText = new GLabel(window3, 80, 0, 128, 40);
  multiplierText.setText("Score multiplier: x1.0");
  multiplierText.setTextBold();
  multiplierText.setOpaque(false);
  window1.loop();
  window2.loop();
  window3.loop();
}

// Variable declarations 
// autogenerated do not edit
GImageButton playButton; 
GImageButton instructionsButton; 
GImageButton quitButton; 
GImageButton editorButton; 
GWindow window1;
GCustomSlider marginXSilder; 
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
GToggleGroup togGroup1; 
GToggleGroup togGroup2; 
GDropList fileList; 
GWindow window2;
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
GWindow window3;
GImageButton startWaveButton; 
GLabel waveCount; 
GLabel enemyCount; 
GImageButton imgButton1; 
GLabel scoreText; 
GLabel moneyText; 
GLabel multiplierText; 
  public void settings() {  size(512, 512); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Main" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
