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
    @Override
    public int hashCode(){
      return x + y;
    }
    // Compare two vectors
    @Override
    public boolean equals(Object other) {
        return (other instanceof Vector2Int && this.x == ((Vector2Int)other).x && this.y == ((Vector2Int)other).y);
    }
    public static float distance(Vector2Int a, Vector2Int b){
      return (float)Math.sqrt(Math.pow(a.x-b.x,2)+Math.pow(a.y-b.y,2));
    }
}
