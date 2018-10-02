package edu.rice.comp504.model.paintobj;

import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.awt.*;
import java.util.Random;

public class ARectangle extends APaintObject {
    /*Add new attributes to the concrete shape "BALL"*/

    public ARectangle(Point loc, Point vel, String color, String type, IUpdateStrategy strategy, Point dims, int size, String switcherBall){
        super(loc, vel, color, type, strategy, dims, size, switcherBall);
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
        collide = checkForCollide(this.size);
    }

    public boolean isCollide(){
        return this.collide;
    }
}

