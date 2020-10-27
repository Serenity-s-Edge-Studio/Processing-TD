import Green.*;

import g4p_controls.*;
import java.awt.*;
import processing.core.*;

public class TurretDialog extends Actor {
  public static TurretDialog instance = null;
  GImageButton dolphinButton; 
  GImageButton sharkButton; 
  GLabel title; 
  GLabel dolphin1desc; 
  GLabel sharkDesc;
  int x, y;
  Tile tile;
  Turret selectedTurret;
  Vector2Int tl, br;

  GImageButton buySpeedButton;
  GImageButton buyRangeButton;
  GImageButton buyDamageButton;
  GImageButton investButton;
  public TurretDialog(int x, int y, Tile tile) {
    super(x, y, 140, 140);
    this.x = x;
    this.y = y;
    this.tile = tile;
    tl = new Vector2Int(x, y);
    br = new Vector2Int(x + getWidth(), y + getHeight());
    setZ(2);
  }
  public TurretDialog(int x, int y, Tile tile, Turret turret) {
    this(x, y, tile);
    this.selectedTurret = turret;
  }
  @Override
  //This function is called every frame by Green
    public void act(float deltaTime) {
  }
  public boolean pointInBounds(int x, int y) {
    if (x >= tl.x && y >= tl.y && x <= br.x && y <= br.y)
      return true;
    return false;
  }
  @Override
    public void addedToWorld(World world) {
    if (instance != null && instance != this) {
      ((Level)getWorld()).removeObject(instance);
    }
    createUI();
    instance = this;
  }
  @Override
    public void removedFromWorld(World world) {
    dispose();
  }
  @Override
    public void draw() {
    PApplet pEngine = Green.getInstance().getParent();
    pEngine.stroke(255);
    pEngine.fill(153);
    pEngine.square(0, 0, getWidth());
    pEngine.fill(255);
    pEngine.textAlign(PConstants.CENTER, PConstants.CENTER);
    if (selectedTurret != null) {
      pEngine.text("Upgrade turret", getWidth()/2, 10);
      pEngine.textAlign(PConstants.LEFT, PConstants.TOP);
      pEngine.textSize(8);
      pEngine.textLeading(17);
      pEngine.text(selectedTurret.turretType.toString() + "\nRange:\nSpeed:\nDamage:\nInvestment:", 0, 60);
      int[] levels = selectedTurret.getLevels();
      pEngine.textAlign(PConstants.CENTER, PConstants.TOP);
      pEngine.text("Levels:\n" + levels[0] + "\n" + levels[1] + "\n" + levels[2] + "\n" + levels[3], 45, 60);
      float[] values = selectedTurret.getValues();
      pEngine.text("Values:\n" + values[0] + "\n" + values[1] + "\n" + values[2] + "\n" + values[3] + "%", 75, 60);
      int[] prices = selectedTurret.getPrices();
      pEngine.text("Cost:\n" + prices[0] + "\n" + prices[1] + "\n" + prices[2] + "\n" + prices[3], 105, 60);
    } else {
      pEngine.text("Buy turret", 60, 10);
      pEngine.textAlign(PConstants.LEFT, PConstants.TOP);
      pEngine.textSize(8);
      pEngine.textLeading(12);
      pEngine.text("Dolphin turret:\nRange: 10\nSpeed: 10/s\nDamage:20\nCost:100", 0, 60);
      pEngine.text("Shark turret:\nRange: 5\nSpeed: 120/s\nDamage:5\nCost:1000", getWidth()/2, 60);
    }
    pEngine.textSize(12);
    pEngine.textAlign(PConstants.LEFT, PConstants.BASELINE);
  }
  private void createUI() {
    if (selectedTurret == null) {
      dolphinButton = new GImageButton(Green.getInstance().getParent(), x - getWidth()/8 + 32, 20 + getY(), 32, 32, new String[] { "Dolphin_Turret.png", "Dolphin_Turret.png", "Dolphin_Turret.png" } );
      dolphinButton.addEventHandler(this, "selectDolphin");
      sharkButton = new GImageButton(Green.getInstance().getParent(), x + getWidth() - 32 - getWidth()/8, 20 + y, 32, 32, new String[] { "Shark_Turret.png", "Shark_Turret.png", "Shark_Turret.png" } );
      sharkButton.addEventHandler(this, "selectShark");
    } else {
      buyRangeButton = new GImageButton(Green.getInstance().getParent(), getX() + getWidth() - 15, getY() + 60 + 16, 10, 10, new String[] { "Buy_Button.png", "Buy_Button.png", "Buy_Button.png" } );
      buyRangeButton.addEventHandler(selectedTurret, "buyRange");
      buySpeedButton = new GImageButton(Green.getInstance().getParent(), getX() + getWidth() - 15, getY() + 60 + 16 * 2, 10, 10, new String[] { "Buy_Button.png", "Buy_Button.png", "Buy_Button.png" } );
      buySpeedButton.addEventHandler(selectedTurret, "buySpeed");
      buyDamageButton = new GImageButton(Green.getInstance().getParent(), getX() + getWidth() - 15, getY() + 60 + 16 * 3, 10, 10, new String[] { "Buy_Button.png", "Buy_Button.png", "Buy_Button.png" } );
      buyDamageButton.addEventHandler(selectedTurret, "buyDamage");
      investButton = new GImageButton(Green.getInstance().getParent(), getX() + getWidth() - 15, getY() + 60 + 16 * 4, 10, 10, new String[] { "Buy_Button.png", "Buy_Button.png", "Buy_Button.png" } );
      investButton.addEventHandler(selectedTurret, "invest");
    }
  }
  public void selectDolphin(GImageButton source, GEvent event) { //_CODE_:dolphinButton:204492:
    System.out.println("imgButton2 - GImageButton >> GEvent." + event + " @ " + Green.getInstance().getParent().millis());
    Vector2Int position = ((Level)getWorld()).map.scalePosition(new Vector2Int(tile.x, tile.y));
    Turret bought = new Turret(position.x, position.y, ((Level)getWorld()).map.getTileLength(), Turret.type.Dolphin);
    if (((Level)getWorld()).addTurret(bought)) {
      getWorld().addObject(bought);
      getWorld().removeObject(this);
      instance = null;
    }
  } //_CODE_:dolphinButton:204492:

  public void selectShark(GImageButton source, GEvent event) { //_CODE_:sharkButton:544988:
    System.out.println("imgButton3 - GImageButton >> GEvent." + event + " @ " + Green.getInstance().getParent().millis());
    Vector2Int position = ((Level)getWorld()).map.scalePosition(new Vector2Int(tile.x, tile.y));
    Turret bought = new Turret(position.x, position.y, ((Level)getWorld()).map.getTileLength(), Turret.type.Shark);
    if (((Level)getWorld()).addTurret(bought)) {
      getWorld().addObject(bought);
      getWorld().removeObject(this);
      instance = null;
    }
  } //_CODE_:sharkButton:544988:
  public void dispose() {
    if (dolphinButton != null) 
      dolphinButton.dispose(); 
    if (sharkButton != null) 
      sharkButton.dispose();
    if (buySpeedButton != null) 
      buySpeedButton.dispose();
    if (buyRangeButton != null) 
      buyRangeButton.dispose();
    if (buyDamageButton != null) 
      buyDamageButton.dispose();
    if (investButton != null) 
      investButton.dispose();
  }
}
