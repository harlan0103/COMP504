package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.MovingLine;

import java.awt.*;

/**
 * Vertical strategy moves the line to the right only in the y direction
 */
public class VerticalStrategy implements IUpdateStrategy {
    private static IUpdateStrategy singleton;

    /**
     * Constructor
     */
    private VerticalStrategy() {

    }

    /**
     * Only makes 1 vertical strategy
     * @return The vertical strategy
     */
    public static IUpdateStrategy makeStrategy() {
        if (singleton == null ) {
            singleton = new VerticalStrategy();
        }
        return singleton;
    }

    /**
     * Get the strategy name
     * @return strategy name
     */
    public String getName() {
        return "vertical";
    }

    /**
     * Update the ball state in the ball world
     * @param context The ball to update
     */
    public void updateState(MovingLine context) {
        Point vel = context.getVelocity();
        context.nextLocation(0, vel.y);
    }
}
