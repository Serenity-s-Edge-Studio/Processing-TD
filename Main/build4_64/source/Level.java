import g4p_controls.*;

import Green.*;
import processing.core.*;
import java.util.*;
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

  Vector2Int[] path;
  int remainingEnemies = 0;
  int waveSize = 5;
  int waveNumber = 0;
  final float waveMultiplier = 1.1f;
  float currentWaveMultiplier = 1;
  List<Wave> activeWaves = new LinkedList<Wave>();
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
    List<Wave> wavesToRemove = new LinkedList<Wave>();
    for (Wave w : activeWaves) {
      w.tick(deltaTime);
      if (w.remaining == 0) {
        wavesToRemove.add(w);
      }
    }
    for (Wave r : wavesToRemove) {
      activeWaves.remove(r);

      if (activeWaves.size() == 0)
        startWave(false);
    }
    PApplet pEngine = Green.getInstance().getParent();
    if (engine.isMouseButtonDown(PConstants.LEFT)) {
      if (TurretDialog.instance != null && TurretDialog.instance.pointInBounds(pEngine.mouseX, pEngine.mouseY))
        return;
      Tile selectedTile = map.getTileAtPosition(pEngine.mouseX, pEngine.mouseY);
      if (selectedTile != null && selectedTile.tileType == Tile.type.Mount) {
        ShowTurretOptions(selectedTile);
      }else if (TurretDialog.instance != null){
        removeObject(TurretDialog.instance);
        TurretDialog.instance = null;
      }
    }
  }
  private void ShowTurretOptions(Tile selected){
    Vector2Int position = map.scalePosition(new Vector2Int(selected.x, selected.y));
    position.x += map.getTileLength();
    addObject(new TurretDialog(position.x, position.y, selected));
  }
  public void startWave(GImageButton source, GEvent event) {
    System.out.println("startWaveButton - GImageButton >> GEvent." + event + " @ " + Green.getInstance().getParent().millis());
    if (path == null)
      path = map.getPathScaled();
    startWave(true);
  }
  public void startWave(boolean triggeredByUser) {
    waveSize *= currentWaveMultiplier;
    currentWaveMultiplier *= waveMultiplier;
    activeWaves.add(new Wave(1/waveMultiplier, waveSize, triggeredByUser? 0 : (float)Math.random() * 10 + 1));
    waveNumber++;
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
  class Wave {
    public int remaining;
    private float delay;
    private float currentDelay;
    public Wave(float spawnDelay, int amount, float period) {
      remaining = amount;
      delay = spawnDelay;
      currentDelay = period;
    }
    public void tick(float deltaTime) {
      currentDelay = Math.max(0, currentDelay - deltaTime * ((float)Math.random()*2 + .5f));
      if (currentDelay < 0.1f) {
        addObject(new Enemy(path[0].x, path[0].y, Enemy.sprites[0], map.getTileLength(), map.getTileLength(), path, Level.this));
        remainingEnemies++;
        remaining--;
        currentDelay = delay;
      }
    }
  }
  
}
