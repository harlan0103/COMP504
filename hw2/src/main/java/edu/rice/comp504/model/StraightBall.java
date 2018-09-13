package edu.rice.comp504.model;

import java.awt.*;

/*
* To draw a ball has default movement
* */
public class StraightBall extends ABall {
    private String name = "StraightBall";

    /*
     * @Constructor for StraightBall
     * */
    public StraightBall(Point loc, int radius, Point vel, String color) {
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

    }
}