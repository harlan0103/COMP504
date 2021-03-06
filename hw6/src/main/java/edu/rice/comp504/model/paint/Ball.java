package edu.rice.comp504.model.paint;

import edu.rice.comp504.model.cmd.IBallCmd;
import edu.rice.comp504.model.strategy.IInteractStrategy;
import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

/**
 * The ball that will be drawn and updated on the canvas.
 */
public class Ball implements Observer {
    private int radius;
    private Point loc;
    private Point vel;
    private String color;
    private IUpdateStrategy uStrategy;
    private IInteractStrategy iStrategy;
    private boolean collide;


    /**
     * Constructor for Ball
     * @param loc The paint location.  The origin (0,0) is the upper left corner of the canvas.
     * @param radius  The paint radius.
     * @param vel The paint velocity.  The velocity is a vector with an x and y component.
     */
    /*
    public Ball(Point loc, int radius, Point vel, String color, IUpdateStrategy uStrategy, IInteractStrategy iStrategy) {
    }
    */

    /**
     * Constructor without IInteractStrategy
     * */
    public Ball(Point loc, int radius, Point vel, String color, IUpdateStrategy uStrategy, IInteractStrategy iStrategy) {
        this.loc = loc;
        this.radius = radius;
        this.vel = vel;
        this.color = color;
        this.uStrategy = uStrategy;
        this.iStrategy = iStrategy;
    }

    /**
     * Create a new Ball object
     * */
    public static Ball makeBall(IUpdateStrategy uStrategy, IInteractStrategy iStrategy, Point dims){
        // Get the dimension of canvas
        int dimWidth = dims.x;
        int dimHeight = dims.y;
        // Create Random size, velocity
        Random random = new Random();
        // Create a random velocity Point
        int velX = random.nextInt(50) + 20;
        int velY = random.nextInt(50) + 20;
        Point vel = new Point(velX, velY);
        // Randomize a new radius
        int radius = random.nextInt(50) + 10;
        // The left boundary of a ball loc is 0 + radius
        // The right boundary of a ball loc is is width - radius
        // Same as the top boundary and bottom boundary
        int locX = random.nextInt(dimWidth - 2 * radius) + radius;
        int locY = random.nextInt(dimHeight - 2 * radius) + radius;
        Point loc = new Point(locX, locY);
        // Return a new ball
        return new Ball(loc, radius, vel, "#97FF7E", uStrategy, iStrategy);
    }

    /**
     * Get the radius of the paint.
     * @return The paint radius.
     */
    public int getRadius() {
        return this.radius;
    }

    /**
     * Set the radius of the paint.
     * @param r The paint radius.
     */
    public void setRadius(int r) {
        this.radius = r;
    }

    /**
     * Get the ball location in the paint world.
     * @return The ball location.
     */
    public Point getLocation() {
        return this.loc;
    }


    /**
     * Set the ball location in the canvas.  The origin (0,0) is the top left corner of the canvas.
     * @param loc  The ball x,y coordinate.
     */
    public void setLocation(Point loc) {
        this.loc = loc;
    }

    /**
     * Get the ball color
     * @return The ball color
     */
    public String getColor() {
        return this.color;
    }

    /**
     * Set the ball color
     * @param color The new color
     */
    public void setColor(String color) {
        this.color = color;
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
     * Get the velocity of the ball.
     * @return The ball velocity
     */
    public  Point getVelocity() {
        return this.vel;
    }

    /**
     * Set the ball velocity.
     * @param vel The new ball velocity.
     */
    public void setVelocity(Point vel) {
        this.vel = vel;
    }

    /**
     * Get the ball strategy.
     * @return The ball strategy.
     */
    public IUpdateStrategy getUpdateStrategy() {
        return this.uStrategy;
    }

    /**
     * Set the ball strategy to the new strategy.
     * @param strategy  The new strategy.
     */
    public void setUpdateStrategy(IUpdateStrategy strategy) {
        this.uStrategy = strategy;
    }

    /**
     * Get the ball-to-ball interaction strategy.
     * @return  The ball-to-ball interaction strategy.
     */
    public IInteractStrategy getInteractStrategy() {
        return this.iStrategy;
    }

    /**
     * Set the ball-to-ball interaction strategy.
     * @param iStrategy  The new ball-to-ball interaction strategy
     */
    public void setInteractStrategy(IInteractStrategy iStrategy) {
        this.iStrategy = iStrategy;
    }

    /**
     * Rotate the paint.
     * @param angle  The angle that determines how far to rotate the paint.
     */
    public void rotate(double angle) {
        // Get the old velocity and update to new velocity
        int velX = vel.x;
        int velY = vel.y;
        this.vel.x = (int)Math.round(velX * Math.cos(angle) - velY * Math.sin(angle));
        this.vel.y = (int)Math.round(velY * Math.cos(angle) + velX * Math.sin(angle));
    }

    /**
     * Detects collision between a ball and a wall in the ball world.  Change direction if ball collides with a wall.
     * @param dims The canvas dimensions
     */
    public void wallCollision(Point dims) {
        this.setCollide(false);
        // Return the boolean value
        int locX = this.getLocation().x;
        int locY = this.getLocation().y;
        if(locX + this.radius >= dims.x || locX - this.radius <= 0){
            Point vel = this.getVelocity();
            this.setVelocity(new Point(-vel.x, vel.y));
            // When collide occurs set the collide status to true
            this.setCollide(true);
        }
        if(locY + this.radius >= dims.y || locY <= 0){
            Point vel = this.getVelocity();
            this.setVelocity(new Point(vel.x, -vel.y));
            this.setCollide(true);
        }
        // No collide return false
    }

    /**
     * Detects collision between two balls in the ball world.  Change direction if ball collides with a ball.
     */
    public boolean ballCollision(Ball ball) {
        // Get radius, velocity and location of this ball
        int radius = this.getRadius();
        Point loc = this.getLocation();
        Point vel = this.getVelocity();
        // Get radius velocity and location of target ball
        int bRadius = ball.getRadius();
        Point bLoc = ball.getLocation();
        Point bVel = ball.getVelocity();

        // Get the distance between two balls
        double distance = Math.sqrt(Math.pow((loc.x - bLoc.x), 2) + Math.pow((loc.y - bLoc.y), 2));
        if(radius + bRadius >= (int)distance){
            // If there is a collision, we change the velocity of both this ball and dest ball
            this.setVelocity(new Point(-vel.x, -vel.y));
            ball.setVelocity(new Point(-bVel.x, -bVel.y));
            return true;
        }
        return false;
    }

    /**
     * Update the state of the paint using strategies associated with the paint.
     */
    public void update(Observable obs, Object o) {
        // Call the execute function to update shape status
        ((IBallCmd) o).execute(this);
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
}
