package edu.rice.comp504.model;

import java.awt.*;

public class Pentagon extends AShape{
    private String name;
    // create position for pentagon
    private int x2, x3, x4, x5, y2, y3, y4, y5;
    // Constructor ofr Pentagon
    public Pentagon(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, int x5, int y5, String c){
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
        this.x4 = x4;
        this.y4 = y4;
        this.x5 = x5;
        this.y5 = y5;
        Point newPoint = new Point(x1, y1);
        paint(newPoint, c);
    }
    // Return name
    @Override
    public String getName(){
        return this.name;
    }
    // Get loc value and return color
    @Override
    public void paint(Point loc, String c){
        // Method paint updates the location and color field
        super.loc.x = loc.x;
        super.loc.y = loc.y;
        super.color = c;
        getColor();
    }
}
