package edu.rice.comp504.model;

import java.awt.*;

/*
* To draw a ball move horizontally
* */
public class HorizontalBall extends ABall {
    private String name = "HorizontalBall";

    /*
    * @Constructor for horizontal ball
    * */
    public HorizontalBall(Point loc, int radius, Point vel, String color){
        super(loc, radius, vel, color);
        // Since this ball moves horizontally, velocity on Y is 0
        vel.y = 0;
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
        int velY = 0;
        nextLocation(velX, velY);
    }

    @Override
    public void updateBallAttrs(boolean collide){

    }

}
