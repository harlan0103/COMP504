package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.DispatchAdapter;
import edu.rice.comp504.model.paint.Ball;

/**
 * This interface is used to add a ball behavior through the use of a strategy
 */
public interface IUpdateStrategy {
    /*
     * Get the strategy name
     * */
    public String getName();

    /*
     * Set a new strategy
     * */
    public void setStrategy(IUpdateStrategy strategy);

    /*
     * Get the strategy
     * */
    public IUpdateStrategy getStrategy();

    /*
     * Update the line state
     * */
    public void updateState(Ball context);
}
