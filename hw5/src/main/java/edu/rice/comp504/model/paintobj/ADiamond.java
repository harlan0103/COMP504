package edu.rice.comp504.model.paintobj;

import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.awt.*;
import java.util.Random;

public class ADiamond extends APaintObject {
    //Add new attributes to the concrete shape "Diamond"
    private int radius;

    /**
     * Constructor of ADiamond
     * */
    private ADiamond(Point loc, Point vel, String color, IUpdateStrategy strategy, int radius){
        super(loc, vel, color, "Diamond", strategy);
        this.radius = radius;
    }

    /**
     * makeABall function will randomize the radius, location and speed for a ball object
     * */
    public static ADiamond makeDiamond(IUpdateStrategy strategy, Point dims){
        // Get the dimension of canvas
        int dimWidth = dims.x;
        int dimHeight = dims.y;
        // Create Random size, velocity
        Random random = new Random();
        // Create a random velocity Point
        int velX = random.nextInt(50) + 20;
        int velY = random.nextInt(50) + 20;
        Point vel = new Point(velX, velY);
        // Randomize a new radius to generate a diamond
        int radius = random.nextInt(50) + 10;
        // Set the midpoint of the diamond shape
        int locX = random.nextInt(dimWidth - 2 * radius) + radius;
        int locY = random.nextInt(dimHeight - 2 * radius) + radius;

        Point loc = new Point(locX, locY);
        // Return a new object
        return new ADiamond(loc, vel, "#5677FF", strategy, radius);
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
        if(this.getLocation().x + this.radius >= dims.x || locX - this.radius <= 0){
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

    /*
     * @nextLocation is override the method in APaintObject
     * change the location of the object to the next point
     * */
    @Override
    public void nextLocation(int velX, int velY) {
        // Add velocity vectors to the current Location
        this.loc.x += velX;
        this.loc.y += velY;
    }
}

