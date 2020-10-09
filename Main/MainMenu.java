import Green.*;
import processing.core.*;
public class MainMenu extends Scene{
  public MainMenu(Green engine){
   super(engine);
  }
  @Override
  public void prepare(){
    setBackgroundColor(0,0,0);
    addObject(
      new Button(engine.getParent().sketchWidth()/2, 
                 engine.getParent().sketchHeight()/2, 
                 engine.getParent().loadImage("playButton.png"), 
                 new BaseEvent()
                 {
                   @Override
                   public void invoke(){
                     System.out.println("Load Game");
                     engine.loadWorld(new Level(engine));
                   }
                 }
                 ));
        
  }
  @Override
  public void act(float deltaTime){
    
  }
}
