package classes.gis.property;

import java.util.Iterator;
import java.util.LinkedList;

public class Property {
  private House [] houses;
  private Surface plot;
  
  public Property(String filename) { //tror man ska utgå från shapefile här för att få in värden till houses.
    int houseCount = 2;
    House [] tempHouses = new House[houseCount];
    
    //temporary way of generating our houses and property borders
    //to make things simpler when reading from the shapefile, areas where you cannot build can be stored as houses, because they will work the same.
    int pointCount = 3;
    Point [] points1 = new Point[pointCount];
    points1[0] = new Point(0.5,0.5);
    points1[1] = new Point(4.5,0.5);
    points1[2] = new Point(0.5,4.5);
    tempHouses[0] = new House(points1);
    
    pointCount = 5;
    Point [] points2 = new Point[pointCount];
    points2[0] = new Point(3,3);
    points2[1] = new Point(4,3);
    points2[2] = new Point(5,4);
    points2[3] = new Point(4,5);
    points2[4] = new Point(3,4);
    tempHouses[1] = new House(points2);
    
    /*
    pointCount = 3;
    Point [] points3 = new Point[pointCount];
    points3[0] = new Point(4,3.5);
    points3[1] = new Point(5,4);
    points3[2] = new Point(4,4);
    tempHouses[2] = new House(points3);
    */
    
    this.houses = tempHouses;
    
    pointCount = 4;
    Point [] points4 = new Point[pointCount];
    points4[0] = new Point(0,0);
    points4[1] = new Point(5,0);
    points4[2] = new Point(5,6);
    points4[3] = new Point(0,6);
    
    this.plot = new Surface(points4);
  }
  
  public House [] getHouses() {
    return this.houses;
  }
  
  /*
  @brief calculates all the intersecting point if we were to extend all house segments into all other lines on the porperty
  @return array of all the intersection points
  */
  public Point [] getIntersectingPoints() {
    LinkedList<Point> intersectPoints = new LinkedList<Point>();
    int currentHouseIndex = 0;
    
    for(House house : houses) {
      /*
      För varje två punkter i house, gör det till en linje, och kolla intersection med alla linjer som dras från två punkter i alla hus, och plotArea.
      Om man hittar en intersect, lägg till den punkten i intersectPoints.
      Sen i slutet vill vi ha en array istället för länkad lista. dvs: 
      points = new Point[intersectPoints.length]; 
      points[i] = intersectPoints.get(i);
      */
      
      Point [] currentHousePoints = houses[currentHouseIndex].getPoints();
      int currentHousePointCount = currentHousePoints.length;
      for(int i = 0; i < currentHousePointCount; i++) {
        //declare a line going through the two connected points of the house
        Line currentLine = null;
        if(i != currentHousePointCount-1) {
          currentLine = new Line(currentHousePoints[i],currentHousePoints[i+1]);
        } else {
          currentLine = new Line(currentHousePoints[i],currentHousePoints[0]);
        }
        
        //iterate through every other line segment in of the other houses
        for(int j = 0; j < houses.length; j++) {
          if(j != currentHouseIndex) { //we don't check intersections with the same house
            Point [] iterHousePoints = houses[j].getPoints();
            int iterHousePointCount = iterHousePoints.length;
            
            for (int k = 0; k < iterHousePointCount; k++) {
              //define the line segment going through the current points of the current building
              Line currentIterLine = null;
              if(k != iterHousePointCount-1) {
                currentIterLine = new Line(iterHousePoints[k],iterHousePoints[k+1]);
              } else {
                currentIterLine = new Line(iterHousePoints[k],iterHousePoints[0]);
              }
              //get intersection point
              Point intersection = currentLine.intersect(currentIterLine);
              System.out.println("(" + currentLine + " & " + currentIterLine + ")");
              //check if collision with the segment
              if(intersection != null) {
                //accumulatively add the intersection point to the list of points if it was valid
                System.out.println("INTERSECT: " + intersection);
                intersectPoints.add(intersection);
              }
            }
          }
        }
        Point [] plotPoints = plot.getPoints();
        int plotPointCount = plotPoints.length;
        for (int j = 0; j < plotPointCount; j++) {
          Line currentPlotLine = null;
          if(j != plotPointCount-1) {
            currentPlotLine = new Line(plotPoints[j],plotPoints[j+1]);
          } else {
            currentPlotLine = new Line(plotPoints[j],plotPoints[0]);
          }
          Point intersection = currentLine.intersect(currentPlotLine);
          System.out.println("(" + currentLine + " & " + currentPlotLine + ")");
          //check if collision with the segment
          if(intersection != null && 
            ((intersection.x >= currentPlotLine.a.x && intersection.x <= currentPlotLine.b.x) 
            || 
            (intersection.x >= currentPlotLine.b.x && intersection.x <= currentPlotLine.a.x))) {
            //accumulatively add the intersection point to the list of points if it was valid
            System.out.println("INTERSECT: " + intersection);
            intersectPoints.add(intersection);
          }
        }
      }
      System.out.println("");
      System.out.println("NEW HOUSE:");
      currentHouseIndex++;
    }
    Point [] points = new Point[intersectPoints.size()];
    for(int i = 0; i < intersectPoints.size(); i++) {
      points[i] = intersectPoints.get(i);
    }
    return points;
  }
  
  public Point [] getAllPoints() {
    LinkedList<Point> allPoints = new LinkedList<Point>();
    
    //points of all the houses
    for(int i = 0; i < this.houses.length; i++) {
      Point [] currentHousePoints = houses[i].getPoints();
      for(int j = 0; j < currentHousePoints.length; j++) {
        allPoints.add(currentHousePoints[j]);
      }
    }
    //points of the border
    Point [] plotPoints = this.plot.getPoints();
    for(int i = 0; i < plotPoints.length; i++) {
      allPoints.add(plotPoints[i]);
    }
    
    //points of all intersections
    Point [] intersectPoints = this.getIntersectingPoints();
    for(int i = 0; i < intersectPoints.length; i++) {
      allPoints.add(intersectPoints[i]);
    }
    
    //remove duplicates
    LinkedList<Point> allUniquePoints = new LinkedList<Point>();
    System.out.println(allPoints);
    System.out.println(allUniquePoints);
    while(allPoints.size() > 0) {
      Point currentPoint = allPoints.pollFirst();
      if(!allUniquePoints.contains(currentPoint)) {
        allUniquePoints.add(currentPoint);
      }
    }
    System.out.println(allPoints);
    System.out.println(allUniquePoints);
    
    //transfer to array
    Point [] points = new Point[allUniquePoints.size()];
    for(int i = 0; i < points.length; i++) {
      points[i] = allUniquePoints.get(i);
    }
    return points;
  }
  
  static public void main(String [] args) {
    Property prop = new Property("hello");
    Point [] intersectPoints = prop.getIntersectingPoints();
    for(int i = 0; i < intersectPoints.length; i++) {
      System.out.println(intersectPoints[i]);
    }
    House [] houses = prop.getHouses();
    for(House house : houses) {
      System.out.println(house.area());
    }
    
    Point [] allPoints = prop.getAllPoints();
  }
}