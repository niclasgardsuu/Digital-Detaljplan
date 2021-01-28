package classes.gis.property;

import java.util.LinkedList;

public class House {
  private Surface surface;
  //private double height; //kanske inte behövs men aa
  
  public House(Point [] points) {
    this.surface = new Surface(points);
  }
  
  public Point [] getPoints() {
    return surface.getPoints();
  }
}