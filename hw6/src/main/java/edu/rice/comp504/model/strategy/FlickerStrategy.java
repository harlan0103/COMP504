package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paint.Ball;

import java.awt.*;

/**
 * FlickerStrategy
 * Return a ball change color between #000000 and #B8B8B8
 * */
public class FlickerStrategy implements IUpdateStrategy {
    private static IUpdateStrategy singleton;
    /**
     * Constructor
     */
    public FlickerStrategy() {

    }

    // Create a singleton
    public static IUpdateStrategy makeStrategy(){
        if(singleton == null){
            singleton = new FlickerStrategy();
        }
        return singleton;
    }

    /**
     * Get the strategy name
     * @return strategy name
     */
    public String getName() {

        return "FlickerStrategy";
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
        // While ball is moving
        // Change color to black and then change back
        //System.out.println("FLICKER");
        Point vel = context.getVelocity();
        makeFlicker(context);
        context.nextLocation(vel.x, vel.y);
    }

    /**
     * Change color between #000000 and #B8B8B8
     * */
    public void makeFlicker(Ball context){
        String color = context.getColor();
        if(color.equals("#000000")){
            context.setColor("#B8B8B8");
        }
        else{
            context.setColor("#000000");
        }
    }
}