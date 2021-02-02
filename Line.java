package classes.gis.property;

public class Line {
  public Point a;
  public Point b;
  
  public Line(Point a, Point b) {
    this.a = a;
    this.b = b;
  }
  
  public Point intersect(Line other) { // other is a line segment,
    double dx1 = (a.x - b.x);
    double dy1 = (a.y - b.y);
    
    double dx2 = (other.a.x - other.b.x);
    double dy2 = (other.a.y - other.b.y);
    
    if(dx1 != 0 ) {
      double k1 = dy1/dx1;
      double m1 = a.y - k1*a.x;
      
      if(dx2 != 0) {
        //this means both lines have a slope, we find their intersection by basic algebra
        double k2 = dy2/dx2;
        double m2 = other.a.y - k2*other.a.x;
        
        double den = (k2 - k1);
        if(den == 0) return null;
        double x = (m1 - m2) / den;
        double y = k1*x + m1;
        if((x < a.x && x > b.x) || (x < b.x && x > a.x)) {
          return new Point(x,y);
        } else {
          return null;
        }
      } else {
        //this means that the second line has a vertical slope. We want to know if the first line 
        //passes through the vertical line segment by checking if the x-coord of a point changes 
        //from one side of the line to the other when passing in the two y-values
        double x_y1 = (other.a.y - m1)/ k1;
        double x_y2 = (other.b.y - m1)/ k1;
        
        if((other.a.x - x_y1)*(other.a.x - x_y2) <= 0) {
          double x = other.a.x;
          double y = k1*x + m1;
          return new Point(x,y);
        }
      }
    } else {
      if(dx2 != 0) {
        //this means that the first line has vertical slope. We want to check if the the 
        //vertical line is going through the interval of the second line's both x-coords
        if((other.a.x <= a.x && other.b.x >= a.x) || (other.b.x <= a.x && other.a.x >= a.x)) {
          double k2 = dy2/dx2;
          double m2 = other.a.y - k2*other.a.x;
          double x = a.x;
          double y = k2*x + m2;
          return new Point(x,y);
        }
      }
    }
    return null;
  }
  
  public String toString() {
    return "(" + a + ", " + b + ")";
  }
}