package edu.rice.comp504.model.paintobj;

import edu.rice.comp504.model.cmd.IPaintObjCmd;
import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public abstract class APaintObject implements Observer {
    Point loc;
    Point vel;
    IUpdateStrategy strategy;
    String color;
    String type;

    /**
     * Constructor
     * @param loc  The location of the paintable object on the canvas
     * @param vel  The object velocity
     * @param strategy  The object update strategy
     */
    public APaintObject(Point loc, Point vel, String color, String type, IUpdateStrategy strategy){
        this.loc = loc;
        this.vel = vel;
        this.color = color;
        this.type = type;
        this.strategy = strategy;


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
    public abstract boolean collision(Point dims);



    /**
     * Get the paintable object type
     * @return The paintable object type
     */
    public String getType() {
        return type;
    }

    /**
     * Get the paint location in the paint world.
     * @return The paint location.
     */
    public Point getLocation() { return loc; }


    /**
     * Set the paint location in the canvas.  The origin (0,0) is the top left corner of the canvas.
     * @param loc  The paint coordinate.
     */
    public void setLocation(Point loc) {
        this.loc = loc;
    }

    /**
     * Check if the paint object is colorable.  For example, images are not colorable and would return false.
     */
    public boolean isColorable() {
        return false;
    }

    /**
     * Get the paintable object color
     * @return The paintable object color
     */
    public String getColor() {
        return color;
    }

    /**
     * Set the paintable object color
     * @param color The new color
     */
    public void setColor(String color) {
        if (isColorable())
            this.color = color;
    }

    /**
     * Compute the next location of the paint in the paint world given the velocity
     * @param velX
     * @param velY
     */
    public void nextLocation(int velX, int velY) {
        loc.x += velX;
        loc.y += velY;
    }

    /**
     * Get the velocity of the paint.
     * @return The paint velocity
     */
    public  Point getVelocity() { return vel; }

    public IUpdateStrategy getStrategy() { return strategy; }

    /**
     * Update the state of the paint using strategies associated with the paint.
     */
    public void update(Observable obs, Object o) {
        ((IPaintObjCmd) o).execute(this);
    }

}
