package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.ball.Ball;

import java.awt.*;

public class LoopStrategy implements IUpdateStrategy {
    /**
     * Constructor
     */
    public LoopStrategy() {
    }

    /**
     * Get the strategy name
     * @return strategy name
     */
    public String getName() {

        return "LoopStrategy";
    }

    /**
     * Update the ball state in the ball world
     * @param context The ball to update
     */
    public void updateState(Ball context) {
        Point vel = context.getVelocity();
        // Vertically move through the wall
        vel.x = 0;
        boolean collide = context.isCollide();
        if(collide == true){
            // If collide the wall
            // Set the ball location at the top of the canvas
            int radius = context.getRadius();
            Point oldLoc = context.getLocation();
            Point loc = new Point(oldLoc.x, radius);
            context.setLocation(loc);
        }
        context.nextLocation(vel.x, vel.y);
    }
}