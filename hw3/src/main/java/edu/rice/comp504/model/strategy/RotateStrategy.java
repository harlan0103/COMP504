package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.ball.Ball;

import java.awt.*;

public class RotateStrategy implements IUpdateStrategy{
    /**
     * Constructor
     */
    public RotateStrategy() {

    }

    /**
     * Get the strategy name
     * @return strategy name
     */
    public String getName() {

        return "RotationStrategy";
    }

    /**
     * Update the ball state in the ball world
     * @param context The ball to update
     */
    public void updateState(Ball context) {
        // Get the velocity from the Ball object
        Point vel = context.getVelocity();
        context.nextLocation(vel.x, vel.y);
        // Then call rotate function
        context.rotate(Math.PI/10);
    }
}

