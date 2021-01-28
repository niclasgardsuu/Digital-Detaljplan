package classes.gis.property;

import java.util.LinkedList;

public class Surface {
  private Point [] points;
  private int pointCount;
  
  public Surface(Point [] points) {
    this.points = points;
  }
  
  public double area() {
    /*
    Någon algoritm utifrån array av punkter till area.
    */
    return 0;
  }
  
  public Point [] getPoints() {
    return points;
  }
}