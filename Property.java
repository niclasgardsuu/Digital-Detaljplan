package classes.gis.property;

import java.util.LinkedList;

public class Property {
  private House [] houses;
  private Surface area;
  
  public Property(int houseCount, LinkedList<Point> area) { //tror man ska utgå från shapefile här för att få in värden till houses.
    this.houses = new House[houseCount];
    for(int i = 0; i < houseCount; i++) {
      houses[i] = new House(/*någon input från nånstans*/);
    }
    
    LinkedList<Point> points = new LinkedList<Point>();
    points.add(new Point(0,0));
    points.add(new Point(3,0));
    points.add(new Point(3,3));
    points.add(new Point(0,3));
    this.area = new Surface(points);
  }
}