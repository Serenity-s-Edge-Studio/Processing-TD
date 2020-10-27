import g4p_controls.*;

import Green.*;
import processing.core.*;
public class Instructions extends Scene {
  GImageButton quitButton;
  public Instructions(Green engine){
    super(engine);
  }
  @Override
    public void prepare() {
    createUI();
    addObject(new Canvas());
  }
  @Override
  public void act(float deltaTime){
  }
  public void createUI(){
    quitButton = new GImageButton(Green.getInstance().getParent(), 512/2 - 60, 468, new String[] { "Exit_Button.png", "Exit_Button.png", "Exit_Button.png" } );
    quitButton.addEventHandler(this, "exitInstructions");
  }
  public void exitInstructions(GImageButton source, GEvent event) {
    System.out.println("exitInstructions - GImageButton >> GEvent." + event + " @ " + Green.getInstance().getParent().millis());
    engine.loadWorld(new MainMenu(engine));
    source.dispose();
  }
  class Canvas extends Actor {
    public Canvas() {
      super(0, 0,512,512);
    }
    @Override
      public void act(float deltaTime) {
    }
    @Override
      public void draw() {
      PApplet pEngine = engine.getParent();
      pEngine.fill(153);
      pEngine.noStroke();
      pEngine.rect(0, 0, 512, 512);
      pEngine.fill(255);
      pEngine.textAlign(PConstants.CENTER, PConstants.CENTER);
      pEngine.textSize(12);
      pEngine.textLeading(12);
      pEngine.text("Diver defense is a 2D tower defense game made\nin the Java Processing environment.\nThe goal of the game is to maximize your score\nmultiplier and get a high score by defending\nthe goal from invading sharks using a\ncombination of two turrets and their upgrades.\nEach turret and upgrades cost money that\nis earned by killing the sharks; the score is\ndetermined by multiplying the base reward\n($100 or 100 points) times the score multiplier,\n which is reset to 1 each time a shark reaches\nthe goal.The score multiplier is calculated\nby multiplying the score multiplier by 1.2x each time\na new wave starts and by 1.001 each time a shark is killed.\n\nThere are two interactive worlds in Diver Defense: \nthe level editor and the game screen. The level editor\nallows you to draw a custom map using the predefined\ntile types (Goal = red, Spawn = green, Turret mount = grey,\nPath tile = cobblestone, Empty tile = sand).\n The created map can then be saved and loaded into the game screen.\nThe game screen is where the actual game is played. Once you\nload in the map you start with $100, which is just enough\nto buy a single dolphin turret. When you are comfortable with\nthe defenses you have placed, you can click the \"Start Wave\"\n button to begin the assault. Each shark killed\nawards the player with $100; \nwhen the player has enough money, they may buy more\nturrets or upgrade existing ones. An upgrade that makes the\ngame easier is the investment upgrades; \nthese allow the player to slash the prices of other upgrades\n(with diminishing returns).", 512/2, 512/2);
      pEngine.textAlign(PConstants.LEFT, PConstants.TOP);
      pEngine.textSize(8);
      pEngine.textLeading(12);
    }
  }
}
