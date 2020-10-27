import shiffman.box2d.*;

import g4p_controls.*;

import Green.*;

import processing.core.*;
//Enemy class, these is the base class for any shark
public class Enemy extends Actor {
  //Members
  public static PImage[] sprites;
  Vector2Int[] path;
  Level level;
  int targetIndex = 1;
  float currentDistance;
  float progress = 0;
  float speed = 20;
  float health = 20;
  public boolean isDead = false;
  PApplet pEngine;
  public Enemy(float x, float y, PImage sprite, int w, int h, Vector2Int[] path, Level level, float multiplier) {
    super(x, y, sprite, w, h);
    this.path = path;
    this.pEngine = Green.getInstance().getParent();
    this.currentDistance = Vector2Int.distance(path[targetIndex-1], path[targetIndex]);
    this.level = level;
    //calculate the speed and health based on curret difficulty modifier
    this.speed = this.speed * (float)(Math.random() * multiplier + .5f);
    this.health = this.health * (float)(Math.random() * multiplier + .5f);
  }
  //This function is called every frame by Green
  public void act(float deltaTime) {
    //calculate the lerp percentage between my next waypoint.
    float currentFraction = progress + (deltaTime * speed)/currentDistance;
    while (currentFraction - 1 > .01f && targetIndex < path.length) {
      currentDistance = Vector2Int.distance(path[targetIndex-1], path[targetIndex]);
      targetIndex++;
      currentFraction -= 1;
    }
    //If we have reached the goal kill this enemy and punish the player.
    if (targetIndex == path.length) {
      ((Level)getWorld()).goalReached(this);
      isDead = true;
      return;
    }
    //Calculate next waypoint
    Vector2Int lastPos = path[targetIndex-1];
    Vector2Int targetPos = path[targetIndex];
    int posOffset = Grid.singleton.getTileLength()/2;
    //move to desiered position
    setLocation(pEngine.lerp(lastPos.x + posOffset, targetPos.x+ posOffset, currentFraction), pEngine.lerp(lastPos.y+ posOffset, targetPos.y+ posOffset, currentFraction));
    progress = currentFraction;
  }
  //This function handles damage
  public void damage(int amount) {
    health = Math.max(0, health - amount);
    //If health == 0 kill the enemy and reward the player
    if (health == 0 && !isDead) {
      isDead = true;
      ((Level)getWorld()).enemyKilled(this);
    }
  }
}
