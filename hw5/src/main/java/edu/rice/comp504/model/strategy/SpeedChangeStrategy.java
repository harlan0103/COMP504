package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paintobj.APaintObject;

import java.awt.*;
import java.util.Random;

public class SpeedChangeStrategy implements IUpdateStrategy {

    /*
     * Constructor
     * */
    public SpeedChangeStrategy(){

    }

    /*
     * Return the strategy name
     * */
    @Override
    public String getName() {
        return "SpeedChangeStrategy";
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
        // speed Change strategy is to change ball speed
        // Give ball a new speed when collide the wall
        //boolean collideState = context.collideStatus();
        boolean collideStatus = context.getCollision();
        Point vel = context.getVelocity();

        if(collideStatus == true){
            Random random = new Random();
            // Create differential of speed
            int diff = random.nextInt(10) + 10;
            int token = random.nextInt(9);
            // Use a token to decide slow down or accelerate
            if(token % 2 == 0){
                vel.x += diff;
                vel.y += diff;
            }
            else{
                vel.x -= diff;
                vel.y -= diff;
            }
            context.nextLocation(vel.x, vel.y);
        }
        context.nextLocation(vel.x, vel.y);
    }
}
