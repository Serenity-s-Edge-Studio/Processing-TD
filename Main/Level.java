import g4p_controls.*;

import Green.*;
import processing.core.*;
public class Level extends Scene {
  public Grid map;
  GImageButton startWaveButton; 
  GLabel waveCount; 
  GLabel enemyCount; 
  GImageButton imgButton1; 
  GLabel scoreText; 
  GLabel moneyText; 
  GLabel multiplierText; 
  public Level(Green engine) {
    super(engine);
  }
  public Level(Green engine, Grid map) {
    super(engine);
    this.map = map;
  }
  @Override
    public void prepare() {
    if (map == null)
      map = new Grid(50, 50, 25, engine.getParent().sketchWidth(), engine.getParent().sketchHeight());
    addObject(map);
    createUI();
  }
  @Override
    public void act(float deltaTime) {
    PApplet pEngine = Green.getInstance().getParent();
    //System.out.println("MouseX: " + pEngine.mouseX + " MouseY: " + pEngine.mouseY);
    pEngine.background(51);
  }
  public void startWave(GImageButton source, GEvent event) {
    System.out.println("startWaveButton - GImageButton >> GEvent." + event + " @ " + Green.getInstance().getParent().millis());
  }

  public void quitLevel(GImageButton source, GEvent event) {
    System.out.println("imgButton1 - GImageButton >> GEvent." + event + " @ " + Green.getInstance().getParent().millis());
  }
  private void createUI() {
    startWaveButton = new GImageButton(Green.getInstance().getParent(), 383, 468, new String[] { "Start_Wave_Button.png", "Start_Wave_Button.png", "Start_Wave_Button.png" } );
    startWaveButton.addEventHandler(this, "startWave");
    waveCount = new GLabel(Green.getInstance().getParent(), 276, 473, 107, 40);
    waveCount.setText("Wave number:");
    waveCount.setOpaque(false);
    enemyCount = new GLabel(Green.getInstance().getParent(), 128, 470, 146, 40);
    enemyCount.setText("Remaining enemies: ");
    enemyCount.setOpaque(false);
    imgButton1 = new GImageButton(Green.getInstance().getParent(), 0, 468, new String[] { "Exit_Button.png", "Exit_Button.png", "Exit_Button.png" } );
    imgButton1.addEventHandler(this, "quitLevel");
    scoreText = new GLabel(Green.getInstance().getParent(), 0, 0, 80, 40);
    scoreText.setText("Score: 0");
    scoreText.setOpaque(false);
    moneyText = new GLabel(Green.getInstance().getParent(), 431, 0, 80, 40);
    moneyText.setTextAlign(GAlign.CENTER, GAlign.MIDDLE);
    moneyText.setText("Money: $0");
    moneyText.setOpaque(false);
    multiplierText = new GLabel(Green.getInstance().getParent(), 80, 0, 128, 40);
    multiplierText.setText("Score multiplier: x1.0");
    multiplierText.setTextBold();
    multiplierText.setOpaque(false);
  }
  void disposeUI() {
    startWaveButton.dispose(); 
    waveCount.dispose(); 
    enemyCount.dispose(); 
    imgButton1.dispose(); 
    scoreText.dispose(); 
    moneyText.dispose(); 
    multiplierText.dispose();
  }
}
