import java.util.*;
import java.lang.Math;
public class NodeComparator implements Comparator<Node>{
  public int compare(Node a, Node b){
    return (int) Math.round(a.fCost() - b.fCost());
  }
}
