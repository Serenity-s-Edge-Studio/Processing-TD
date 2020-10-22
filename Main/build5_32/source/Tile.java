import Green.*;
import processing.core.*;
public class Tile {
  public static PImage[] sprites;
  public final int x;
  public final int y;
  enum type{
    Walkable,
    Empty,
    Mount,
    Goal,
    Start
  }
  public type tileType;
  public Tile(int x, int y){
    this.x = x;
    this.y = y;
    this.tileType = type.Empty;
  }
  public Tile(type tileType, int x, int y){
    this (x,y);
    this.tileType = tileType;
  }
}
