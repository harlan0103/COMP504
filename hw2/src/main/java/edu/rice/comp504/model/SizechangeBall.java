package edu.rice.comp504.model;

import java.awt.*;
import java.util.Random;

/*
 * To draw a ball will change its size when a collision happened
 * */
public class SizechangeBall extends ABall {
    private String name = "SizechangeBall";

    /*
     * @Constructor for horizontal ball
     * */
    public SizechangeBall(Point loc, int radius, Point vel, String color) {
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
        int velX = vel.x;
        int velY = vel.y;
        nextLocation(velX, velY);
    }

    /*
    * @ When collision happens
    * Randomly create a new ball radius
    * and apply to the ball
    * */
    @Override
    public void updateBallAttrs(boolean collide) {
        if(collide == true){
            int oldRadius = getRadius();
            int newRadius = oldRadius;
            Random random = new Random();
            while(newRadius == oldRadius){
                // Randomize a new radius
                // And ensure new radius is different than the old one
                newRadius = random.nextInt(100)+10;
            }
            int locX = getLocation().x;
            int locY = getLocation().y;
            // Ensure that ball will change size properly
            if(locX + newRadius > 800){
                locX = 800 - newRadius;
            }
            else if(locX - newRadius < 0){
                locX = 0 + newRadius;
            }

            if(locY - newRadius < 0 ){
                locY = 0 + newRadius;
            }
            else if(locY + newRadius > 800){
                locY = 800 - newRadius;
            }
            // Set to the new radius and loc
            setLocation(new Point(locX, locY));
            setRadius(newRadius);
        }
    }
}