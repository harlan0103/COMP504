package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paint.Ball;

import java.awt.*;

/**
 * NullStrategy
 * Return back a static black ball
 * */
public class NullStrategy implements IUpdateStrategy {

    /**
     * Constructor
     */
    public NullStrategy() {

    }

    /**
     * Get the strategy name
     * @return strategy name
     */
    public String getName() {
        return "Null";
    }

    @Override
    public IUpdateStrategy getStrategy(){
        return null;
    }

    @Override
    public void setStrategy(IUpdateStrategy strategy){}

    /**
     * Update the ball state in the ball world
     * @param context The ball to update
     */
    public void updateState(Ball context) {
        // Set velocity to zero
        // Set color to black
        Point vel = context.getVelocity();
        vel.x = 0;
        vel.y = 0;
        context.setColor("#000000");
        context.nextLocation(vel.x, vel.y);
    }
}