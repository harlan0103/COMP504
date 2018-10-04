package edu.rice.comp504.model;

import edu.rice.comp504.model.paintobj.*;
import edu.rice.comp504.model.cmd.UpdateStateCmd;
import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.strategy.*;

import java.awt.*;
import java.util.LinkedList;
import java.util.Observable;

/**
 * This adapter triggers updates to the balls in the shape world
 */
public class DispatcherAdapter extends Observable {
    private Point dims;
    private LinkedList<Wall> iWalls; //Note: an array of walls does not fully using the observable - observer pattern

    /**
     * Constructor
     */
    public DispatcherAdapter() {
        iWalls = new LinkedList<>();
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
        return dims;
    }


    /**
     * Get the list of inner walls
     * @return  The inner wall list
     */
    public LinkedList<Wall> getInnerWalls() {
        return iWalls;
    }

    /**
     * Call the update method on all the paintobj observers to update their position in the paintobj world
     */
    public void updateBallWorld() {
        setChanged();
        notifyObservers(new UpdateStateCmd(dims, iWalls));
    }

    /**
     * Load either a ball or a wall into the shape world
     * @param type  The paintobj type (line, ball)
     * @return A paintobj
     */
    public APaintObject loadPaintObj(String type) {
        //TODO Add a new paint object to the shape world
        APaintObject shape = null;

        if(type.equals("ball")){
            IUpdateStrategy strategy = StraightStrategy.makeStrategy();
            shape = Ball.makeBall(strategy, getCanvasDims());
        }
        else if(type.equals("wall")){
            IUpdateStrategy strategy = NullStrategy.makeStrategy();
            shape = Wall.makeBall(strategy, getCanvasDims());
            this.iWalls.addLast((Wall) shape);
        }
        addObserver(shape);
        return shape;

    }

    public void clear(){
        this.iWalls.clear();
        deleteObservers();
    }
}
