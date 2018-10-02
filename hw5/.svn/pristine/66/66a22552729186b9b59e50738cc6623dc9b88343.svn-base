package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paintobj.APaintObject;

import java.awt.*;

public class FlickerStrategy implements IUpdateStrategy {
    private static StraightStrategy INSTANCE;
    /**
     * Constructor
     */
    public FlickerStrategy() {

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
    public void updateState(APaintObject context) {
        // While ball is moving
        // Change color to black and then change back
        //System.out.println("FLICKER");
        String color = context.getColor();
        if(!color.equals("#000000")){
            context.setColor("#000000");
        }
        else{
            context.setColor("#00FFBF");
        }
        Point vel = context.getVelocity();
        context.nextLocation(vel.x, vel.y);
    }
}
