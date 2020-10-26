import g4p_controls.*;

import Green.*;
import processing.core.*;
import java.util.*;
import java.text.*;
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
  float currentScoreMultiplier = 1;
  int score = 0;
  int money = 100;
  List<Wave> activeWaves = new LinkedList<Wave>();
  HashMap<Vector2Int, Turret> turrets = new HashMap<Vector2Int, Turret>();
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
    addObject(new Canvas());
    createUI();
    MusicManager.levelMusic();
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
        Turret existingTurret = turrets.get(new Vector2Int(selectedTile.x, selectedTile.y));
        if (existingTurret != null)
          ShowTurretOptions(selectedTile, existingTurret);
        else
          ShowTurretOptions(selectedTile);
      } else if (TurretDialog.instance != null) {
        removeObject(TurretDialog.instance);
        TurretDialog.instance = null;
      }
    }
  }
  private void ShowTurretOptions(Tile selected) {
    Vector2Int position = map.scalePosition(new Vector2Int(selected.x, selected.y));
    position.x += map.getTileLength();
    addObject(new TurretDialog(position.x, position.y, selected));
  }
  private void ShowTurretOptions(Tile selected, Turret existingTurret) {
    Vector2Int position = map.scalePosition(new Vector2Int(selected.x, selected.y));
    position.x += map.getTileLength();
    addObject(new TurretDialog(position.x, position.y, selected, existingTurret));
  }
  public void startWave(GImageButton source, GEvent event) {
    System.out.println("startWaveButton - GImageButton >> GEvent." + event + " @ " + Green.getInstance().getParent().millis());
    if (path == null)
      path = map.getPathScaled();
    startWave(true);
    MusicManager.playSharkOnce();
  }
  public void startWave(boolean triggeredByUser) {
    waveSize *= currentWaveMultiplier;
    currentWaveMultiplier *= waveMultiplier;
    currentScoreMultiplier *= waveMultiplier;
    activeWaves.add(new Wave(1/waveMultiplier, waveSize, triggeredByUser? 0 : (float)Math.random() * 10 + 1));
  }

  public void quitLevel(GImageButton source, GEvent event) {
    System.out.println("quitLevel - GImageButton >> GEvent." + event + " @ " + Green.getInstance().getParent().millis());
    engine.loadWorld(new MainMenu(engine));
    MusicManager.clearQueue();
    disposeUI();
  }
  private void createUI() {
    startWaveButton = new GImageButton(Green.getInstance().getParent(), 383, 468, new String[] { "Start_Wave_Button.png", "Start_Wave_Button.png", "Start_Wave_Button.png" } );
    startWaveButton.addEventHandler(this, "startWave");
    quitLevelButton = new GImageButton(Green.getInstance().getParent(), 0, 468, new String[] { "Exit_Button.png", "Exit_Button.png", "Exit_Button.png" } );
    quitLevelButton.addEventHandler(this, "quitLevel");
  }
  void disposeUI() {
    startWaveButton.dispose(); 
    quitLevelButton.dispose();
    if (TurretDialog.instance != null)
      TurretDialog.instance.dispose();
  }
  public void goalReached(Enemy enemy) {
    removeObject(enemy);
    currentScoreMultiplier = 1;
    remainingEnemies--;
  }
  public void enemyKilled(Enemy enemy) {
    removeObject(enemy);
    remainingEnemies--;
    money += 100;
    score += 100 * currentScoreMultiplier;
    currentScoreMultiplier *= 1.001f;
  }
  public boolean buy(int cost) {
    if (cost <= money) {
      money -= cost;
      return true;
    }
    return false;
  }
  public boolean addTurret(Turret newTurret) {
    int cost;
    switch (newTurret.turretType) {
    case Dolphin:
      cost = 100;
      break;
    case Shark:
      cost = 1000;
      break;
    default: 
      cost = 100;
      break;
    }
    Tile tile = map.getTileAtPosition(Math.round(newTurret.getX()), Math.round(newTurret.getY()));

    return cost <= money && turrets.putIfAbsent(new Vector2Int(tile.x, tile.y), newTurret) == null && buy(cost);
  }
  class Wave {
    public int remaining;
    private float delay;
    private float currentDelay;
    private boolean started = false;
    public Wave(float spawnDelay, int amount, float period) {
      remaining = amount;
      delay = spawnDelay;
      currentDelay = period;
    }
    public void tick(float deltaTime) {
      currentDelay = Math.max(0, currentDelay - deltaTime * ((float)Math.random()*2 + .5f));
      if (currentDelay < 0.1f) {
        if (!started) {
          started = true; 
          waveNumber++;
        }
        addObject(new Enemy(path[0].x, path[0].y, Enemy.sprites[0], map.getTileLength(), map.getTileLength(), path, Level.this, currentWaveMultiplier));
        remainingEnemies++;
        remaining--;
        currentDelay = delay;
      }
    }
  }
  class Canvas extends Actor {
    public Canvas() {
      super(0, 0, 512, 512);
    }
    @Override
      public void act(float deltaTime) {
    }
    @Override
      public void draw() {
      PApplet pEngine = engine.getParent();
      pEngine.fill(153);
      pEngine.noStroke();
      pEngine.rect(0, 0, 512, map.getMarginY());
      pEngine.fill(255);
      DecimalFormat formatter = new DecimalFormat("#.00");
      pEngine.text("Score: " + score, 0, map.getMarginY()/2);
      pEngine.text("Score multiplier: x" + formatter.format(currentScoreMultiplier), 80, map.getMarginY()/2);
      pEngine.text("Difficulty multiplier: x" + formatter.format(currentWaveMultiplier), 225, map.getMarginY()/2);
      pEngine.text("Money: $" + money, 425, map.getMarginY()/2);
      pEngine.fill(153);
      pEngine.rect(0, 513-map.getMarginY(), 512, 512);
      pEngine.fill(255);
      pEngine.text("Remaining enemies: " + remainingEnemies, 128, 513-map.getMarginY()/2);
      pEngine.text("Wave count: " + waveNumber, 275, 513-map.getMarginY()/2);
    }
  }
}
