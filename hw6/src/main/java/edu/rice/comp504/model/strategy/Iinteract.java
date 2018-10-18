package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.DispatchAdapter;
import edu.rice.comp504.model.paint.Ball;

/**
 * Iinteract strategy is the default interact strategy
 * When two balls collide, they will only change the moving direction
 * And no further action
 * */
public class Iinteract implements IInteractStrategy{
    private static IInteractStrategy singleton;

    /**
     * Constructor
     * */
    public Iinteract(){

    }

    /**
     * Make singleton
     * */
    public static IInteractStrategy makeStrategy(){
        if(singleton == null){
            singleton = new Iinteract();
        }
        return singleton;
    }

    @Override
    public String getName(){
        return "interact";
    }

    @Override
    public void interact(Ball src, Ball dest, DispatchAdapter adapter){

    }

    @Override
    public void setStrategy(IInteractStrategy strategy){
    }

    @Override
    public IInteractStrategy getStrategy(){
        return null;
    }
}
