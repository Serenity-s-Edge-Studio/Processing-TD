import shiffman.box2d.*;

import Green.*;

private Green engine;
private MainMenu menu;
void setup()
{
    size(512, 512);
    engine = new Green(this);
    menu = new MainMenu();
    engine.loadWorld(menu);
}
void draw(){
  engine.handleAct();
}
