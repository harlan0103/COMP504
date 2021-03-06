package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paintobj.APaintObject;

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
        Point vel = context.getVelocity();
        // Vertically move through the wall
        vel.x = 0;

        boolean collide = context.getCollide();
        if(collide == true){
            // If collide the wall
            // Set the ball location at the top of the canvas
            //int radius = context.getSize();
            Point oldLoc = context.getLocation();
            Point loc = new Point(oldLoc.x, context.getSize());
            context.setLocation(loc);
        }
        context.nextLocation(vel.x, vel.y);
    }
}
