import Green.*;

import processing.core.*;
public class Turret extends Actor{
  public static PImage[] sprites;
  public type turretType;
  public int range, damage;
  public float delay, currentDelay;
  private Vector2Int hitPosition;
  public enum type{
    Dolphin,
    Shark
  }
  public Turret(int x, int y, int size, type turretType){
    super(x + size/2,y+size/2,sprites[turretType.ordinal()],size,size);
    this.turretType = turretType;
    switch(turretType){
      case Dolphin:
        range = 10*size;
        delay = 60/10f;
        damage = 20;
        break;
      case Shark:
        range = 5*size;
        delay = 60/120f;
        damage = 5;
        break;
    }
  }
  @Override
  public void act(float deltaTime){
    currentDelay = Math.max(0, currentDelay - deltaTime);
    if (currentDelay < .01f){
      Enemy target = getOneObjectInRange(range, Enemy.class);
      if (target != null){
        hitPosition = new Vector2Int((int)target.getX(), (int)target.getY());
        target.damage(damage);
        currentDelay = delay;
      }
    }
  }
  @Override
  public void draw(){
    super.draw();
    Green.getInstance().getParent().resetMatrix();
    if (currentDelay/delay > .8f && hitPosition != null){
      Green.getInstance().getParent().line(getX(), getY(), hitPosition.x, hitPosition.y);
    }else if(currentDelay/delay < .5f){
      hitPosition = null;
    }
  }
}
