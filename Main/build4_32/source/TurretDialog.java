import Green.*;

import g4p_controls.*;

public class TurretDialog extends Actor {
  public static TurretDialog instance = null;
  GImageButton dolphinButton; 
  GImageButton sharkButton; 
  GLabel title; 
  GLabel dolphin1desc; 
  GLabel sharkDesc;
  int x, y;
  Tile tile;
  Vector2Int tl,br;
  public TurretDialog(int x, int y, Tile tile) {
    super(x, y, 100, 100);
    this.x = x;
    this.y = y;
    this.tile = tile;
    tl = new Vector2Int(x,y);
    br = new Vector2Int(x + 100, y + 100);
    createUI();
  }
  @Override
    public void act(float deltaTime) {
  }
  public boolean pointInBounds(int x, int y){
    if (x >= tl.x && y >= tl.y && x <= br.x && y <= br.y)
      return true;
    return false;
  }
  @Override
    public void addedToWorld(World world) {
    if (instance != null && instance != this) {
      ((Level)getWorld()).removeObject(instance);
    }
    instance = this;
  }
  @Override
  public void removedFromWorld(World world){
    dispose();
  }
  @Override
  public void draw(){
    Green.getInstance().getParent().fill(153);
    Green.getInstance().getParent().square(0,0,100);
  }
  private void createUI() {
    dolphinButton = new GImageButton(Green.getInstance().getParent(), 0 + getX(), 20 + getY(), 32, 32, new String[] { "Dolphin_Turret.png", "Dolphin_Turret.png", "Dolphin_Turret.png" } );
    dolphinButton.addEventHandler(this, "selectDolphin");
    sharkButton = new GImageButton(Green.getInstance().getParent(), 60 + x, 20 + y, 32, 32, new String[] { "Shark_Turret.png", "Shark_Turret.png", "Shark_Turret.png" } );
    sharkButton.addEventHandler(this, "selectShark");
    title = new GLabel(Green.getInstance().getParent(), 0 + x, 0 + y, 100, 20);
    title.setTextAlign(GAlign.CENTER, GAlign.MIDDLE);
    title.setText("Buy turret");
    title.setOpaque(false);
    G4P.setDisplayFont("Arial", G4P.PLAIN, 6);
    dolphin1desc = new GLabel(Green.getInstance().getParent(), 0 + x, 60 + y, 60, 40);
    dolphin1desc.setTextAlign(GAlign.CENTER, GAlign.MIDDLE);
    dolphin1desc.setText("Dolphin turret:\nSpeed: 1\nDamage:20\nCost:100");
    dolphin1desc.setOpaque(false);
    sharkDesc = new GLabel(Green.getInstance().getParent(), 60 + x, 60 + y, 40, 40);
    sharkDesc.setTextAlign(GAlign.CENTER, GAlign.MIDDLE);
    sharkDesc.setText("Shark turret:\nSpeed: 10\nDamage:5\nCost:1000");
    sharkDesc.setOpaque(false);
    G4P.setDisplayFont("Arial", G4P.PLAIN, 12);
  }
  public void selectDolphin(GImageButton source, GEvent event) { //_CODE_:dolphinButton:204492:
    System.out.println("imgButton2 - GImageButton >> GEvent." + event + " @ " + Green.getInstance().getParent().millis());
    Vector2Int position = ((Level)getWorld()).map.scalePosition(new Vector2Int(tile.x, tile.y));
    getWorld().addObject(new Turret(position.x, position.y, ((Level)getWorld()).map.getTileLength(), Turret.type.Dolphin));
    getWorld().removeObject(this);
    instance = null;
  } //_CODE_:dolphinButton:204492:

  public void selectShark(GImageButton source, GEvent event) { //_CODE_:sharkButton:544988:
    System.out.println("imgButton3 - GImageButton >> GEvent." + event + " @ " + Green.getInstance().getParent().millis());
  } //_CODE_:sharkButton:544988:
  public void dispose() {
    dolphinButton.dispose(); 
    sharkButton.dispose(); 
    title.dispose(); 
    dolphin1desc.dispose(); 
    sharkDesc.dispose();
  }
}
