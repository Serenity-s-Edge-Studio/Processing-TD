import Green.*;
import processing.core.*;
public class Grid extends Actor {
  private Tile[][] map;
  private int mapWidth, mapHeight;
  private int tileLength;
  private int marginX;
  private int marginY;
  public Grid(int marginX, int marginY, int tileLength, int mapWidth, int mapHeight){
    super(marginX, marginY, mapWidth-(marginX*2), mapHeight-(marginY*2));
    this.marginX = marginX;
    this.marginY = marginY;
    this.mapWidth = mapWidth-(marginX*2);
    this.mapHeight = mapHeight-(marginY*2);
    this.tileLength = tileLength;
    map = new Tile[this.mapWidth/tileLength][this.mapHeight/tileLength];
    for(int x = 0; x < map[0].length; x++)
      for (int y = 0; y < map.length; y++)
        map[x][y] = new Tile();
  }
  public Tile getTileAtPosition(int xPos, int yPos){
    int xIndex = (xPos - marginX)/tileLength;
    int yIndex = (yPos - marginY)/tileLength;
    if (yIndex < 0 || yIndex > map.length || xIndex < 0 || xIndex > map[0].length || !pointInBounds(xPos, yPos))
      return null;
    return map[xIndex][yIndex];
  }
  public void DrawTileAtPos(int xPos, int yPos){
    System.out.println("TilePos: " + ((xPos - marginX * 2)/tileLength) + ", " + ((yPos - marginY * 2)/tileLength));
    if (getTileAtPosition(xPos, yPos) != null){
      PApplet _processing = Green.getInstance().getParent();
      _processing.stroke(250,250,250);
      _processing.square((xPos/tileLength) * tileLength, (yPos/tileLength) * tileLength, tileLength);
    }
  }
  @Override
  public void act(float deltaTime){
    PApplet _processing = Green.getInstance().getParent();
    _processing.noFill();
    _processing.rect(getX(), getY(), getWidth(), getHeight());
  }
}
