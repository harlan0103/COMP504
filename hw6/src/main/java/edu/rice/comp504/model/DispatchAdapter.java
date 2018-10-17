package edu.rice.comp504.model;

import edu.rice.comp504.model.cmd.IBallMoveCmd;
import edu.rice.comp504.model.cmd.SwitchCmd;
import edu.rice.comp504.model.paint.*;
import edu.rice.comp504.model.strategy.*;

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
            IBallMoveCmd moveCmd = new IBallMoveCmd(this.dims);
            notifyObservers(moveCmd);
        }
    }

    /**
     * Load a paint into the paint world
     * @param body  The REST request body has the strategy names.
     * @return A new ball
     */
    public Ball loadBall(String body, String switcher) {
        Ball newBall;
        // Get user selected strategy
        IUpdateStrategy strategy = getStrategy(body);
        System.out.println(strategy.getName());
        // Get canvas dimension
        Point dims = this.getCanvasDims();

        // If it is the switcher type ball
        if(switcher.equals("true")){
            IUpdateStrategy switcherStrategy = new SwitcherStrategy(strategy);
            newBall = Ball.makeBall(switcherStrategy, dims);
        }
        else{
            newBall = Ball.makeBall(strategy, dims);
        }
        addObserver(newBall);
        return newBall;
    }

    /**
     * Switch the strategy for all the switcher balls
     * @param body  The REST request body containing the new strategy.
     */
    public void switchStrategy(String body) {
        if(!body.equals("")){
            // Get the strategy
            IUpdateStrategy strategy = getStrategy(body);
            setChanged();
            SwitchCmd switchCmd = new SwitchCmd(strategy);
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

    /**
     * Clear all observers
     * */
    public void clear(){
        deleteObservers();
    }

}
