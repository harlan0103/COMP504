package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.paintobj.CompositeObject;

import java.awt.*;
import java.util.Random;

public class SizeChangeStrategy implements IUpdateStrategy {
    private static IUpdateStrategy singleton;
    /*
     * Constructor
     * */
    public SizeChangeStrategy(){

    }

    // Create a singleton
    public static IUpdateStrategy makeStrategy(){
        if(singleton == null){
            singleton = new SizeChangeStrategy();
        }
        return singleton;
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
        // Change the shape size during the each update time
        Point vel = context.getVelocity();
        Random random = new Random();
        // Randomize a new side
        int side = random.nextInt(50) + 10;

        if(context.getType().equals("CompositeObject")){
            // This is a composite object
            APaintObject[] arr = ((CompositeObject) context).getChildren();
            for(APaintObject child : arr){
                // Override the invalid composite velocity
                vel = child.getVelocity();
                child.setSize(side);
                child.nextLocation(vel.x, vel.y);
            }
        }
        else{
            context.setSize(side);
            context.nextLocation(vel.x, vel.y);
        }
    }
}
