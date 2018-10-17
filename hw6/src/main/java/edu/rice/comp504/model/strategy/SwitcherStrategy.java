package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paint.Ball;

/**
 * SwitcherStrategy
 * Create a switcherStrategy for switcher ball
 * */
public class SwitcherStrategy implements IUpdateStrategy {
    private IUpdateStrategy strategy;

    /*
     * Need to pass in a strategy object
     * */
    public SwitcherStrategy(IUpdateStrategy strategy){
        this.strategy = strategy;
    }

    @Override
    public String getName(){
        return "SwitcherStrategy";
    }

    /*
     * Return the current strategy
     * */
    @Override
    public IUpdateStrategy getStrategy(){
        return this.strategy;
    }

    /*
     * Set a new strategy to SwitcherStrategy
     * */
    public void setStrategy(IUpdateStrategy strategy){
        this.strategy = strategy;
    }

    @Override
    public void updateState(Ball context){
        // The strategy in the SwitcherStrategy
        // Will call its own updateState method
        strategy.updateState(context);
    }
}
