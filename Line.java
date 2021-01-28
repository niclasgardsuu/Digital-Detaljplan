package classes.gis.property;

public class Line {
  public Point a;
  public Point b;
  
  public Line(Point a, Point b) {
    this.a = a;
    this.b = b;
  }
  
  public Point intersect(Line other) {
    double k1 = (a.y-b.y)/(a.x-b.x); 
    double m1 = a.y - k1*a.x;
    
    double k2 = (other.a.y-other.b.y)/(other.a.x-other.b.x); 
    double m2 = other.a.y - k2*other.a.x;
    
    double den = (k2 - k1);
    if(den == 0) return null;
    double x = (m1 - m2) / den;
    double y = k1*x + m1;
    return new Point(x,y);
  }
}