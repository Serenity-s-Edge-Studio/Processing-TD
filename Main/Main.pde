import shiffman.box2d.*;

import Green.*;

import g4p_controls.*;

public Green engine;

private MainMenu menu;
void setup()
{
  size(512, 512);
  credits();
  smooth(8);
  //load custom font
  textFont(createFont("raidercrusader.ttf", 12, true));
  //initialise the game engine
  engine = new Green(this);
  //create MainMenu scene
  menu = new MainMenu(engine);
  //load the MainMenu scene
  engine.loadWorld(menu);
  loadImageResources();
}
//Main game loop
void draw() {
  background(173, 216, 230);
  //call each of greens' game loops.
  engine.handleAct();//update
  engine.handleDraw();//draw loop
  engine.handleInput();//register all frame-by-frame input changes
}
//event handler pipes processings' input events to green's own event handler
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
//prints credits to the console
void credits(){
  println("Diver defense: a tower defense game made in Processing");
  println("created by: serenity edge studios:");
  println("Sam Kessler, MarkAllman, meganmcnamee, Dillweeddude");
  println("Starting game engine");
}
//This function loads all the necessary graphics resources
void loadImageResources() {
  Tile.sprites = new PImage[5];
  Tile.sprites[0] = loadImage("Path_Tile.png");
  Tile.sprites[1] = loadImage("Sand Tile.png");
  Enemy.sprites = new PImage[1];
  Enemy.sprites[0] = loadImage("Enemy_Shark.png");
  Turret.sprites = new PImage[2];
  Turret.sprites[0] = loadImage("Dolphin_Turret.png");
  Turret.sprites[1] = loadImage("Shark_Turret.png");
}
