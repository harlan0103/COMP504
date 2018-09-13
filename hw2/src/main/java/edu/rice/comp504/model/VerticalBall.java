package edu.rice.comp504.model;

import java.awt.*;

/*
* This is a vertical moving ball
* */
public class VerticalBall extends ABall {
    private String name = "VerticalBall";

    /*
     * @Constructor for horizontal ball
     * */
    public VerticalBall(Point loc, int radius, Point vel, String color){
        super(loc, radius, vel, color);
        // Since this ball moves horizontally, velocity on Y is 0
        vel.x = 0;
    }
    /*
     *@ Abstract method from ABall getName()
     *  Return the name of the ball type
     */
    @Override
    public String getName(){
        return this.name;
    }

    /*
     * @ Abstract method from ABall updateBallLoc()
     *   Call method nextLocation() to update the ball location
     * */
    @Override
    public void updateBallLoc(){
        // Get velocity from ABall
        Point vel = getVelocity();
        int velY = vel.y;
        int velX = 0;
        nextLocation(velX, velY);
    }

    @Override
    public void updateBallAttrs(boolean collide){

    }
}
