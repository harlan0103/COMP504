package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paintobj.APaintObject;

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

        return "RotateStrategy";
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
    public void updateState(APaintObject context) {
        // Get the velocity from the Ball object
        Point vel = context.getVelocity();
        context.nextLocation(vel.x, vel.y);
        // Then call rotate function
        context.rotate(Math.PI/10);
    }
}
