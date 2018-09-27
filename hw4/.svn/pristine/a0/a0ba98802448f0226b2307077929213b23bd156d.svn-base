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
    String switcherBall;
    int size;
    // Check for collide situation

    boolean collide;

    /*
    * Constructor without params
    * */
    public APaintObject(){

    }

    /**
     * Constructor
     * @param loc  The location of the paintable object on the canvas
     * @param vel  The object velocity
     * @param color  The object color
     * @param type  The object type (e.g. ball, pentagon)
     * @param strategy  The object update strategy
     */
    public APaintObject(Point loc, Point vel, String color, String type, IUpdateStrategy strategy, Point dims, int size, String switcherBall){
        this.loc = loc;
        this.vel = vel;
        this.color = color;
        this.type = type;
        this.strategy = strategy;
        this.dims = dims;
        this.size = size;
        this.switcherBall = switcherBall;
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
    * Get the size of shape
    * */
    public int getSize(){
        return this.size;
    }

    /*
    * Set the size of shape
    * */
    public void setSize(int size){
        this.size = size;
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
    public void nextLocation(int velX, int velY) {
        // Add velocity vectors to the current Location
        this.loc.x += velX;
        this.loc.y += velY;

    }

    /**
     * Get the velocity of the paint.
     * @return The paint velocity
     */
    public Point getVelocity() {
        // Return velocity point
        return this.vel;
    }

    /*
    * Get the switcherBall type
    * */
    public String getSwitcherBall(){
        return this.switcherBall;
    }

    public IUpdateStrategy getStrategy() {
        // Return the current strategy name
        return this.strategy;
    }

    /**
     * Update the state of the paint using strategies associated with the paint.
     */
    public void update(Observable obs, Object o) {
        // Call the execute function to update shape status
        collision(dims);
        ((IPaintObjCmd) o).execute(this);
    }

    /*
    * Function to check for if shape is collide with the wall
    * Return a true or false value
    * */
    public boolean checkForCollide(int variable){
        collide = false;
        // Get the width and height of the canvas and detect for the collision
        int width = dims.x;
        int height = dims.y;

        /*
         * Idea is that the loc is the center point of the ball
         * if loc.x + radius exceeds the canvas width, then ball collides the right wall
         * it is the collision situation
         * */
        int rightEdge = this.loc.x + variable;
        int leftEdge = this.loc.x - variable;
        int topEdge = this.loc.y - variable;
        int bottomEdge = this.loc.y + variable;

        // Check for horizontal collision
        if(rightEdge >= width){
            // If collide, change the ball x velocity
            this.vel.x = -(this.vel.x);
            collide = true;
        }
        else if(leftEdge <= 0){
            this.vel.x = -(this.vel.x);
            collide = true;
        }

        // Check for vertical collision
        if(topEdge <= 0){
            // If collide, change the ball y velocity as well
            this.vel.y = -(this.vel.y);
            collide = true;
        }
        else if(bottomEdge >= height){
            this.vel.y = -(this.vel.y);
            collide = true;
        }
        // Return the collide status
        return collide;
    }

    /*
    * Return the collide situation
    * */
    public boolean getCollide(){
        return this.collide;
    }

}
