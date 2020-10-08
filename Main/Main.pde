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
  engine.handleMousePosition(pmouseX, pmouseY, mouseX, mouseY);
  engine.handleDraw();
  engine.handleAct();
}
void mousePressed(){
  engine.handleMouseDown(mouseButton);
}
void mouseReleased(){
  engine.handleMouseUp(mouseButton);
}
