import Green.*;

import processing.core.*;
import java.util.*;
public class Turret extends Actor {
  public static PImage[] sprites;
  public type turretType;
  public int range, damage;
  public float delay, currentDelay, fireRate;
  public int rangeCost, damageCost, fireRateCost, investmentCost; //<>//
  public int rangeLevel, damageLevel, fireRateLevel, investmentLevel; //<>//
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
    return new int[]{rangeLevel,damageLevel,fireRateLevel,investmentLevel};
  }
  public int[] getPrices(){
    return new int[]{rangeCost,damageCost,fireRateCost,investmentCost};
  }
  public float[] getValues(){
    return new float[]{range, damage, fireRateCost, 100/(investmentLevel+1)};
  }
  public void buySpeed(){
    if (((Level)getWorld()).buy(fireRateCost)){
      fireRate *= 1.2f;
      delay = 60/fireRate;
      fireRateCost *= 1.2f;
      fireRateLevel++;
    }
  }
  public void buyRange(){
    if (((Level)getWorld()).buy(rangeCost)){
      range *= 1.2f;
      rangeCost *= 1.2f;
      rangeLevel++;
    }
  }
  public void buyDamage(){
    if (((Level)getWorld()).buy(damageCost)){
      damage *= 1.2f;
      damageCost *= 1.2f;
      damageLevel++;
    }
  }
  public void invest(){
    if (((Level)getWorld()).buy(investmentCost)){
      investmentCost *= 10f;
      investmentLevel++;
      fireRateCost *= 1/investmentLevel;
      rangeCost *= 1/investmentLevel;
      damageCost *= 1/investmentLevel;
    }
  }
}
