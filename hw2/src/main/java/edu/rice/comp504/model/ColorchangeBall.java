package edu.rice.comp504.model;

import java.awt.*;
import java.util.Random;

/*
 * To draw a ball that will change the color when collision happened
 * */
public class ColorchangeBall extends ABall {
    private String name = "ColorchangeBall";

    /*
     * @Constructor for horizontal ball
     * */
    public ColorchangeBall(Point loc, int radius, Point vel, String color) {
        super(loc, radius, vel, color);

    }

    /*
     *@ Abstract method from ABall getName()
     *  Return the name of the ball type
     */
    @Override
    public String getName() {
        return this.name;
    }

    /*
     * @ Abstract method from ABall updateBallLoc()
     *   Call method nextLocation() to update the ball location
     * */
    @Override
    public void updateBallLoc() {
        // Get velocity from ABall
        Point vel = getVelocity();
        int velY = vel.y;
        int velX = vel.x;
        nextLocation(velX, velY);
    }

    @Override
    public void updateBallAttrs(boolean collide) {
        if(collide == true){
            // Randomize a new color code
            String oldColor = getColor();
            String newColor = oldColor;
            // Ensure we have different color
            while(oldColor.equals(newColor)){
                newColor = randomNewColor();
            }
            // Set new color
            setColor(newColor);
        }
    }

    /*
    * @ randomNewColor
    * create a new color code in hex
    * */
    public String randomNewColor(){
        String newColor;
        String r, g, b;
        Random random = new Random();
        r = Integer.toHexString(random.nextInt(256)).toUpperCase();
        g = Integer.toHexString(random.nextInt(256)).toUpperCase();
        b = Integer.toHexString(random.nextInt(256)).toUpperCase();

        r = r.length() == 1 ? "0" + r : r;
        g = g.length() == 1 ? "0" + g : g;
        b = b.length() == 1 ? "0" + b : b;
        newColor = "#" + r+g+b;
        return newColor;
    }
}