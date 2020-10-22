import java.lang.Math;
public class Vector2Int
{              
    // Members
    public int x;
    public int y;
       
    // Constructors
    public Vector2Int() {
        this.x = 0;
        this.y = 0;
    }
       
    public Vector2Int(int x, int y) {
        this.x = x;
        this.y = y;
    }
       
    // Compare two vectors
    public boolean equals(Vector2Int other) {
        return (this.x == other.x && this.y == other.y);
    }
    public static float distance(Vector2Int a, Vector2Int b){
      return (float)Math.sqrt(Math.pow(a.x-b.x,2)+Math.pow(a.y-b.y,2));
    }
}
