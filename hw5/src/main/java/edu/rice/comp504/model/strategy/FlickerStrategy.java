package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.paintobj.CompositeObject;

import java.awt.*;

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
    public void updateState(APaintObject context) {
        // While ball is moving
        // Change color to black and then change back
        //System.out.println("FLICKER");
        Point vel = context.getVelocity();
        // Check if it is a composite object
        if(context.getType().equals("CompositeObject")){
            // This is a composite object
            APaintObject[] arr = ((CompositeObject) context).getChildren();
            for(APaintObject child : arr){
                // Override the invalid composite velocity
                vel = child.getVelocity();
                makeFlicker(child);
                child.nextLocation(vel.x, vel.y);
            }
        }
        else{
            makeFlicker(context);
            context.nextLocation(vel.x, vel.y);
        }
    }

    public void makeFlicker(APaintObject context){
        String color = context.getColor();
        if(color.equals("#000000")){
            context.setColor("#B8B8B8");
        }
        else{
            context.setColor("#000000");
        }
    }
}
