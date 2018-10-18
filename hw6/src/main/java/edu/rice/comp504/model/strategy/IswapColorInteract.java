package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.DispatchAdapter;
import edu.rice.comp504.model.paint.Ball;

/**
 * IeatInteract strategy is the eat interact strategy
 * When two balls collide, balls will switch their color
 * */
public class IswapColorInteract implements IInteractStrategy{
    private static IInteractStrategy singleton;

    /**
     * Constructor
     * */
    public IswapColorInteract(){

    }

    /**
     * Make singleton
     * */
    public static IInteractStrategy makeStrategy(){
        if(singleton == null){
            singleton = new IswapColorInteract();
        }
        return singleton;
    }

    @Override
    public String getName(){
        return "swapColorInteract";
    }

    @Override
    public void interact(Ball src, Ball dest, DispatchAdapter adapter){
        String srcColor = src.getColor();
        String destColor = dest.getColor();
        src.setColor(destColor);
        dest.setColor(srcColor);
    }

    @Override
    public void setStrategy(IInteractStrategy strategy){
    }

    @Override
    public IInteractStrategy getStrategy(){
        return null;
    }
}
