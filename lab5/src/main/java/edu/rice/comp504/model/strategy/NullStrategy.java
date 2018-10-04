package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paintobj.APaintObject;

public class NullStrategy implements IUpdateStrategy {
    private static IUpdateStrategy singleton;

    /**
     * Constructor
     */
    private NullStrategy() {

    }

    /**
     * Make a straight strategy.  There is only one (singleton).
     * @return
     */
    public static IUpdateStrategy makeStrategy() {
        if (singleton == null) {
            singleton = new NullStrategy();
        }

        return singleton;
    }

    /**
     * Get the strategy name
     * @return strategy name
     */
    public String getName() {
        return "null";
    }

    /**
     * Update the paintobj state in the paintobj world
     * @param context The paintobj to update
     */
    public void updateState(APaintObject context) {
        context.setColor("black");
    }
}
