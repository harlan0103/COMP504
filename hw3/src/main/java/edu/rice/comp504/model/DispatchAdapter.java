package edu.rice.comp504.model;

import edu.rice.comp504.model.ball.*;
import edu.rice.comp504.model.strategy.*;

import java.awt.*;
import java.util.Observable;
import java.util.Random;

/**
 * The dispatch adapter is used to communicates with the model to periodically update all the balls
 * in the BallWorld using its notifyObservers method
 */
public class DispatchAdapter extends Observable {
    Point dims;

    /**
     * Constructor
     */
    public DispatchAdapter() {

    }

    /**
     * Set the canvas dimensions
     *
     * @param dims The canvas width (x) and height (y)
     */
    public void setCanvasDims(Point dims) {
        // Retrieve dimension data and set to the dims Point
        this.dims = dims;
    }

    /**
     * Get the canvas dimensions
     *
     * @return The canvas dimensions
     */
    public Point getCanvasDims() {
        // Return the dims Point
        return this.dims;
    }

    /**
     * Call the update method on all the ball observers to update their position in the ball world
     */
    public void updateBallWorld() {
        // If there has an observer
        // Then notify that observer to set change
        if (countObservers() != 0) {
            setChanged();
            notifyObservers();
        }
    }


    /**
     * Load a ball into the ball world
     *
     * @param body The REST request body has the strategy names.
     * @return A ball
     */
    public Ball loadBall(String body, boolean isSwitcherBall) {
        Ball newBall;

        // Get canvas width and height
        Point dims = getCanvasDims();

        int dimWidth = dims.x;
        int dimHeight = dims.y;

        // Create random size, radius and velocity
        Random random = new Random();

        // Radius cannot be 0
        int radius = random.nextInt(50) + 10;

        // The left boundary of a ball loc is 0 + radius
        // The right boundary of a ball loc is is width - radius
        // Same as the top boundary and bottom boundary
        int locX = random.nextInt(dimWidth - 2 * radius) + radius;
        int locY = random.nextInt(dimHeight - 2 * radius) + radius;

        // Create a random velocity Point
        int velX = random.nextInt(50) + 20;
        int velY = random.nextInt(50) + 20;

        Point loc = new Point(locX, locY);
        Point vel = new Point(velX, velY);

        // Get the selected strategy of body
        IUpdateStrategy s = getStrategy(body);
        // Then create the new ball
        newBall = new Ball(loc, radius, vel, "#00FFBF", s, dims, isSwitcherBall);

        addObserver(newBall);
        return newBall;
    }


    /**
     * Switch the strategy for all the switcher balls
     * @param body  The REST request body containing the new strategy.
     */
    public void switchStrategy(String body) {
        //System.out.println("ask for switch");
        IUpdateStrategy strategy = getStrategy(body);
        setChanged();
        notifyObservers(strategy);
    }

    /**
     * Delete all observers
     * */
    public void clear(){
        deleteObservers();
    }

    /*
     * Get the strategy from body passed from html selector
     * */
    public IUpdateStrategy getStrategy(String body){
        // Example: body = "strategy=+RotationStrategy+SizeChangeStrategy"
        // Parse the body
        String[] strategies = body.split("\\+");
        IUpdateStrategy[] sArray;
        if(strategies.length == 1 ){    // Only contains "strategy="
            return new NullStrategy();
        }
        else if(strategies.length == 2){    // Has one strategy
            IUpdateStrategy s = getStrategyName(strategies[1]);
            return s;
        }
        else{   // More than one strategy has been selected
            sArray = new IUpdateStrategy[strategies.length - 1];
            for(int i = 1; i < strategies.length; i++){
                // Add each strategy into the strategy array
                IUpdateStrategy s = getStrategyName(strategies[i]);
                sArray[i-1] = s;
            }
            // Return the compositeStrategy
            return new CompositeStrategy(sArray);
        }
    }


    /*
     * Get strategy name from array and return a new strategy
     * */
    public IUpdateStrategy getStrategyName(String strategyName){
        switch (strategyName){
            case "StraightStrategy":
                return new StraightStrategy();
            case "RotationStrategy":
                return new RotateStrategy();
            case "SizeChangeStrategy":
                return new SizeChangeStrategy();
            case "ColorChangeStrategy":
                return new ColorChangeStrategy();
            case "SpeedChangeStrategy":
                return new SpeedChangeStrategy();
            case "FlickerStrategy":
                return new FlickerStrategy();
            case "LoopStrategy":
                return new LoopStrategy();
            default:
                return new NullStrategy();
        }
    }
}