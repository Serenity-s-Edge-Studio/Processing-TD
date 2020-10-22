import shiffman.box2d.*;

import Green.*;

import g4p_controls.*;

public Green engine;
private MainMenu menu;
void setup()
{
  size(512, 512);
  print("Starting game engine");
  engine = new Green(this);
  menu = new MainMenu(engine);
  engine.loadWorld(menu);
  loadTileResources();
}
void draw() {
  background(51);
  engine.handleAct();
  engine.handleDraw();
  engine.handleInput();
}
void mousePressed() {
  engine.handleMouseDown(mouseButton);
}
void mouseReleased() {
  engine.handleMouseUp(mouseButton);
}
void mouseMoved() {
  engine.handleMousePosition(pmouseX, pmouseY, mouseX, mouseY);
}
void keyPressed() {
  engine.handleKeyDown(key, keyCode);
}
void keyReleased() {
  engine.handleKeyUp(key, keyCode);
}
void loadTileResources() {
  Tile.sprites = new PImage[5];
  Tile.sprites[0] = loadImage("Path_Tile.png");
  Tile.sprites[1] = loadImage("Sand Tile.png");
  Enemy.sprites = new PImage[1];
  Enemy.sprites[0] = loadImage("Enemy_Shark.png");
  Turret.sprites = new PImage[2];
  Turret.sprites[0] = loadImage("Dolphin_Turret.png");
  Turret.sprites[1] = loadImage("Shark_Turret.png");
}
