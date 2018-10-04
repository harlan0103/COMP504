package edu.rice.comp504.model.paintobj;

import edu.rice.comp504.model.cmd.IPaintObjCmd;
import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.awt.*;
import java.util.Observable;

/**
 * A vertical wall at a location within the canvas
 */
public class Wall extends APaintObject {
    private int len;

    /**
     * Constructor for Wall
     * @param loc The wall location.  The origin (0,0) is the upper left corner of the canvas.
     * @param vel The wall velocity.  The velocity is a vector with an x and y component.
     * @param len The vertical length of the wall.
     * @param strategy The wall strategy.
     */
    private Wall(Point loc, Point vel, int len, IUpdateStrategy strategy) {
        super(loc, vel, "black", "wall", strategy);
        this.len = len;
    }

    /**
     * Make a new paintobj
     * @return A paintobj.
     */
    public static Wall makeBall(IUpdateStrategy strategy, Point dims) {
        return new Wall(new Point((int)Math.floor(Math.random()* dims.x), (int)Math.floor(Math.random()* dims.y)),
                new Point(0, 0), (int)Math.floor(Math.random()*200), strategy);
    }

    /**
     * Get the paintobj color.
     * @return paintobj color
     */
    public String getColor() {return color; }

    /**
     * Set the paintobj color.
     * @param c The new paintobj color
     */
    public void setColor(String c) { color = c; }


    /**
     * Get the paintobj location in the paintobj world.
     * @return The paintobj location.
     */
    public Point getLocation() { return loc; }


    /**
     * Set the paintobj location in the canvas.  The origin (0,0) is the top left corner of the canvas.
     * @param loc  The paintobj coordinate.
     */
    public void setLocation(Point loc) {
        this.loc = loc;
    }

    /**
     * Compute the next location of the paintobj in the paintobj world given the velocity
     * @param velX
     * @param velY
     */
    public void nextLocation(int velX, int velY) {
        loc.x += velX;
        loc.y += velY;
    }

    /**
     * Get the velocity of the paintobj.
     * @return The paintobj velocity
     */
    public  Point getVelocity() { return vel; }


    /**
     * Get the length of the paintobj.
     * @return The paintobj length.
     */
    public int getLength() { return len; }

    /**
     * Set the length of the paintobj.
     * @param l The paintobj length.
     */
    public void setLength(int l) { len = l; }

    public IUpdateStrategy getStrategy() { return strategy; }

    /**
     * Detects collision between a paintobj and a wall in the paintobj world.  Change direction if paintobj collides with a wall.
     * @param dims  The canvas dimensions.  The canvas width (x) and canvas height (y).
     */
    public boolean collision(Point dims) {
        return false;
    }

    /**
     * Rotate the paintobj.
     * @param angle  The angle that determines how far to rotate the paintobj.
     */
    public void rotate(double angle) {
    }

    /**
     * Update the state of the paintobj using strategies associated with the paintobj.
     */
    public void update(Observable obs, Object o) {
        ((IPaintObjCmd)o).execute(this);
    }
}
