package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paint.Ball;

import java.awt.*;
import java.util.Random;

/**
 * colorChangeStrategy
 * Change ball color while movement
 * */
public class ColorChangeStrategy implements IUpdateStrategy {
    private static IUpdateStrategy singleton;
    /*
     * Constructor
     * */
    public ColorChangeStrategy(){

    }

    // Create a singleton
    public static IUpdateStrategy makeStrategy(){
        if(singleton == null){
            singleton = new ColorChangeStrategy();
        }
        return singleton;
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
    public void updateState(Ball context) {
        // Change color while ball movement
        Point vel = context.getVelocity();
        newColor(context);
        context.nextLocation(vel.x, vel.y);
    }

    /*
     * Randomize a new color
     * */
    public void newColor(Ball context){
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
}
