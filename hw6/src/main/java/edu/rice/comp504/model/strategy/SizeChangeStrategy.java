package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paint.Ball;

import java.awt.*;
import java.util.Random;

/**
 * SizeChangeStrategy
 * Return a Ball that change the radius while moving
 * */
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
    public void updateState(Ball context) {
        // Change the shape size during the each update time
        Point vel = context.getVelocity();
        Random random = new Random();
        // Randomize a new side
        int side = random.nextInt(50) + 10;
        context.setRadius(side);
        context.nextLocation(vel.x, vel.y);
    }
}