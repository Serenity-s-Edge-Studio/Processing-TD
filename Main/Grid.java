import Green.*;
import processing.core.*;
public class Grid extends Actor {
  private Tile[][] map;
  private int mapWidth, mapHeight;
  private int tileLength;
  private int marginX;
  private int marginY;
  public Grid(int marginX, int marginY, int tileLength, int mapWidth, int mapHeight) {
    super(marginX, marginY, mapWidth-(marginX*2), mapHeight-(marginY*2));
    tileLength = Math.max(tileLength, 1);
    this.marginX = marginX;
    this.marginY = marginY;
    this.mapWidth = mapWidth-(marginX*2);
    this.mapHeight = mapHeight-(marginY*2);
    this.tileLength = tileLength;
    map = new Tile[this.mapWidth/tileLength][this.mapHeight/tileLength];
    for (int x = 0; x < map[0].length; x++)
      for (int y = 0; y < map.length; y++)
        map[x][y] = new Tile();
  }
  public Tile getTileAtPosition(int xPos, int yPos) {
    int xIndex = roundUp(xPos - marginX - tileLength, tileLength)/tileLength;
    int yIndex = roundUp(yPos - marginY - tileLength, tileLength)/tileLength;
    if (yIndex < 0 || yIndex >= map.length || xIndex < 0 || xIndex >= map[0].length)
      return null;
    return map[xIndex][yIndex];
  }
  public void DrawTileAtPos(int xPos, int yPos) {
    System.out.println("TilePos: " + (roundUp(xPos - marginX - tileLength, tileLength)/tileLength) + ", " + (roundUp(xPos - marginX - tileLength, tileLength)/tileLength));
    if (getTileAtPosition(xPos, yPos) != null) {
      PApplet _processing = Green.getInstance().getParent();
      _processing.stroke(250, 250, 250);
      _processing.square((xPos/tileLength) * tileLength, (yPos/tileLength) * tileLength, tileLength);
    }
  }
  @Override
    public void act(float deltaTime) {
    PApplet _processing = Green.getInstance().getParent();
    _processing.noFill();
    _processing.rect(getX(), getY(), getWidth(), getHeight());
  }
  int roundUp(int numToRound, int multiple)
  {
    if (multiple == 0)
      return numToRound;

    int remainder = Math.abs(numToRound) % multiple;
    if (remainder == 0)
      return numToRound;

    if (numToRound < 0)
      return -(Math.abs(numToRound) - remainder);
    else
      return numToRound + multiple - remainder;
  }
}
