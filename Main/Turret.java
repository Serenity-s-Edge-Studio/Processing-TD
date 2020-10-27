import g4p_controls.*;

import Green.*;

import processing.core.*;
import java.util.*;
public class Turret extends Actor {
  public static PImage[] sprites;
  public type turretType;
  public int range, damage;
  public float delay, currentDelay, fireRate;
  public int rangeCost, damageCost, fireRateCost, investmentCost;
  public int rangeLevel, damageLevel, fireRateLevel, investmentLevel;
  private Vector2Int hitPosition;
  public enum type {
    Dolphin, 
      Shark
  }
  public Turret(int x, int y, int size, type turretType) {
    super(x + size/2, y+size/2, sprites[turretType.ordinal()], size, size);
    this.turretType = turretType;
    switch(turretType) {
    case Dolphin:
      range = 10*size;
      fireRate = 10;
      delay = 60f/fireRate;
      damage = 20;
      break;
    case Shark:
      range = 5*size;
      fireRate = 120;
      delay = 60f/fireRate;
      damage = 5;
      break;
    }
    rangeCost = 100; damageCost = 100; fireRateCost = 100; investmentCost = 100;
    rangeLevel = 1; damageLevel = 1; fireRateLevel = 1; investmentLevel = 1;
  }
  @Override
  //This function is called every frame by Green
    public void act(float deltaTime) {
    currentDelay = Math.max(0, currentDelay - deltaTime);
    if (currentDelay < .01f) {
      List<Enemy> targets = getObjectsInRange(range, Enemy.class);
      for (Enemy target : targets) {
        if (target != null && !target.isDead) {
          hitPosition = new Vector2Int((int)target.getX(), (int)target.getY());
          target.damage(damage);
          currentDelay = delay;
          break;
        }
      }
    }
  }
  @Override
  public void draw() {
    super.draw();
    Green.getInstance().getParent().resetMatrix();
    if (currentDelay/delay > .8f && hitPosition != null) {
      Green.getInstance().getParent().stroke(255);
      Green.getInstance().getParent().line(getX(), getY(), hitPosition.x, hitPosition.y);
    } else if (currentDelay/delay < .5f) {
      hitPosition = null;
    }
  }
  public int[] getLevels(){
    return new int[]{rangeLevel,fireRateLevel,damageLevel,investmentLevel};
  }
  public int[] getPrices(){
    return new int[]{rangeCost,fireRateCost,damageCost,investmentCost};
  }
  public float[] getValues(){
    return new float[]{range, fireRate, damage, 100/(investmentLevel+1)};
  }
  public void buySpeed(GImageButton imagebutton, GEvent event){
    System.out.println("buySpeedButton - GImageButton >> GEvent." + event + " @ " + Green.getInstance().getParent().millis());
    if (((Level)getWorld()).buy(fireRateCost)){
      fireRate *= 1.2f;
      delay = 60/fireRate;
      fireRateCost *= 1.2f;
      fireRateLevel++;
    }
  }
  public void buyRange(GImageButton imagebutton, GEvent event){
    System.out.println("buyRangeButton - GImageButton >> GEvent." + event + " @ " + Green.getInstance().getParent().millis());
    if (((Level)getWorld()).buy(rangeCost)){
      range *= 1.2f;
      rangeCost *= 1.2f;
      rangeLevel++;
    }
  }
  public void buyDamage(GImageButton imagebutton, GEvent event){
    System.out.println("buyDamageButton - GImageButton >> GEvent." + event + " @ " + Green.getInstance().getParent().millis());
    if (((Level)getWorld()).buy(damageCost)){
      damage *= 1.2f;
      damageCost *= 1.2f;
      damageLevel++;
    }
  }
  public void invest(GImageButton imagebutton, GEvent event){
    System.out.println("buyInvestmentButton - GImageButton >> GEvent." + event + " @ " + Green.getInstance().getParent().millis());
    if (((Level)getWorld()).buy(investmentCost)){
      investmentCost *= 10f;
      investmentLevel++;
      fireRateCost = Math.round(fireRateCost * 1f/investmentLevel);
      rangeCost = Math.round(rangeCost * 1f/investmentLevel);
      damageCost = Math.round(damageCost * 1f/investmentLevel);
    }
  }
}
