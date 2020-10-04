import shiffman.box2d.*;

import Green.*;

public Green engine;
private MainMenu menu;
void setup()
{
    size(512, 512);
    print("Starting game engine");
    engine = new Green(this);
    menu = new MainMenu(engine);
    engine.loadWorld(menu);
}
void draw(){
  engine.handleInput();
  engine.handleAct();
  engine.handleDraw();
}
void mousePressed(){
  engine.handleMouseDown(mouseButton);
}
