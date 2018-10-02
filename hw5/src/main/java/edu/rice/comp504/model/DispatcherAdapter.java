package edu.rice.comp504.model;


import edu.rice.comp504.model.cmd.MoveShapeCmd;
import edu.rice.comp504.model.cmd.SwitchCmd;
import edu.rice.comp504.model.paintobj.ABall;
import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.paintobj.ARectangle;
import edu.rice.comp504.model.strategy.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

/**
 * The dispatch adapter is periodically called upon to update the ShapeWorld
 */
public class DispatcherAdapter extends Observable {
    private Point dims;

    /**
     * Constructor
     */
    public DispatcherAdapter() {

    }

    /**
     * Set the canvas dimensions
     * @param dims The canvas width (x) and height (y)
     */
    public void setCanvasDims(Point dims) {
        // Set the new dimension point
        this.dims = dims;
    }

    /**
     * Get the canvas dimensions
     * @return The canvas dimensions
     */
    public Point getCanvasDims() {
        // Return the canvas dimension
        return this.dims;
    }

    /**
     * Call the update method on all the paintobj observers to update their position in the paintobj world
     */
    public void updateBallWorld() {
        // If there has an observer
        // Then notify that observer to set change
        if(countObservers() != 0) {
            setChanged();
            MoveShapeCmd moveCmd = new MoveShapeCmd(this.dims);
            notifyObservers(moveCmd);
        }
    }

    /**
     * Load a paintobj into the shape world
     * @param body  The REST request body has the strategy names.
     * @param type  The object type (e.g. ball, star, pentagon)
     * @return A paintobj
     */
    public APaintObject loadPaintObj(String body, String type, String switchType) {

        APaintObject newShape = null;

        //System.out.println(body);
        IUpdateStrategy strategy = getStrategy(body);

        // If there is no type selected
        if(type.equals("")){
            String[] nullShape = {"Ball", "Rectangle"};
            strategy = new NullStrategy();
            for(String s: nullShape){
                // We create an object for all shapes with Nullstrategy
                newShape = createRandomShape(s, strategy);
            }
        }else{  // Has selected options
            String[] shapeArr = type.split("\\s+");
            for(String s: shapeArr){
                // If it is the switcher shape
                if(switchType.equals("true")){
                    IUpdateStrategy switcherStrategy = new SwitcherStrategy(strategy);
                    newShape = createRandomShape(s, switcherStrategy);
                }
                else{
                    newShape = createRandomShape(s, strategy);
                }
            }
        }
        return newShape;
    }

    /*
     * @createRandomShape
     * Create shape based on the selected option
     * Pass-in type, color and strategy
     * */
    public APaintObject createRandomShape(String type, IUpdateStrategy strategy){
        // Create a APaintObject
        APaintObject newShape;
        Point dims = this.getCanvasDims();
        // If type is "Ball"
        if(type.equals("Ball")){
            newShape = ABall.makeABall(strategy, dims);
            addObserver(newShape);
            return newShape;
        }
        // If type is "Rectangle"
        else if(type.equals("Rectangle")){
            newShape = ARectangle.makeARectangle(strategy, dims);
            addObserver(newShape);
            return newShape;
        }
        else{
            return null;
        }
    }

    /**
     * Switch the strategy for all the switcher balls
     * @param body  The REST request body containing the new strategy.
     */
    public void switchStrategy(String body, String type) {

        if(!body.equals("") && !type.equals("")){
            ArrayList<String> typeList = new ArrayList<>();

            // Get the strategy
            IUpdateStrategy strategy = getStrategy(body);
            setChanged();


            String[] typeArray = type.split("\\s+");
            for(int i = 0; i < typeArray.length; i++){
                typeList.add(typeArray[i]);
            }
            SwitchCmd switchCmd = new SwitchCmd(strategy, typeList);
            notifyObservers(switchCmd);
        }
        else{
            System.out.println("No type selected and no strategy selected");
        }
    }

    /*
    * Get the strategy from selectors
    * */
    public IUpdateStrategy getStrategy(String body){
        // " StraightStrategy RotateStrategy ColorChangeStrategy"
        String[] strategies = body.split("\\s+");
        IUpdateStrategy[] sArray;

        if(strategies.length == 0){ // No strategy selected
            return new NullStrategy();
        }
        else if(strategies.length == 1){    // Single strategy
            IUpdateStrategy s = getStrategyName(strategies[0]);
            //System.out.println(strategies[0]);
            return s;
        }
        else{   // More than one strategies has been selected
            sArray = new IUpdateStrategy[strategies.length];
            for(int i = 0; i < strategies.length; i++){
                IUpdateStrategy s = getStrategyName(strategies[i]);
                //System.out.println(sArray[i].getName());
                sArray[i] = s;
                //System.out.println("sArray[i] :" + sArray[i].getName());
            }
            return new CompositeStrategy(sArray);
        }
    }

    /*
    * @getStrategyName()
    * Get strategy ame from array and return a new strategy
    * */
    public IUpdateStrategy getStrategyName(String strategyName){
        switch (strategyName){
            case "StraightStrategy":
                return StraightStrategy.makeStrategy();
            case "RotateStrategy":
                return new RotateStrategy();
            case "ColorChangeStrategy":
                return new ColorChangeStrategy();
            case "FlickerStrategy":
                return new FlickerStrategy();
            case "LoopStrategy":
                return new LoopStrategy();
            case "SizeChangeStrategy":
                return new SizeChangeStrategy();
            case "SpeedChangeStrategy":
                return new SpeedChangeStrategy();
            default:
                return new NullStrategy();
        }
    }

    public void clear(){
        deleteObservers();
    }
}
