package edu.rice.comp504.model;

import java.awt.*;

/*
 * To draw a null ball
 * */
public class NullBall extends ABall {
    private String name = "NullBall";

    /*
     * @Constructor for StraightBall
     * */
    public NullBall(Point loc, int radius, Point vel, String color) {
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
        // For null ball
        // will not have any position change
    }

    @Override
    public void updateBallAttrs(boolean collide) {
    }
}