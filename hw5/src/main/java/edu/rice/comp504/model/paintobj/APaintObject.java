package edu.rice.comp504.model.paintobj;

import edu.rice.comp504.model.cmd.IPaintObjCmd;
import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * The APaintObject class provides an abstract representation of all the objects
 * in the ShapeWorld.
 */
public abstract class APaintObject implements Observer {
    Point loc;
    Point vel;
    IUpdateStrategy strategy;
    String color;
    String type;
    Point dims;
    int size;

    // collide is to check if the object is collide to the wall
    boolean collide;

    /**
     * Constructor
     * @param loc  The location of the paintable object on the canvas
     * @param vel  The object velocity
     * @param color  The object color
     * @param type  The object type (e.g. ball, pentagon)
     * @param strategy  The object update strategy
     */
    public APaintObject(Point loc, Point vel, String color, String type, IUpdateStrategy strategy){
        this.loc = loc;
        this.vel = vel;
        this.color = color;
        this.type = type;
        this.strategy = strategy;
        // Initialize collide status to false;
        this.collide = false;
    }

    /**
     * Rotate the paintable object
     * @param angle  The angle to rotate the paintable object
     */
    public abstract void rotate(double angle);

    /**
     * Detects collision between a paint and a wall in the paint world.  Change direction if paint collides with a wall.
     * @param dims  The canvas dimensions
     */
    public abstract void collision(Point dims);

    /**
     * Get the paintable object type
     * @return The paintable object type
     */
    public String getType() {
        // return the type name
        return this.type;
    }

    public void setType(String type){
        this.type = type;
    }

    /**
     * Get the paint location in the paint world.
     * @return The paint location.
     */
    public Point getLocation() {
        // Return the location point
        return this.loc;
    }

    /**
     * Set the paint location in the canvas.  The origin (0,0) is the top left corner of the canvas.
     * @param loc  The paint coordinate.
     */
    public void setLocation(Point loc) {
        // Set the location to a new loc point
        this.loc = loc;
    }

    /**
     * Get the paintable object color
     * @return The paintable object color
     */
    public String getColor() {
        // Return the color
        return this.color;
    }

    /**
     * Set the paintable object color
     * @param color The new color
     */
    public void setColor(String color) {
        // Set the color to a new color
        this.color = color;
    }

    /*
    * Return the dims point
    * */
    public Point getDims(){
        return this.dims;
    }
    /**
     * Compute the next location of the paint in the paint world given the velocity
     * @param velX
     * @param velY
     */
    public abstract void nextLocation(int velX, int velY);

    /**
     * Get the velocity of the paint.
     * @return The paint velocity
     */
    public Point getVelocity() {
        // Return velocity point
        return this.vel;
    }

    public void setVelocity(Point vel){
        this.vel = vel;
    }

    /*
    * @getStrategy
    * Get the current strategy name
    * */
    public IUpdateStrategy getStrategy() {
        return this.strategy;
    }

    /**
     * Update the state of the paint using strategies associated with the paint.
     */
    public void update(Observable obs, Object o) {
        // Call the execute function to update shape status
        ((IPaintObjCmd) o).execute(this);
    }

    /*
    * @getCollision
    * Return the collide status
    * */
    public boolean getCollision(){
        return this.collide;
    }

    /*
    * @setCollide
    * Set the collide status to object
    * */
    public void setCollide(boolean collideStatus){
        this.collide = collideStatus;
    }

    /*
    * @setSize
    * Abstract method for shapes to change size
    * */
    public abstract void setSize(int newSize);

    /*
    * @getSize
    * Abstract method for shapes to get size
    * */
    public abstract int getSize();
}
