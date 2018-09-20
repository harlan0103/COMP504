package edu.rice.comp504.model.ball;

import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Represents one ball in the BallWorld. It implements the Observer class since there will be multiple
 * balls in the BallWorld that need to periodically update their location.
 */
public class Ball implements Observer {
    private Point loc;
    private String color;
    private int radius;
    private Point vel;
    private Point dims;
    private boolean collide;
    private IUpdateStrategy strategy;
    // Check if the ball is switch ball
    private boolean isSwitcherBall;

    /**
     * Constructor for Ball
     * @param loc The ball location.  The origin (0,0) is the upper left corner of the canvas.
     * @param radius  The ball radius.
     * @param vel The ball velocity.  The velocity is a vector with an x and y component.
     * @param color The ball color.
     * @param strategy The ball strategy determining ball behavior
     */
    public Ball(Point loc, int radius, Point vel, String color, IUpdateStrategy strategy, Point dims, boolean isSwitcherBall) {
        this.vel = vel;
        this.dims = dims;
        this.strategy = strategy;
        this.isSwitcherBall = isSwitcherBall;
        setColor(color);
        setLocation(loc);
        setRadius(radius);
    }

    /**
     * Get the ball color.
     * @return ball color
     */
    public String getColor() {

        return this.color;
    }

    /**
     * Set the ball color.
     * @param c The new ball color
     */
    public void setColor(String c) {
        // Set a new color
        this.color = c;
    }


    /**
     * Get the ball location in the ball world.
     * @return The ball location.
     */
    public Point getLocation() {
        // Return the location
        return this.loc;
    }


    /**
     * Set the ball location in the canvas.  The origin (0,0) is the top left corner of the canvas.
     * @param loc  The ball coordinate.
     */
    public void setLocation(Point loc) {
        // Set a new location
        this.loc = loc;
    }

    /**
     * Compute the next location of the ball in the ball world given the velocity
     * @param velX
     * @param velY
     */
    public void nextLocation(int velX, int velY) {
        this.loc.x += velX;
        this.loc.y += velY;

    }

    /**
     * Get the velocity of the ball.
     * @return The ball velocity
     */
    public  Point getVelocity() {
        return this.vel;
    }


    /**
     * Get the radius of the ball.
     * @return The ball radius.
     */
    public int getRadius() {
        // Return the radius
        return this.radius;
    }

    /**
     * Set the radius of the ball.
     * @param radius The ball radius.
     */
    public void setRadius(int radius) {
        // Set a new radius to the ball
        this.radius = radius;
    }

    /**
     * Detects collision between a ball and a wall in the ball world.  Change direction if ball collides with a wall.
     * @param dims  The canvas dimensions.  The canvas width (x) and canvas height (y).
     */
    public void collision(Point dims) {
        collide = false;
        // Get the width and height of the canvas and detect for the collision
        int width = dims.x;
        int height = dims.y;

        /*
         * Idea is that the loc is the center point of the ball
         * if loc.x + radius exceeds the canvas width, then ball collides the right wall
         * it is the collision situation
         * */
        int rightEdge = this.loc.x + this.radius;
        int leftEdge = this.loc.x - this.radius;
        int topEdge = this.loc.y - this.radius;
        int bottomEdge = this.loc.y + this.radius;

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

    }

    /**
     * Rotate the ball.
     * @param angle  The angle that determines how far to rotate the ball.
     */
    public void rotate(double angle) {
        int velX = vel.x;
        int velY = vel.y;
        this.vel.x = (int)Math.round(velX * Math.cos(angle) - velY * Math.sin(angle));
        this.vel.y = (int)Math.round(velY * Math.cos(angle) + velX * Math.sin(angle));
    }

    /*
     * Return if the ball is collide
     * */
    public boolean isCollide(){
        return this.collide;
    }

    /*
     * Function to return the dimension of the canvas
     * */
    public Point getDims(){
        return this.dims;
    }

    /**
     * Update the state of the ball using strategies associated with the ball.
     */
    public void update(Observable obs, Object o) {
        if(this.isSwitcherBall == true && o instanceof IUpdateStrategy){
            strategy = (IUpdateStrategy)o;
        }
        collision(dims);
        strategy.updateState(this);
    }

    /*
    * Return the strategy type
    * */
    public IUpdateStrategy getStrategy(){
        return this.strategy;
    }
}
