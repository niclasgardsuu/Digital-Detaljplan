package classes.gis.property;

import java.util.LinkedList;

public class House {
  private Surface surface;
  //private double height; //kanske inte behövs men aa
  
  public House(/*LinkedList<Point> points*/) {
    LinkedList<Point> points = new LinkedList<Point>();
    points.add(new Point(1,1));
    points.add(new Point(2,1));
    points.add(new Point(2,2));
    points.add(new Point(1,2));
    this.surface = new Surface(points);
  }
  
  public LinkedList<Point> getCorners() {
    return surface.getCorners();
  }
}