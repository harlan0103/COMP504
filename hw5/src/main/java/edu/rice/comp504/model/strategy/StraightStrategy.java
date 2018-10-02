package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paintobj.APaintObject;

import java.awt.*;

/**
 * The straight strategy will cause the ball to travel in a horizontally straight line
 */
public class StraightStrategy implements IUpdateStrategy {
    private static IUpdateStrategy singleton;
    /**
     * Constructor
     */
    private StraightStrategy() {

    }

    /*
    * Make a singleton for straight strategy
    * */
    public static IUpdateStrategy makeStrategy(){
        if(singleton == null){
            singleton = new StraightStrategy();
        }
        return singleton;
    }

    /**
     * Get the strategy name
     * @return strategy name
     */
    public String getName() {
        return "StraightStrategy";
    }

    @Override
    public void setStrategy(IUpdateStrategy strategy){}

    @Override
    public IUpdateStrategy getStrategy(){
        return null;
    }

    /**
     * Update the ball state in the ball world
     * @param context The ball to update
     */
    public void updateState(APaintObject context) {
        // Get the velocity from the Ball object
        // Then call the nextLocation to change the location
        Point vel = context.getVelocity();
        // Since it is straight strategy, the ball will only move horizontally
        vel.y = 0;
        context.nextLocation(vel.x, vel.y);
    }
}
