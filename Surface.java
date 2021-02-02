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
    int length = points.length;
    double acc = 0; //accumulator of the numerator of the formula for area
    for(int i = 0; i < (length)-1; i++) {
      acc += (points[i].x * points[i+1].y) - (points[i].y * points[i+1].x);
    }
    acc += (points[length-1].x * points[0].y) - (points[length-1].y * points[0].x);
    
    return acc/2;
  }
  
  public Point [] getPoints() {
    return points;
  }
}