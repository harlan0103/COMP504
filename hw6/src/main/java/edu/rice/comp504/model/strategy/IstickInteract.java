package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.DispatchAdapter;
import edu.rice.comp504.model.paint.Ball;

import java.awt.*;

/**
 * IeatInteract strategy is the eat interact strategy
 * When two balls collide, two balls will stick together and move together
 * */
public class IstickInteract implements IInteractStrategy {
    private static IInteractStrategy singleton;

    /**
     * Constructor
     */
    public IstickInteract() {

    }

    /**
     * Make singleton
     */
    public static IInteractStrategy makeStrategy() {
        if (singleton == null) {
            singleton = new IstickInteract();
        }
        return singleton;
    }

    @Override
    public String getName() {
        return "stickInteract";
    }

    @Override
    public void interact(Ball src, Ball dest, DispatchAdapter adapter) {
        // When collide set velocity to 0 to stick to balls together
        src.setVelocity(new Point(0, 0));
        dest.setVelocity(new Point(0, 0));
    }

    @Override
    public void setStrategy(IInteractStrategy strategy){
    }

    @Override
    public IInteractStrategy getStrategy(){
        return null;
    }
}
