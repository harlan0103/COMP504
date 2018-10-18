package edu.rice.comp504.model;

import edu.rice.comp504.model.cmd.IBallMoveCmd;
import edu.rice.comp504.model.cmd.SwitchCmd;
import edu.rice.comp504.model.paint.*;
import edu.rice.comp504.model.strategy.*;
import org.eclipse.jetty.websocket.api.SuspendToken;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Note: Balls are only accessed through using the observable-observer design pattern.  You should NOT create
 * a separate list of balls.
 */
public class DispatchAdapter extends Observable {
    private Point dims;

    /**
     * Constructor
     */
    public DispatchAdapter() {

    }

    /**
     * set changes for observers
     * */
    public void setChange(){
        this.setChanged();
    }

     /**
     * Set the canvas dimensions
     * @param dims The canvas width (x) and height (y)
     */
    public void setCanvasDims(Point dims) {
        this.dims = dims;
    }

    /**
     * Get the canvas dimensions
     * @return The canvas dimensions
     */
    public Point getCanvasDims() {
        return this.dims;
    }

    /**
     * Call the update method on all the paint observers to update their position in the paint world
     */
    public void updateBallWorld() {
        if(countObservers() != 0){
            setChanged();
            IBallMoveCmd moveCmd = new IBallMoveCmd(this.dims, this);
            notifyObservers(moveCmd);
        }
    }

    /**
     * Load a paint into the paint world
     * @param body  The REST request body has the strategy names.
     * @return A new ball
     */
    public Ball loadBall(String body, String interactBody, String switcher) {
        Ball newBall;
        // Get user selected strategy and interact strategy
        IUpdateStrategy strategy = getStrategy(body);
        IInteractStrategy interact = getInteractStrategy(interactBody);
        //System.out.println(strategy.getName());
        // Get canvas dimension
        Point dims = this.getCanvasDims();

        // If it is the switcher type ball
        if(switcher.equals("true")){
            IUpdateStrategy switcherStrategy = new SwitcherStrategy(strategy);
            IInteractStrategy interactStrategy = new SwitcherInteract(interact);
            newBall = Ball.makeBall(switcherStrategy, interactStrategy, dims);
        }

        else{
            newBall = Ball.makeBall(strategy, interact, dims);
        }
        addObserver(newBall);
        return newBall;
    }

    /**
     * Switch the strategy for all the switcher balls
     * @param body  The REST request body containing the new strategy.
     */
    public void switchStrategy(String body, String interactBody) {
        if(!body.equals("") || !interactBody.equals("")){
            // Get the strategy
            IUpdateStrategy strategy = getStrategy(body);
            IInteractStrategy interact = getInteractStrategy(interactBody);
            setChanged();
            SwitchCmd switchCmd = new SwitchCmd(strategy, interact);
            notifyObservers(switchCmd);
        }
    }

    /**
     * getStrategy()
     * @body
     * Get the strategy from selectors
     * */
    public IUpdateStrategy getStrategy(String body){
        String[] strategies = body.split("\\s+");
        IUpdateStrategy[] sArray;
        // If no strategy selected return NullStrategy
        if(strategies.length == 0){
            return new NullStrategy();
        }
        // If single strategy selected return that strategy
        else if(strategies.length == 1){    // Single strategy
            IUpdateStrategy s = getStrategyName(strategies[0]);
            return s;
        }
        // More than one strategies has been selected
        else{
            sArray = new IUpdateStrategy[strategies.length];
            for(int i = 0; i < strategies.length; i++){
                IUpdateStrategy s = getStrategyName(strategies[i]);
                sArray[i] = s;
            }
            return new CompositeStrategy(sArray);
        }
    }

    /**
     * getStrategyName()
     * @strategyName
     * Get strategy ame from array and return a new strategy
     * */
    public IUpdateStrategy getStrategyName(String strategyName) {
        switch (strategyName) {
            case "StraightStrategy":
                return StraightStrategy.makeStrategy();
            case "RotateStrategy":
                return RotateStrategy.makeStrategy();
            case "ColorChangeStrategy":
                return ColorChangeStrategy.makeStrategy();
            case "FlickerStrategy":
                return FlickerStrategy.makeStrategy();
            case "LoopStrategy":
                return LoopStrategy.makeStrategy();
            case "SizeChangeStrategy":
                return SizeChangeStrategy.makeStrategy();
            case "SpeedChangeStrategy":
                return SpeedChangeStrategy.makeStrategy();
            default:
                return new NullStrategy();
        }
    }

    public IInteractStrategy getInteractStrategy(String interactName){
        switch (interactName) {
            case "EatInteract ":
                return IeatInteract.makeStrategy();
            case "DestroyInteract ":
                return IdestroyInteract.makeStrategy();
            case "ChangeColorInteract ":
                return IchangeColorInteract.makeStrategy();
            case "SwapRadiusInteract ":
                return IswapRadiusInteract.makeStrategy();
            case "SwapColorInteract ":
                return IswapColorInteract.makeStrategy();
            case "StickInteract ":
                return IstickInteract.makeStrategy();
            case "SwapUpdateInteract ":
                return IswapUpdateInteract.makeStrategy();
            default:
                return new Iinteract();
        }
    }

    /**
     * Clear all observers
     * */
    public void clear(){
        deleteObservers();
    }

}
