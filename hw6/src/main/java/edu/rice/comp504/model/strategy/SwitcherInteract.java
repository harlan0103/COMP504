package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.DispatchAdapter;
import edu.rice.comp504.model.paint.Ball;

/**
 * IeatInteract strategy is the eat interact strategy
 * When two balls collide, The ball with larger radius will "eat" the ball with smaller radius
 * */
public class SwitcherInteract implements IInteractStrategy{
    private IInteractStrategy interactStrategy;

    /**
     * Constructor
     * */
    public SwitcherInteract(IInteractStrategy interact){
        this.interactStrategy = interact;
    }

    @Override
    public String getName(){
        return "switcherInteract";
    }

    @Override
    public void interact(Ball src, Ball dest, DispatchAdapter adapter){
        // The strategy in the SwitcherStrategy
        // Will call its own updateState method
        interactStrategy.interact(src, dest, adapter);
    }

    @Override
    public void setStrategy(IInteractStrategy strategy){
        this.interactStrategy = strategy;
    }

    @Override
    public IInteractStrategy getStrategy(){
        return this.interactStrategy;
    }

}
