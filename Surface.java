package classes.gis.property;

import java.util.LinkedList;

public class Surface {
  private LinkedList<Point> points;
  
  public Surface(LinkedList<Point> points) {
    this.points = points;
  }
  
  public double area() {
    /*
    Någon algoritm utifrån lista av punkter till area.
    */
    return 0;
  }
  
  public LinkedList<Point> getCorners() {
    return points;
  }
}