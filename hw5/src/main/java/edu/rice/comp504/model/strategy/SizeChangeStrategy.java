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
        // Change the shape size during the each update time
        Point vel = context.getVelocity();
        Random random = new Random();
        // Randomize a new side
        int side = random.nextInt(50) + 10;
        context.setSize(side);
        context.nextLocation(vel.x, vel.y);
    }
}
