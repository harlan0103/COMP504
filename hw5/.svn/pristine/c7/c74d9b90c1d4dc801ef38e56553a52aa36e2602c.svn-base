package edu.rice.comp504.model.paintobj;

import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.awt.*;
import java.util.Random;

public class ABall extends APaintObject {
    /*Add new attributes to the concrete shape "BALL"*/


    public ABall(Point loc, Point vel, String color, String type, IUpdateStrategy strategy, Point dims, int size, String switcherBall){
        super(loc, vel, color, type, strategy, dims, size, switcherBall);
    }

    /*
    * return the radius
    * */
    public int getSize(){
        return this.size;
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
        // Return the boolean value
        collide = checkForCollide(this.size);
    }

}
