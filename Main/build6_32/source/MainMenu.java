import processing.sound.*;

import g4p_controls.*;

import Green.*;
import processing.core.*;
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
    setBackgroundColor(0, 0, 0);
    createGUI();
    MusicManager.init();
    MusicManager.playMenu();
  }
  @Override
    public void act(float deltaTime) {
  }
  public void loadGame(GImageButton source, GEvent event) {
    System.out.println("imgButton1 - GImageButton >> GEvent." + event + " @ " + engine.getParent().millis());
    System.out.println("Load Game");
    disposeUI();
    engine.loadWorld(new Level(engine, Grid.fromJSON(engine.getParent().loadJSONObject("saves/test.json"))));
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
    playButton = new GImageButton(engine.getParent(), 204, 108, new String[] { "Start_Button.png", "Start_Button.png", "Start_Button.png" } );
    playButton.addEventHandler(this, "loadGame");
    instructionsButton = new GImageButton(engine.getParent(), 204, 256, new String[] { "Instructions_Button.png", "Instructions_Button.png", "Instructions_Button.png" } );
    instructionsButton.addEventHandler(this, "instructionScene");
    quitButton = new GImageButton(engine.getParent(), 204, 348, new String[] { "Exit_Button.png", "Exit_Button.png", "Exit_Button.png" } );
    quitButton.addEventHandler(this, "exitGame");
    editorButton = new GImageButton(engine.getParent(), 204, 179, new String[] { "Map_Editor_Button.png", "Map_Editor_Button.png", "Map_Editor_Button.png" } );
    editorButton.addEventHandler(this, "launchEditor");
  }
  public void disposeUI() {
    playButton.dispose();
    instructionsButton.dispose();
    quitButton.dispose();
    editorButton.dispose();
  }
}
