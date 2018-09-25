package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.MovingLine;


/**
 * Composite strategy is comprised of the Horizontal and Vertical strategies
 */
public class CompositeStrategy implements IUpdateStrategy {
    final private IUpdateStrategy[] children = {HorizontalStrategy.makeStrategy(), VerticalStrategy.makeStrategy()};

    /**
     * Constructor
     */
    public  CompositeStrategy() {

    }

    /**
     * Get the strategy name
     * @return strategy name
     */
    public String getName() {
        return "composite";
    }

    /**
     * Update the ball state in the ball world
     * @param context The ball to update
     */
    public void updateState(MovingLine context) {
        for (IUpdateStrategy child: children) {
            child.updateState(context);
        }
    }
}
