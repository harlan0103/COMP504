package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.DispatchAdapter;
import edu.rice.comp504.model.paint.Ball;

/**
 * IeatInteract strategy is the eat interact strategy
 * When two balls collide, The ball with larger radius will "eat" the ball with smaller radius
 * */
public class IeatInteract implements IInteractStrategy{
    private static IInteractStrategy singleton;

    /**
     * Constructor
     * */
    public IeatInteract(){

    }

    /**
     * Make singleton
     * */
    public static IInteractStrategy makeStrategy(){
        if(singleton == null){
            singleton = new IeatInteract();
        }
        return singleton;
    }

    @Override
    public String getName(){
        return "eatInteract";
    }

    @Override
    public void interact(Ball src, Ball dest, DispatchAdapter adapter){
        int srcRadius = src.getRadius();
        int destRadius = src.getRadius();
        if(srcRadius > destRadius){
            adapter.deleteObserver(dest);
        }
        else{
            adapter.deleteObserver(src);
        }
    }

    @Override
    public void setStrategy(IInteractStrategy strategy){
    }

    @Override
    public IInteractStrategy getStrategy(){
        return null;
    }
}
