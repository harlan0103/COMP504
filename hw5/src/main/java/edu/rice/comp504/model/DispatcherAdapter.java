package edu.rice.comp504.model;


import edu.rice.comp504.model.cmd.MoveShapeCmd;
import edu.rice.comp504.model.cmd.SwitchCmd;
import edu.rice.comp504.model.paintobj.*;
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
        if(type.equals("") || type.equals("unknown") || body.equals("")){
            // Adding new shapes to the nullShape array
            String[] nullShape = {"Ball", "Rectangle", "Fish", "Triangle", "Rectangle", "Diamond", "Pentagon", "Hexagon", "Octagon"};
            strategy = new NullStrategy();
            // Create a APaintObject array
            APaintObject[] children = new APaintObject[9];
            int index = 0;
            for(String s: nullShape){
                // We create an object for all shapes with null Strategy
                newShape = createRandomShape(s, strategy);
                //newShape.setType("CompositeObject");
                children[index] = newShape;
                index++;
            }
            // Create the Composite shape
            APaintObject composite = new CompositeObject(strategy, children);
            addObserver(composite);
            return composite;
        }else{  // Has selected options
            String[] shapeArr = type.split("\\s+");
            // If only selected one shape
            if(shapeArr.length == 1){
                if(switchType.equals("true")){
                    IUpdateStrategy switcherStrategy = new SwitcherStrategy(strategy);
                    newShape = createRandomShape(shapeArr[0], switcherStrategy);
                }
                else{
                    newShape = createRandomShape(shapeArr[0], strategy);
                }
                addObserver(newShape);
                return newShape;
            }
            else{
                // selected object more than one, create composite object
                APaintObject[] compositeArr = new APaintObject[shapeArr.length];
                // Check for the strategy, then create shape
                if(switchType.equals("true")){
                    IUpdateStrategy switcher = new SwitcherStrategy(strategy);
                    // Update strategy to switcher strategy
                    strategy = switcher;
                }
                // Create an index for compositeArr
                int index = 0;
                for(String s: shapeArr){
                    newShape = createRandomShape(s, strategy);
                    compositeArr[index] = newShape;
                    index++;
                }

                APaintObject composite = new CompositeObject(strategy, compositeArr);
                addObserver(composite);
                return composite;
            }
        }
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
            //addObserver(newShape);
            return newShape;
        }
        // If type is "Rectangle"
        else if(type.equals("Rectangle")){
            newShape = ARectangle.makeARectangle(strategy, dims);
            //addObserver(newShape);
            return newShape;
        }
        // If type is "Fish"
        else if(type.equals("Fish")){
            // now we create a new fish object
            newShape = AFish.makeAFish(strategy, dims);
            //addObserver(newShape);
            return newShape;
        }
        // If type is "Diamond"
        else if(type.equals("Diamond")){
            // now we create a new fish object
            newShape = ADiamond.makeDiamond(strategy, dims);
            //addObserver(newShape);
            return newShape;
        }
        // If type is "Triangle"
        else if(type.equals("Triangle")){
            // now we create a new fish object
            newShape = ATriangle.makeTriangle(strategy, dims);
            //addObserver(newShape);
            return newShape;
        }
        // If type is "Hexagon"
        else if(type.equals("Hexagon")){
            // now we create a new fish object
            newShape = AHexagon.makeHexagon(strategy, dims);
            //addObserver(newShape);
            return newShape;
        }
        else if(type.equals("Octagon")){
            // now we create a new fish object
            newShape = AOctagon.makeOctagon(strategy, dims);
            //addObserver(newShape);
            return newShape;
        }
        else if(type.equals("Pentagon")){
            // now we create a new fish object
            newShape = APentagon.makePentagon(strategy, dims);
            //addObserver(newShape);
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

    public void clear(){
        deleteObservers();
    }
}
