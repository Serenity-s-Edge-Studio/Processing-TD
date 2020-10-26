import processing.sound.*;

import g4p_controls.*;

import Green.*;
import processing.core.*;
import java.io.*;
public class MainMenu extends Scene {
  GImageButton playButton;
  GImageButton instructionsButton; 
  GImageButton quitButton;
  GImageButton editorButton; 
  public MainMenu(Green engine) {
    super(engine);
  }
  @Override
    public void prepare() {
    createGUI();
    MusicManager.init();
    MusicManager.playMenu();
    addObject(new Canvas(Green.getInstance().getParent().sketchWidth()/2, 40));
  }
  @Override
    public void act(float deltaTime) {
      
  }
  public void loadGame(GImageButton source, GEvent event) {
    System.out.println("loadButton - GButton >> GEvent." + event + " @ " + engine.getParent().millis());
    System.out.println("Load Game");
    File file = new File(engine.getParent().sketchPath("data/saves/*.json"));
    engine.getParent().selectInput("Select saved map:", "loadMap", file, this);
  } //_CODE_:loadButton:405479:
  public void loadMap(File selection){
    if (selection != null){
      String name = selection.getName();
      String extension = name.substring(name.lastIndexOf("."));
      if (extension.equals( ".json")){
        engine.loadWorld(new Level(engine, Grid.fromJSON(engine.getParent().loadJSONObject(selection.getAbsolutePath()))));
        disposeUI();
      }
    }
  }
  public void instructionScene(GImageButton source, GEvent event) {
    System.out.println("instructionsButton - GImageButton >> GEvent." + event + " @ " + engine.getParent().millis());
  }
  public void exitGame(GImageButton source, GEvent event) {
    System.out.println("quitButton - GImageButton >> GEvent." + event + " @ " + engine.getParent().millis());
    engine.getParent().exit();
  }
  public void launchEditor(GImageButton source, GEvent event) {
    System.out.println("imgButton1 - GImageButton >> GEvent." + event + " @ " + engine.getParent().millis());
    disposeUI();
    engine.loadWorld(new EditorSetup(engine));
  } 
  public void createGUI() {
    G4P.messagesEnabled(false);
    G4P.setGlobalColorScheme(GCScheme.BLUE_SCHEME);
    G4P.setMouseOverEnabled(false);
    playButton = new GImageButton(engine.getParent(), engine.getParent().sketchWidth()/2 - 60, 108, new String[] { "Start_Button.png", "Start_Button.png", "Start_Button.png" } );
    playButton.addEventHandler(this, "loadGame");
    instructionsButton = new GImageButton(engine.getParent(), engine.getParent().sketchWidth()/2 - 60, 256, new String[] { "Instructions_Button.png", "Instructions_Button.png", "Instructions_Button.png" } );
    instructionsButton.addEventHandler(this, "instructionScene");
    quitButton = new GImageButton(engine.getParent(), engine.getParent().sketchWidth()/2 - 60, 348, new String[] { "Exit_Button.png", "Exit_Button.png", "Exit_Button.png" } );
    quitButton.addEventHandler(this, "exitGame");
    editorButton = new GImageButton(engine.getParent(), engine.getParent().sketchWidth()/2 - 60, 179, new String[] { "Map_Editor_Button.png", "Map_Editor_Button.png", "Map_Editor_Button.png" } );
    editorButton.addEventHandler(this, "launchEditor");
  }
  public void disposeUI() {
    playButton.dispose();
    instructionsButton.dispose();
    quitButton.dispose();
    editorButton.dispose();
  }
  class Canvas extends Actor{
    public Canvas(int x, int y){
      super(x,y, 100, 100);
    }
    @Override
    public void act(float deltaTime){}
    @Override
    public void draw(){
      PApplet pEngine = Green.getInstance().getParent();
      pEngine.textSize(24);
      pEngine.textAlign(PConstants.CENTER, PConstants.CENTER);
      pEngine.fill(255);
      pEngine.text("Diver defense", 0, 0);
      pEngine.textSize(12);
      pEngine.textAlign(PConstants.LEFT, PConstants.CENTER);
    }
  }
}
