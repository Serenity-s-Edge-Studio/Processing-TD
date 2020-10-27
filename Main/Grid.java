import Green.*;
import processing.core.*;
import processing.data.*;
import java.util.*;
public class Grid extends Actor {
  public static Grid singleton;
  private Tile[][] map;
  private int mapWidth, mapHeight;
  private int tileLength;
  private int marginX;
  private int marginY;
  private Tile goalTile;
  private Tile spawnTile;
  private Vector2Int[] pathArray = null;
  public Grid(int marginX, int marginY, int tileLength, int mapWidth, int mapHeight) {
    super(marginX, marginY, mapWidth-(marginX*2), mapHeight-(marginY*2));
    tileLength = Math.max(tileLength, 1);
    this.marginX = marginX;
    this.marginY = marginY;
    this.mapWidth = mapWidth-(marginX*2);
    this.mapHeight = mapHeight-(marginY*2);
    this.tileLength = tileLength;
    map = new Tile[this.mapWidth/tileLength][this.mapHeight/tileLength];
    for (int x = 0; x < map.length; x++)
      for (int y = 0; y < map[0].length; y++) {
        try {
          map[x][y] = new Tile(x, y);
        }
        catch(Exception e) {
          e.printStackTrace(); //<>//
        }
      }
    singleton = this;
  }
  public Grid(int marginX, int marginY, int tileLength, int mapWidth, int mapHeight, Tile[][] array) {
    this(marginX, marginY, tileLength, mapWidth, mapHeight);
    this.map = array;
    for (int x = 0; x < map.length; x++){
      for (int y = 0; y < map[0].length; y++) {
        switch(map[x][y].tileType) {
        case Goal:
          goalTile = map[x][y];
          break;
        case Start:
          spawnTile = map[x][y];
          break;
        }
      }
    }
    pathArray = getPath();
  }
  public Tile getTileAtPosition(int xPos, int yPos) {
    int xIndex = roundUp(xPos - marginX - tileLength, tileLength)/tileLength;
    int yIndex = roundUp(yPos - marginY - tileLength, tileLength)/tileLength;
    if (yIndex < 0 || yIndex >= map[0].length || xIndex < 0 || xIndex >= map.length)
      return null;
    return map[xIndex][yIndex];
  }
  public Vector2Int[] getPath() {
    if (pathArray == null) {
      Queue<Vector2Int> path = calculatePath();
      if (path != null) {
        pathArray = new Vector2Int[0];
        pathArray = path.toArray(pathArray);
        return getPath();
      }
      System.out.println("No path found!");
      return null;
    }
    return pathArray.clone();
  }
  public Vector2Int[] getPathScaled() {
    Vector2Int[] returnList = getPath();
    if (returnList != null) {
      for (int i = 0; i < returnList.length; i++) {
        returnList[i] = scalePosition(returnList[i]);
      }
    }
    return returnList;
  }
  public Vector2Int scalePosition(Vector2Int value) {
    return new Vector2Int(value.x * tileLength + marginX, value.y * tileLength + marginY);
  }
  public int getGoalX() {
    return goalTile.x;
  }
  public int getGoalY() {
    return goalTile.y;
  }
  public boolean goalExists() {
    return goalTile != null;
  }
  public int getSpawnX() {
    return spawnTile.x;
  }
  public int getSpawnY() {
    return spawnTile.y;
  }
  public boolean spawnExists() {
    return spawnTile != null;
  }
  public int getTileLength() {
    return tileLength;
  }
  public int getMarginY() {
    return marginY;
  }
  public int getMarginX() {
    return marginX;
  }
  public void DrawTileAtPos(int xPos, int yPos) {
    //System.out.println("TilePos: " + (roundUp(xPos - marginX - tileLength, tileLength)/tileLength) + ", " + (roundUp(xPos - marginX - tileLength, tileLength)/tileLength));
    if (getTileAtPosition(xPos, yPos) != null) {
      PApplet _processing = Green.getInstance().getParent();
      _processing.noFill();
      _processing.stroke(250, 250, 250);
      int xIndex = roundUp(xPos - marginX - tileLength, tileLength);
      int yIndex = roundUp(yPos - marginY - tileLength, tileLength);
      _processing.square(xIndex, yIndex, tileLength);
    }
  }
  @Override
  //This function is called every frame by Green
    public void act(float deltaTime) {
  }
  @Override
  //This function is called every frame by Green
    public void draw() {
    PApplet _processing = Green.getInstance().getParent();

    //renderLoop
    _processing.noStroke();
    for (int x = 0; x < map.length; x++){
      for (int y = 0; y < map[0].length; y++) {
        switch(map[x][y].tileType) {
        case Walkable:
          _processing.image(Tile.sprites[0], x * tileLength, y * tileLength, tileLength, tileLength);
          continue;
        case Empty:
          _processing.image(Tile.sprites[1], x * tileLength, y * tileLength, tileLength, tileLength);
          continue;
        case Mount:
          _processing.fill(153);
          break;
        case Goal:
          _processing.fill(255, 0, 0);
          break;
        case Start:
          _processing.fill(0, 128, 0);
        }
        _processing.square(x * tileLength, y * tileLength, tileLength);
      }
    }
    //draw mouse over graphics
    DrawTileAtPos(_processing.mouseX, _processing.mouseY);
    DrawPath();
    //grid outline
    _processing.noFill();
    _processing.stroke(250, 250, 250);
    _processing.rect(0, 0, getWidth(), getHeight());
  }
  //This function draws the calculated path as a green line
  private void DrawPath() {
    PApplet _processing = Green.getInstance().getParent();
    if (pathArray != null && pathArray.length > 1) {
      for (int i = 0; i < pathArray.length - 1; i++) {
        _processing.stroke(0, 128, 0);
        _processing.line(pathArray[i].x * tileLength + (float)tileLength/2, 
          pathArray[i].y * tileLength + (float)tileLength/2, 
          pathArray[i + 1].x * tileLength + (float)tileLength/2, 
          pathArray[i + 1].y * tileLength + (float)tileLength/2);
      }
    }
  }
  //Update tile at unscaled position
  public void SetTile(int xPos, int yPos, Tile.type type) {
    Tile tile = getTileAtPosition(xPos, yPos);
    if (tile != null) {
      if (tile.tileType != type) {
        switch(tile.tileType) {
        case Goal:
          goalTile = tile;
          break;
        case Start:
          spawnTile = tile;
          break;
        }
        tile.tileType = type;
      }
    }
  }
  //helper method for rounding numbers
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
  //Creates a empty map using the empty tiles
  public void clear() {
    map = new Tile[this.mapWidth/tileLength][this.mapHeight/tileLength];
    for (int x = 0; x < map.length; x++)
      for (int y = 0; y < map[0].length; y++)
        map[x][y] = new Tile(x, y);
  }
  //Convert the grid into a JSONObject
  public JSONObject toJSON() {
    JSONObject json = new JSONObject();
    JSONObject metaData = new JSONObject();
    metaData.setInt("mapWidth", mapWidth+marginX*2);
    metaData.setInt("mapHeight", mapHeight+marginY*2);
    metaData.setInt("tileLength", tileLength);
    metaData.setInt("marginX", marginX);
    metaData.setInt("marginY", marginY);
    json.setJSONObject("metaData", metaData);

    JSONArray tilesX = new JSONArray();
    for (int x = 0; x < map.length; x++){
      JSONArray tilesY = new JSONArray();
      for (int y = 0; y < map[0].length; y++){
        tilesY.setJSONObject(y, new JSONObject().setInt("type", map[x][y].tileType.ordinal()));
      }
      tilesX.setJSONArray(x, tilesY);
    }
    json.setJSONArray("Map", tilesX);
    return json;
  }
  //return a new Grid from a JSONObject
  public static Grid fromJSON(JSONObject data) {
    JSONObject metaData = data.getJSONObject("metaData");
    int mapWidth = metaData.getInt("mapWidth");
    int mapHeight = metaData.getInt("mapHeight");
    int tileLength = metaData.getInt("tileLength");
    int marginX = metaData.getInt("marginX");
    int marginY = metaData.getInt("marginY");
    JSONArray tilesX = data.getJSONArray("Map");
    Tile[][] mapArray = new Tile[tilesX.size()][tilesX.getJSONArray(0).size()];
    Tile.type[] enums = Tile.type.values();
    for (int x = 0; x < tilesX.size(); x++) {
      JSONArray tilesY = tilesX.getJSONArray(x);
      for (int y = 0; y < tilesY.size(); y++) {
        JSONObject tileObj = tilesY.getJSONObject(y);
        mapArray[x][y] = new Tile(enums[tileObj.getInt("type")], x, y);
      }
    }
    return new Grid(marginX, marginY, tileLength, mapWidth, mapHeight, mapArray);
  }
  //Function to calculate the path using the A* pathing algorithm
  public Queue<Vector2Int> calculatePath() {
    if (!goalExists() || !spawnExists()) //<>//
      return null;
    PriorityQueue<Node> open = new PriorityQueue<Node>(new NodeComparator());
    open.add(new Node(spawnTile, this));
    List<Node> closed = new ArrayList<Node>();
    Map<Tile, Integer> gCostDictionary = new HashMap<Tile, Integer>(10);
    while (open.peek() != null) { //<>//
      if (open.peek().tile.tileType == Tile.type.Goal)
        return retracePath(open.peek());
      Node current = open.poll();
      closed.add(current);
      for (Node n : getNeighbors(current)) {
        if (closed.contains(n))
          continue;
        if (n.gCost() < gCostDictionary.getOrDefault(n.tile, Integer.MAX_VALUE)) {
          gCostDictionary.put(n.tile, n.gCost());
          open.add(n);
        }
      }
    }
    return null;
  }
  //Helper method for retracing a Node based linked list into a Queue<Vector2Int>
  private Queue<Vector2Int> retracePath(Node end) {
    Node current = end;
    Stack<Tile> stack = new Stack<Tile>();
    do {
      stack.push(current.tile);
      current = current.parent;
    } while (current != null);
    Queue<Vector2Int> returnQueue = new LinkedList<Vector2Int>();
    while (!stack.empty()) {
      Tile currentTile = stack.pop();
      returnQueue.add(new Vector2Int(currentTile.x, currentTile.y));
    }
    return returnQueue;
  }
  //Finds the list of neighboring nodes
  private List<Node> getNeighbors(Node parent) {
    List<Node> returnList = new ArrayList<Node>();
    for (int x = -1; x < 2; x++) {
      for (int y = -1; y < 2; y++) {
        if (x == 0 && y == 0) continue;
        int xIndex = parent.tile.x + x; 
        int yIndex = parent.tile.y + y;
        if (yIndex < 0 || yIndex >= map[0].length || xIndex < 0 || xIndex >= map.length)
          continue;
        if (map[xIndex][yIndex].tileType == Tile.type.Goal || map[xIndex][yIndex].tileType == Tile.type.Walkable)
          returnList.add(new Node(parent, map[xIndex][yIndex], this));
      }
    }
    return returnList;
  }
}
