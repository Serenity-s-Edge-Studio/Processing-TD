import java.lang.Math;
public class Node{
  
  public Node parent = null;
  public Tile tile;
  private Grid map;
  public double fCost;
  
  public Node(Tile tile, Grid map){
    this.tile = tile;
    this.map = map;
  }
  public Node(Node parent, Tile tile, Grid map){
    this(tile, map);
    this.parent = parent;
  }
  public double fCost(){
    fCost = gCost() + hCost();
    return fCost;
  }
  public int gCost(){
    if (parent != null)
      return 1 + parent.gCost();
    else
      return 1;
  }
  public double hCost(){
    if (map.goalExists()){
      return Math.sqrt(Math.pow(tile.x-map.getGoalX(),2)+Math.pow(tile.y-map.getGoalY(),2));
    }
    return Double.MAX_VALUE;
  }
}
