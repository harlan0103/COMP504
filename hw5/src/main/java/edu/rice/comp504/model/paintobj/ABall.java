package edu.rice.comp504.model.paintobj;

import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.awt.*;
import java.util.Random;

public class ABall extends APaintObject {
    //Add new attributes to the concrete shape "BALL"
    private int radius;

    /**
    * Constructor of ABall
    * */
    private ABall(Point loc, Point vel, String color, IUpdateStrategy strategy, int radius){
        super(loc, vel, color, "Ball", strategy);
        this.radius = radius;
    }

    /**
    * makeABall function will randomize the radius, location and speed for a ball object
    * */
    public static ABall makeABall(IUpdateStrategy strategy, Point dims){
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
        // Return a new object
        return new ABall(loc, vel, "#97FF7E", strategy, radius);
    }


    @Override
    public void rotate(double angle){
        int velX = vel.x;
        int velY = vel.y;
        this.vel.x = (int)Math.round(velX * Math.cos(angle) - velY * Math.sin(angle));
        this.vel.y = (int)Math.round(velY * Math.cos(angle) + velX * Math.sin(angle));
    }

    @Override
    public void collision(Point dims){
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
    }

    /*
    * @setSize
    * Set a new radius for the ABall
    * */
    @Override
    public void setSize(int newSize){
        this.radius = newSize;
    }

    @Override
    public int getSize(){
        return this.radius;
    }

}

