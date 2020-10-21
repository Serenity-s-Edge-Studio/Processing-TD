import g4p_controls.*;

import Green.*;
import processing.core.*;
public class Level extends Scene {
  public Grid map;
  //UI variables
  GImageButton startWaveButton; 
  GLabel waveCount; 
  GLabel enemyCount; 
  GImageButton quitLevelButton; 
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
    setBackgroundColor(0, 0, 0);
  }
  @Override
  public void act(float deltaTime) {
    
  }
  public void startWave(GImageButton source, GEvent event) {
    System.out.println("startWaveButton - GImageButton >> GEvent." + event + " @ " + Green.getInstance().getParent().millis());
    Vector2Int[] path = map.getPathScaled();
    if (path != null)
      addObject(new Enemy(path[0].x, path[0].y, Enemy.sprites[0], map.getTileLength(), map.getTileLength(), path, this));
  }

  public void quitLevel(GImageButton source, GEvent event) {
    System.out.println("quitLevel - GImageButton >> GEvent." + event + " @ " + Green.getInstance().getParent().millis());
    engine.loadWorld(new MainMenu(engine));
    disposeUI();
  }
  private void createUI() {
    startWaveButton = new GImageButton(Green.getInstance().getParent(), 383, 468, new String[] { "Start_Wave_Button.png", "Start_Wave_Button.png", "Start_Wave_Button.png" } );
    startWaveButton.addEventHandler(this, "startWave");
    waveCount = new GLabel(Green.getInstance().getParent(), 276, 473, 107, 40);
    waveCount.setText("Wave number:");
    waveCount.setOpaque(false);
    waveCount.setTextBold();
    enemyCount = new GLabel(Green.getInstance().getParent(), 128, 470, 146, 40);
    enemyCount.setText("Remaining enemies: ");
    enemyCount.setOpaque(false);
    enemyCount.setTextBold();
    quitLevelButton = new GImageButton(Green.getInstance().getParent(), 0, 468, new String[] { "Exit_Button.png", "Exit_Button.png", "Exit_Button.png" } );
    quitLevelButton.addEventHandler(this, "quitLevel");
    scoreText = new GLabel(Green.getInstance().getParent(), 0, 0, 80, 40);
    scoreText.setText("Score: 0");
    scoreText.setOpaque(false);
    scoreText.setTextBold();
    moneyText = new GLabel(Green.getInstance().getParent(), 431, 0, 80, 40);
    moneyText.setTextAlign(GAlign.CENTER, GAlign.MIDDLE);
    moneyText.setText("Money: $0");
    moneyText.setTextBold();
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
    quitLevelButton.dispose(); 
    scoreText.dispose(); 
    moneyText.dispose(); 
    multiplierText.dispose();
  }
}
