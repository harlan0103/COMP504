package edu.rice.comp504.model;

import java.awt.*;

public class Rectangle extends AShape {
    private String name;
    private int width;
    private int height;

    // Constructor for shape rectangle
    public Rectangle(int locX, int locY, int width, int height, String c){
        //super(locX, locY, c);
        this.width = width;
        this.height = height;
        // Create a new Point object by using input value
        Point newPoint = new Point(locX, locY);
        // Call paint method inside constructor
        paint(newPoint, c);
        //paint(super.loc, c);
    }

    // Get width variable for rectangle
    public int getWidth(){
        return width;
    }

    // Get height variable for rectangle
    public int getHeight(){
        return height;
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
        getWidth();
        getHeight();
        getColor();
    }
}
