import Green.*;
import processing.core.*;
public class Button extends Actor {
  BaseEvent onPressed;
  boolean wasPressed = false;
  public Button(float x, float y, PImage sprite, BaseEvent event) {
    super(x, y, sprite);
    onPressed = event;
  }
  @Override
  public void act(float deltaTime) {
    if (isMouseButtonUpThisFrameHere(PConstants.LEFT) && !wasPressed){
      wasPressed = true;
      onPressed.invoke();
    }
  }
}
