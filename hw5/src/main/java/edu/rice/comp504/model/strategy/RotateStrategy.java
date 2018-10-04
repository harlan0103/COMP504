package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paintobj.AFish;
import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.paintobj.CompositeObject;

import java.awt.*;

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
    public void updateState(APaintObject context) {
        Point vel = context.getVelocity();
        if(context.getType().equals("CompositeObject")){
            // This is a composite object
            APaintObject[] arr = ((CompositeObject) context).getChildren();
            for(APaintObject child : arr){
                if(child.getType().equals("Fish")){
                    ((AFish) child).setAngle();
                }
                else{
                    // Override the invalid composite velocity
                    vel = child.getVelocity();
                    child.nextLocation(vel.x, vel.y);
                    // Then call rotate function
                    child.rotate(Math.PI/10);
                }
            }
        }
        else{
            if(context.getType().equals("Fish")){
                ((AFish) context).setAngle();
            }
            else{
                context.nextLocation(vel.x, vel.y);
                // Then call rotate function
                context.rotate(Math.PI/10);
            }
        }
    }
}
