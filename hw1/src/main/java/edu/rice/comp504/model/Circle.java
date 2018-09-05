package edu.rice.comp504.model;

import java.awt.*;

public class Circle extends AShape{
    private String name;
    private int radius;

    // Constructor for shape circle
    public Circle(int locX, int locY, int radius, String c){
        this.radius = radius;
        // Create a new Point object by using input value
        Point newPoint = new Point(locX, locY);
        // Call paint method inside constructor
        paint(newPoint, c);
    }
    // Return radius of circle
    public int getRadius(){
        return radius;
    }

    @Override
    public String getName(){
        return this.name;
    }
    // Get value of loc and return color radius
    @Override
    public void paint(Point loc, String c){
        // Method paint updates the location and color field
        super.loc.x = loc.x;
        super.loc.y = loc.y;
        super.color = c;
        getRadius();
        getColor();
    }
}
