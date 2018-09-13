package edu.rice.comp504.model;


import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Abstract ball that will be extended by all concrete type balls.   The abstract ball implements the Observer
 * interface enabling all concrete balls to be updated when the dispatcher, which is an Observable,
 */
public abstract class ABall implements Observer {
    private Point loc;
    private String color;
    private int radius;
    private Point vel;

    // Hardcode walls of ball world
    protected int leftWall = 0;
    protected int rightWall = 800;
    protected int topWall = 0;
    protected int bottomWall = 800;

    /**
     * Constructor for ABall
     * @param loc The ball location.  The origin (0,0) is the upper left corner of the canvas.
     * @param radius  The ball radius.
     * @param vel The ball velocity.  The velocity is a vector with an x and y component.
     * @param color The ball color.
     */
    public ABall(Point loc, int radius, Point vel, String color) {
        this.loc = loc;
        this.radius = radius;
        this.vel = vel;
        this.color = color;
    }
    /**
     * Get the ball name
     * @return ball name
     */
    public abstract String getName();

    /**
     * Get the ball color.
     * @return ball color
     */
    public String getColor() {return color; }

    /**
     * Set the ball color.
     * @param color The new ball color
     */
    public void setColor(String color) { this.color = color; }


    /**
     * Get the ball location in the ball world.
     * @return The ball location.
     */
    public Point getLocation() { return loc; }


    /**
     * Set the ball location in the canvas.  The origin (0,0) is the top left corner of the canvas.
     * @param loc  The ball coordinate.
     */
    public void setLocation(Point loc) {
        this.loc = loc;
    }

    /**
     * Compute the next location of the ball in the ball world given the velocity
     * @param velX
     * @param velY
     */
    public void nextLocation(int velX, int velY) {
        // Update location by adding velocity to location
        this.loc.x += velX;
        this.loc.y += velY;
    }

    /**
     * Get the velocity of the ball.
     * @return The ball velocity
     */
    public  Point getVelocity() { return vel; }


    /**
     * Get the radius of the ball.
     * @return The ball radius.
     */
    public int getRadius() { return radius; }

    /**
     * Set the radius of the ball.
     * @param r The ball radius.
     */
    public void setRadius(int r) { radius = r; }

    /**
     * Detects collision between a ball and a wall in the ball world.  Change direction if ball collides with a wall.
     */
    public void collision() {
        /*
        * Idea is that the loc is the center point of the ball
        * if loc.x + radius exceeds the canvas width, then ball collides the right wall
        * it is the collision situation
        * */
        int rightEdge = this.loc.x + this.radius;
        int topEdge = this.loc.y - this.radius;
        int leftEdge = this.loc.x - this.radius;
        int bottomEdge = this.loc.y + this.radius;

        // Check for horizontal collision
        if(rightEdge >= rightWall){
            // If collide, some ball attributes will be changed
            updateBallAttrs(true);
            this.vel.x = -(this.vel.x);

        }
        else if(leftEdge <= leftWall){
            updateBallAttrs(true);
            this.vel.x = -(this.vel.x);

        }

        // Check for vertical collision
        if(topEdge <= topWall){ // Top edge is 0
            updateBallAttrs(true);
            this.vel.y = -(this.vel.y);

        }
        else if(bottomEdge >= bottomWall){
            updateBallAttrs(true);
            this.vel.y = -(this.vel.y);
        }
    }

    /**
     * Move the ball to the next location in the ball world
     */
    public void move() {
        // First check collision status
        // Then update to next location
        collision();
        updateBallLoc();
    }

    /**
     * Rotate the ball
     * @param angle  The angle that determines how much to rotate the ball
     */
    public void rotate(double angle) {
        Point vel = getVelocity();
        int oldX = vel.x;
        int oldY = vel.y;
        // Equation from course slide lecture 7
        this.vel.x = (int) Math.ceil(oldX * Math.cos(angle) - oldY * Math.sin(angle));
        this.vel.y = (int) Math.ceil(oldY * Math.cos(angle) + oldX * Math.sin(angle));

    }

    /**
     * Update the ball x,y coordinate in the ball world
     */
    public abstract void updateBallLoc();

    /**
     * Update the ball attributes.   This may include the radius and color.
     */
    public abstract void updateBallAttrs(boolean collide);

    /*
     * @update:
     * */
    @Override
    public void update(Observable o, Object arg){
        move();
    }

}
