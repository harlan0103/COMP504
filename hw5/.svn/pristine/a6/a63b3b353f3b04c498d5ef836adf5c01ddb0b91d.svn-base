package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paintobj.APaintObject;

import java.awt.*;
import java.util.Random;

public class SizeChangeStrategy implements IUpdateStrategy {
    /*
     * Constructor
     * */
    public SizeChangeStrategy(){

    }

    /*
     * Return the strategy name
     * */
    @Override
    public String getName() {
        return "SizeChangeStrategy";
    }

    // get strategy
    @Override
    public IUpdateStrategy getStrategy(){
        return null;
    }


    @Override
    public void setStrategy(IUpdateStrategy strategy){}

    /*
     * Update the ball state in the ball world
     * */
    public void updateState(APaintObject context) {
        // Size Change strategy is to change ball size randomly
        // First check if the ball is collided
        // If collide then change the radius of the ball
        // And relocate the ball
        boolean collideState = context.getCollide();
        Point vel = context.getVelocity();
        Point dims = context.getDims();

        if (collideState == true) {
            int oldRadius = context.getSize();
            int newRadius = oldRadius;
            Random random = new Random();
            while (newRadius == oldRadius) {
                // Randomize a new radius
                newRadius = random.nextInt(100) + 10;
            }
            int locX = context.getLocation().x;
            int locY = context.getLocation().y;
            // Ensure that ball will change size properly
            if (locX + newRadius > dims.x) {
                locX = dims.x - newRadius;
            } else if (locX - newRadius < 0) {
                locX = 0 + newRadius;
            }

            if (locY - newRadius < 0) {
                locY = 0 + newRadius;
            } else if (locY + newRadius > dims.y) {
                locY = dims.x - newRadius;
            }
            context.setLocation(new Point(locX, locY));
            context.setSize(newRadius);
        }
        context.nextLocation(vel.x, vel.y);
    }
}
