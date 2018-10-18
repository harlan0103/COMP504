package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paint.Ball;

import java.awt.*;

/**
 * LoopStrategy
 * Return a Ball will move horizontally but no collision
 * */
public class LoopStrategy implements IUpdateStrategy {
    private static IUpdateStrategy singleton;
    /**
     * Constructor
     */
    public LoopStrategy() {
    }

    // Create a singleton
    public static IUpdateStrategy makeStrategy(){
        if(singleton == null){
            singleton = new LoopStrategy();
        }
        return singleton;
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
    public void updateState(Ball context) {
        Point vel = context.getVelocity();
        // Vertically move through the wall
        vel.x = 0;
        checkLoop(context);
        context.nextLocation(vel.x, vel.y);
    }

    /**
     * Check if ball is collide
     * If collide then set location to the top of the canvas
     * */
    public void checkLoop(Ball context){
        //Get the collide status
        boolean collideStatus = context.getCollision();
        Point loc;
        if(collideStatus == true){
            // If collide the wall
            // Set the ball location at the top of the canvas
            //int radius = context.getSize();
            Point oldLoc = context.getLocation();
            loc = new Point(oldLoc.x, context.getRadius());
            context.setLocation(loc);
        }
    }
}
