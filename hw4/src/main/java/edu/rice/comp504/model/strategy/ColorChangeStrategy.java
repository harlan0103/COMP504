package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paintobj.APaintObject;

import java.awt.*;
import java.util.Random;

public class ColorChangeStrategy implements IUpdateStrategy {

    /*
     * Constructor
     * */
    public ColorChangeStrategy(){

    }

    /*
     * Return the strategy name
     * */
    @Override
    public String getName() {
        return "ColorChangeStrategy";
    }

    @Override
    public IUpdateStrategy getStrategy(){
        return null;
    }

    @Override
    public void setStrategy(IUpdateStrategy strategy){}
    /*
     * Update the ball state in the ball world
     * */
    @Override
    public void updateState(APaintObject context) {
        // Color Change strategy is to change ball color randomly
        // First check if the ball is collided
        // If collide then change the color of the ball

        //boolean collideState = context.isCollide();
        boolean collideState = true;

        Point vel = context.getVelocity();
        // When collide, we change the color
        if (collideState == true) {
            String oldColor = context.getColor();
            String newColor = oldColor;
            while(oldColor.equals(newColor)){
                String r, g, b;
                Random random = new Random();
                r = Integer.toHexString(random.nextInt(256)).toUpperCase();
                g = Integer.toHexString(random.nextInt(256)).toUpperCase();
                b = Integer.toHexString(random.nextInt(256)).toUpperCase();

                r = r.length() == 1 ? "0" + r : r;
                g = g.length() == 1 ? "0" + g : g;
                b = b.length() == 1 ? "0" + b : b;
                newColor = "#" + r + g + b;
            }
            context.setColor(newColor);
        }
        context.nextLocation(vel.x, vel.y);
    }
}