package edu.rice.comp504.model.paintobj;


import edu.rice.comp504.model.cmd.IPaintObjCmd;
import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.awt.*;
import java.util.Observable;

public class Ball extends APaintObject{
    private int radius;

    /**
     * Constructor for Ball
     * @param loc The paintobj location.  The origin (0,0) is the upper left corner of the canvas.
     * @param radius  The paintobj radius.
     * @param vel The paintobj velocity.  The velocity is a vector with an x and y component.
     * @param color The paintobj color.
     * @param strategy The paint object strategy.
     */
    private Ball(Point loc, int radius, Point vel, String color, IUpdateStrategy strategy) {
        super(loc, vel, color, "ball", strategy);
        this.radius = radius;
    }

    /**
     * Make a new paintobj
     * @return A paintobj.
     */
    public static Ball makeBall(IUpdateStrategy strategy, Point dims) {
        return new Ball(new Point((int)Math.floor(Math.random()* dims.x), (int)Math.floor(Math.random()* dims.y)),
                (int)Math.floor(Math.random() * 40 + 10),
                new Point((int)Math.floor(Math.random() * 15 + 1), (int)Math.floor(Math.random() * 15) + 1),
                "blue", strategy);
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
     * Get the radius of the paintobj.
     * @return The paintobj radius.
     */
    public int getRadius() { return radius; }

    /**
     * Set the radius of the paintobj.
     * @param r The paintobj radius.
     */
    public void setRadius(int r) { radius = r; }

    public IUpdateStrategy getStrategy() { return strategy; }

    /**
     * Detects collision between a paintobj and a wall in the paintobj world.  Change direction if paintobj collides with a wall.
     * @param dims  The canvas dimensions.  The canvas width (x) and canvas height (y).
     */
    public boolean collision(Point dims) {
        // right wall collision
        if ((vel.x < 0) && ((loc.x - radius) <= 0)) {
            vel.x = vel.x * -1;
            return true;
        }
        // left wall collision
        else if ((vel.x > 0) && ((loc.x + radius) >= dims.x)) {
            vel.x = vel.x * -1;
            return true;
        }
        // top wall collision
        if ((vel.y < 0) && ((loc.y - radius) <= 0)) {
            vel.y = vel.y * -1;
            return true;
        }
        // bottom wall collision
        else if ((vel.y > 0) && ((loc.y + radius) >= dims.y)) {
            vel.y = vel.y * -1;
            return true;
        }

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
