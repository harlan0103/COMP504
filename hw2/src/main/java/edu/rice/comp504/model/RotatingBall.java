package edu.rice.comp504.model;

import java.awt.*;

public class RotatingBall extends ABall {

    private String name = "RotatingBall";
    private double angle;

    /*
     * @Constructor for horizontal ball
     * */
    public RotatingBall(Point loc, int radius, Point vel, String color, double angle){
        super(loc, radius, vel, color);
        // Get angle value
        this.angle = angle;
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
        int velX = vel.x;
        int velY = vel.y;
        nextLocation(velX, velY);
        // Call rotate method to change velocity
        rotate(angle);
    }

    @Override
    public void updateBallAttrs(boolean collide){

    }
}
