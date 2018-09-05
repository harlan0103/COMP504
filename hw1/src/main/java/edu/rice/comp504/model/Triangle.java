package edu.rice.comp504.model;

import java.awt.*;

public class Triangle extends AShape {
    private String name;
    private int x2, y2, x3, y3;

    // Constructor for Triangle
    public Triangle(int locX, int locY, int x2, int y2, int x3, int y3, String c){
        //super(locX, locY, c);
        this.x2 = x2;
        this.x3 = x3;
        this.y2 = y2;
        this.y3 = y3;
        // Create a new Point object by using input value
        Point newPoint = new Point(locX, locY);
        // Call paint method inside constructor
        paint(newPoint, c);
        //paint(super.loc, c);
    }

    // Get other points of triangle
    public int getX2(){
        return x2;
    }

    public int getX3(){
        return x2;
    }

    public int getY2(){
        return x2;
    }

    public int getY3(){
        return x2;
    }

    @Override
    public String getName(){
        return this.name;
    }

    @Override
    public void paint(Point loc, String c){
        // Method paint updates the location and color field
        super.loc.x = loc.x;
        super.loc.y = loc.y;
        super.color = c;
        getX2();
        getX3();
        getY2();
        getY3();
        getColor();
    }

}
