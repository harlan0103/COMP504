package edu.rice.comp504.model.paintobj;

import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.awt.*;
import java.util.Random;

public class AHexagon extends APaintObject {
    /*Add new attributes to the concrete shape "BALL"*/
    private int side;

    /**
     * Constructor of ARectangle
     * */
    private AHexagon(Point loc, Point vel, String color, IUpdateStrategy strategy, int side){
        super(loc, vel, color, "Hexagon", strategy);
        this.side = side;
    }

    /**
     * makeARectangle function will randomize the radius, location and speed for a ball object
     * */
    public static AHexagon makeHexagon(IUpdateStrategy strategy, Point dims){
        // Get the dimension of canvas
        int dimWidth = dims.x;
        int dimHeight = dims.y;
        // Create Random size, velocity
        Random random = new Random();
        // Create a random velocity Point
        int velX = random.nextInt(50) + 20;
        int velY = random.nextInt(50) + 20;
        Point vel = new Point(velX, velY);
        // Randomize a new side length
        int side = random.nextInt(30) + 40;
        // The left boundary of a rectangle is the left top point
        // Therefore only need to minus one side to make sure not exceed right wall
        int locX = random.nextInt(dimWidth - side);
        int locY = random.nextInt(dimHeight - side);
        Point loc = new Point(locX, locY);
        // Return a new object
        return new AHexagon(loc, vel, "#22BDB6", strategy, side);
    }

    @Override
    public void rotate(double angle){
        int velX = vel.x;
        int velY = vel.y;
        this.vel.x = (int)Math.round(velX * Math.cos(angle) - velY * Math.sin(angle));
        this.vel.y = (int)Math.round(velY * Math.cos(angle) + velX * Math.sin(angle));
    }

    /*
     * @collision
     * Check for the collision status
     * */
    @Override
    public void collision(Point dims){
        this.setCollide(false);
        int locX = this.getLocation().x;
        int locY = this.getLocation().y;
        Point vel = this.getVelocity();

        if(locX + this.side > dims.x){
            this.setLocation(new Point(dims.x - this.side, locY));
            this.setVelocity(new Point(-vel.x, vel.y));
            this.setCollide(true);
        }else if(this.getLocation().x < 0){
            this.setLocation(new Point(0, locY));
            this.setVelocity(new Point(-vel.x, vel.y));
            this.setCollide(true);
        }

        if(locY + this.side > dims.y){
            this.setLocation(new Point(locX, dims.y - this.side));
            this.setVelocity(new Point(vel.x, -vel.y));
            this.setCollide(true);
        }
        else if(this.getLocation().y < 0){
            this.setLocation(new Point(locX, 0));
            this.setVelocity(new Point(vel.x, -vel.y));
            this.setCollide(true);
        }
    }

    /*
     * @setSize
     * Set a new side length for the ARectangle
     * */
    @Override
    public void setSize(int newSize){
        this.side = newSize;
    }

    @Override
    public int getSize(){
        return this.side;
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
