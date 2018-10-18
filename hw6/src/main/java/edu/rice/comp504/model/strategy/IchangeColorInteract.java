package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.DispatchAdapter;
import edu.rice.comp504.model.paint.Ball;

import java.util.Random;

/**
 * IchangeColorInteract strategy is the color change interact
 * When two balls collide, both all will change to a new random color
 * */
public class IchangeColorInteract implements IInteractStrategy{
    private static IInteractStrategy singleton;

    /**
     * Constructor
     * */
    public IchangeColorInteract(){

    }

    /**
     * Make singleton
     * */
    public static IInteractStrategy makeStrategy(){
        if(singleton == null){
            singleton = new IchangeColorInteract();
        }
        return singleton;
    }

    @Override
    public String getName(){
        return "colorChangeInteract";
    }

    @Override
    public void interact(Ball src, Ball dest, DispatchAdapter adapter){
        // Get a new color from newColor()
        // Set both src and dest ball a new color
        String newColor = newColor();
        src.setColor(newColor);
        dest.setColor(newColor);
    }

    /**
     * newColor return a random color
     * @Param context
     * */
    public String newColor(){
        String color;
        String r, g, b;
        Random random = new Random();
        r = Integer.toHexString(random.nextInt(256)).toUpperCase();
        g = Integer.toHexString(random.nextInt(256)).toUpperCase();
        b = Integer.toHexString(random.nextInt(256)).toUpperCase();

        r = r.length() == 1 ? "0" + r : r;
        g = g.length() == 1 ? "0" + g : g;
        b = b.length() == 1 ? "0" + b : b;
        color = "#" + r + g + b;
        return color;
    }

    @Override
    public void setStrategy(IInteractStrategy strategy){
    }

    @Override
    public IInteractStrategy getStrategy(){
        return null;
    }
}