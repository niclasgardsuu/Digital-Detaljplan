package classes.gis.property;

public class Point {
  public double x;
  public double y;
  
  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }
  
  @Override
  public boolean equals(Object o) {
    if(o instanceof Point) {
      return this.equals((Point) o);
    } else {
      return false;
    }
  }
  
  public boolean equals(Point p) {
    return ((this.x == p.x) && (this.y == p.y));
  }
  
  public String toString() {
    return "("+ x + "," + y + ")";
  }
}