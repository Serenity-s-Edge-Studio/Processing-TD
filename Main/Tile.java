import Green.*;
import processing.core.*;
public class Tile {
  enum type{
    Walkable,
    Empty,
    Mount,
    Goal,
    Start
  }
  public type tileType;
  public Tile(){
    this.tileType = type.Empty;
  }
  public Tile(type tileType){
    this.tileType = tileType;
  }
}