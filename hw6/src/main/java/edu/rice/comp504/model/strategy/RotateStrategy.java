package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paint.Ball;

import java.awt.*;

/**
 * RotateStrategy
 * Return a ball rotating along a point
 * */
public class RotateStrategy implements IUpdateStrategy{
    private static IUpdateStrategy singleton;
    /**
     * Constructor
     */
    public RotateStrategy() {

    }

    // Create a singleton
    public static IUpdateStrategy makeStrategy(){
        if(singleton == null){
            singleton = new RotateStrategy();
        }
        return singleton;
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
    public void updateState(Ball context) {
        Point vel = context.getVelocity();
        context.nextLocation(vel.x, vel.y);
        // Then call rotate function
        context.rotate(Math.PI/10);
    }
}

