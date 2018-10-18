package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.paint.Ball;
import edu.rice.comp504.model.strategy.IInteractStrategy;
import edu.rice.comp504.model.strategy.IUpdateStrategy;

/**
 * SwitchCmd
 * Switch the ball strategy based on user selection
 * */
public class SwitchCmd implements IBallCmd {
    private IUpdateStrategy strategy;
    private IInteractStrategy istrategy;

    public SwitchCmd(IUpdateStrategy strategy, IInteractStrategy istrategy){
        this.istrategy = istrategy;
        this.strategy = strategy;
    }

    /*
     * MoveShapeCms controls shape moving
     * */
    @Override
    public void execute(Ball context){
        executeStrategySwitch(context);
    }

    public void executeStrategySwitch(Ball context){
        if(context.getUpdateStrategy().getName().equals("SwitcherStrategy")){
            // Check if the current ball is a SwitcherShape
            context.getUpdateStrategy().setStrategy(strategy);
        }
        if(context.getInteractStrategy().getName().equals("switcherInteract")){
            // Check if the current ball is a SwitcherInteractStrategy
            context.getInteractStrategy().setStrategy(istrategy);
        }
    }
}

