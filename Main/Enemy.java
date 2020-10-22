import shiffman.box2d.*;

import g4p_controls.*;

import Green.*;

import processing.core.*;

public class Enemy extends Actor{
  public static PImage[] sprites;
  Vector2Int[] path;
  Level level;
  int targetIndex = 1;
  float currentDistance;
  float progress = 0;
  float speed = 20;
  float health = 20;
  PApplet pEngine;
  public Enemy(float x, float y, PImage sprite, int w, int h, Vector2Int[] path, Level level, float multiplier){
    super(x,y,sprite, w,h);
    this.path = path;
    this.pEngine = Green.getInstance().getParent();
    this.currentDistance = Vector2Int.distance(path[targetIndex-1], path[targetIndex]);
    this.level = level;
    this.speed = this.speed * (float)(Math.random() * multiplier + .5f);
    this.health = this.health * (float)(Math.random() * multiplier + .5f);
  }
  public void act(float deltaTime){
    float currentFraction = progress + (deltaTime * speed)/currentDistance;
    if (currentFraction - 1 > .01f){
      if (targetIndex < path.length - 1){
      targetIndex++;
      currentDistance = Vector2Int.distance(path[targetIndex-1], path[targetIndex]);
      currentFraction -= 1;
      }else{
        goalReached();
      }
    }
    Vector2Int lastPos = path[targetIndex-1];
    Vector2Int targetPos = path[targetIndex];
    int posOffset = Grid.singleton.getTileLength()/2;
    setLocation(pEngine.lerp(lastPos.x + posOffset, targetPos.x+ posOffset, currentFraction), pEngine.lerp(lastPos.y+ posOffset, targetPos.y+ posOffset, currentFraction));
    progress = currentFraction;
  }
  private void goalReached(){
    getWorld().removeObject(this);
  }
  public void damage(int amount){
    health = Math.max(0, health - amount);
    if (health == 0){
    getWorld().removeObject(this);
    }
  }
}
