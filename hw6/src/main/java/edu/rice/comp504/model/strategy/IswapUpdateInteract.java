package edu.rice.comp504.model.strategy;


import edu.rice.comp504.model.DispatchAdapter;
import edu.rice.comp504.model.paint.Ball;

/**
 * IeatInteract strategy is the eat interact strategy
 * When two balls collide, two balls will swap their update strategy
 * */
public class IswapUpdateInteract implements IInteractStrategy{
    private static IInteractStrategy singleton;

    /**
     * Constructor
     * */
    public IswapUpdateInteract(){

    }

    /**
     * Make singleton
     * */
    public static IInteractStrategy makeStrategy(){
        if(singleton == null){
            singleton = new IswapUpdateInteract();
        }
        return singleton;
    }

    @Override
    public String getName(){
        return "swapColorInteract";
    }

    @Override
    public void interact(Ball src, Ball dest, DispatchAdapter adapter){
        IUpdateStrategy srcStrategy = src.getUpdateStrategy();
        IUpdateStrategy destStrategy = dest.getUpdateStrategy();
        src.setUpdateStrategy(destStrategy);
        dest.setUpdateStrategy(srcStrategy);
    }

    @Override
    public void setStrategy(IInteractStrategy strategy){
    }

    @Override
    public IInteractStrategy getStrategy(){
        return null;
    }
}



