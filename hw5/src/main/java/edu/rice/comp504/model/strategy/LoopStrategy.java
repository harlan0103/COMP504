package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.paintobj.CompositeObject;

import java.awt.*;

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
    public void updateState(APaintObject context) {
        Point vel = context.getVelocity();
        // Vertically move through the wall
        vel.x = 0;

        if(context.getType().equals("CompositeObject")){
            // This is a composite object
            APaintObject[] arr = ((CompositeObject) context).getChildren();
            for(APaintObject child : arr){
                // Override the invalid composite velocity
                vel.y = child.getVelocity().y;
                checkLoop(child);
                child.nextLocation(vel.x, vel.y);
            }
        }
        else{
            checkLoop(context);
            context.nextLocation(vel.x, vel.y);
        }
    }

    public void checkLoop(APaintObject context){
        //Get the collide status
        boolean collideStatus = context.getCollision();
        Point loc;
        if(collideStatus == true){
            // If collide the wall
            // Set the ball location at the top of the canvas
            //int radius = context.getSize();
            Point oldLoc = context.getLocation();
            if(context.getType().equals("Ball")){
                loc = new Point(oldLoc.x, context.getSize());
            }
            else{
                loc = new Point(oldLoc.x, 0);
            }
            context.setLocation(loc);
        }
    }
}
