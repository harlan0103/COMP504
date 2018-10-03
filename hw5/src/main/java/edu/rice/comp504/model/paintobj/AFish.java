package edu.rice.comp504.model.paintobj;

import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.awt.*;
import java.util.Random;

public class AFish extends APaintObject {
    /*Add new attributes to the concrete shape "AFish"*/
    private int imgWidth;
    private int imgHeight;
    private boolean flip;

    /**
     * Constructor of ARectangle
     * */
    private AFish(Point loc, Point vel, IUpdateStrategy strategy, int imgWidth, int imgHeight){
        // Fish has initial color with black
        super(loc, vel, "#000000","Fish", strategy);
        this.imgWidth = imgWidth;
        this.imgHeight = imgHeight;
        this.flip = false;
    }

    /**
     * makeARectangle function will randomize the radius, location and speed for a ball object
     * */
    public static AFish makeAFish(IUpdateStrategy strategy, Point dims){
        // Get the dimension of canvas
        int dimWidth = dims.x;
        int dimHeight = dims.y;
        // Create Random size, velocity
        Random random = new Random();
        // Create a random velocity Point
        int velX = random.nextInt(50) + 20;
        int velY = random.nextInt(50) + 20;
        Point vel = new Point(velX, velY);
        // Randomize a new width and height for fish
        int imgHeight = random.nextInt(50) + 50;
        int imgWidth = imgHeight;
        // The left boundary of a rectangle is the left top point
        // Therefore only need to minus one side to make sure not exceed right wall
        int locX = random.nextInt(dimWidth - imgWidth);
        int locY = random.nextInt(dimHeight - imgHeight);
        Point loc = new Point(locX, locY);

        // Return a new fish object with imgWidth and imgHeight
        return new AFish(loc, vel, strategy, imgWidth, imgHeight);
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

        if(locX + this.imgWidth > dims.x){
            this.setLocation(new Point(dims.x - this.imgWidth, locY));
            this.setVelocity(new Point(-vel.x, vel.y));
            this.setFlipStatus(true);
            this.setCollide(true);
        }else if(locX < 0){
            this.setLocation(new Point(0, locY));
            this.setVelocity(new Point(-vel.x, vel.y));
            this.setFlipStatus(false);
            this.setCollide(true);
        }

        if(locY + this.imgHeight > dims.y){
            this.setLocation(new Point(locX, dims.y - this.imgHeight));
            this.setVelocity(new Point(vel.x, -vel.y));
            this.setCollide(true);
        }
        else if(locY < 0){
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
        this.imgHeight = newSize;
        this.imgWidth = imgHeight;
    }

    @Override
    public int getSize(){
        return this.imgHeight;
    }

    public void setFlipStatus(boolean status){
        this.flip = status;
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
