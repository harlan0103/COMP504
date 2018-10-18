package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.DispatchAdapter;
import edu.rice.comp504.model.paint.Ball;

/**
 * IeatInteract strategy is the eat interact strategy
 * When two balls collide, balls will switch their radius
 * */
public class IswapRadiusInteract implements IInteractStrategy{
    private static IInteractStrategy singleton;

    /**
     * Constructor
     * */
    public IswapRadiusInteract(){

    }

    /**
     * Make singleton
     * */
    public static IInteractStrategy makeStrategy(){
        if(singleton == null){
            singleton = new IswapRadiusInteract();
        }
        return singleton;
    }

    @Override
    public String getName(){
        return "swapRadiusInteract";
    }

    @Override
    public void interact(Ball src, Ball dest, DispatchAdapter adapter){
        // Swap the radius of two balls
        int srcRadius = src.getRadius();
        int destRadius = dest.getRadius();
        src.setRadius(destRadius);
        dest.setRadius(srcRadius);
    }

    @Override
    public void setStrategy(IInteractStrategy strategy){
    }

    @Override
    public IInteractStrategy getStrategy(){
        return null;
    }
}

