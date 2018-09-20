package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.ball.Ball;

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

    /*
     * Update the ball state in the ball world
     * */
    public void updateState(Ball context) {
        // speed Change strategy is to change ball speed
        // Give ball a new speed when collide the wall
        boolean collideState = context.isCollide();
        Point vel = context.getVelocity();

        if(collideState == true){
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
