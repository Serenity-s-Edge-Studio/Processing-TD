import Green.*;
import processing.core.*;
public class Tile {
  enum type{
    Walkable,
    Empty,
    Mount,
    Goal
  }
  public type tileType;
  public Tile(){
    this.tileType = type.Walkable;
  }
  public Tile(type tileType){
    this.tileType = tileType;
  }
}
