package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.paintobj.CompositeObject;

import java.awt.*;
import java.util.Random;

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
    public void updateState(APaintObject context) {
        // Color Change strategy is to change ball color randomly
        // First check if the ball is collided
        // If collide then change the color of the ball
        Point vel = context.getVelocity();
        // Check if it is a composite object
        if(context.getType().equals("CompositeObject")){
            // This is a composite object
            APaintObject[] arr = ((CompositeObject) context).getChildren();
            for(APaintObject child : arr){
                // Override the invalid composite velocity
                vel = child.getVelocity();
                newColor(child);
                child.nextLocation(vel.x, vel.y);
            }
        }
        else{
            newColor(context);
            context.nextLocation(vel.x, vel.y);
        }
    }

    /*
    * Randomize a new color
    * */
    public void newColor(APaintObject context){
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