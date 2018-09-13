package edu.rice.comp504.model;

import java.awt.*;
import java.util.Observable;
import java.util.Random;

/**
 * The dispatch adapter holds all the balls in the BallWorld. It is responsible for communicating with the model
 * and the view.
 */
public class DispatchAdapter extends Observable {
    private int canvasWidth = 800;
    private int canvasHeight = 800;
    /**
     * Constructor
     */
    public DispatchAdapter() {

    }

    /**
     * Call the update method on all the ball observers to update their position in the ball world
     */
    public void updateBallWorld() {
        if(countObservers()!=0){
            setChanged();
            notifyObservers();
        }
    }

    /**
     * Load a ball into the ball world
     * @param kind  The kind of ball to load into ball world
     * @return A ball
     */
    public ABall loadBall(String kind) {
        ABall ball;
        // To create a random number
        Random random = new Random();

        // Radius cannot be 0
        int radius = random.nextInt(50)+10;
        Point loc = new Point();
        Point vel = new Point();
        // We need to make sure the new ball is not exceeding the wall
        // Therefore the left boundary is 0 + radius
        // The right boundary is wall width - radius
        // Since we need to add radius to the loc.x
        // Then we need to subtract two times of width - radius
        // Same as loc.y
        loc.x = radius + random.nextInt(canvasWidth - 2*radius);
        loc.y = radius + random.nextInt(canvasHeight - 2*radius);

        vel.x = random.nextInt(50)+10;
        vel.y = random.nextInt(50)+10;

        // Initialize ball object
        if(kind.equals("horizontal")) { // Create a horizontal moving ball
            ball = new HorizontalBall(loc, radius, vel, "#2ECCFA");
        }
        else if(kind.equals("vertical")){ // Create a vertical moving ball
            ball = new VerticalBall(loc, radius, vel, "#FF4000");
        }
        else if(kind.equals("rotating")){ // Create a rotating ball
            int angle = random.nextInt(320) + 40;
            double rad = angle * 2 * Math.PI / 360;
            ball = new RotatingBall(loc, radius, vel, "#58FA58", rad);
        }
        else if(kind.equals("diagonal")){ // Create a ball moving along diagonal line
            loc.x = loc.y;
            vel.x = vel.y;
            Point diagonalLoc = new Point(loc.x, loc.y);
            Point diagonalVel = new Point(vel.x, vel.y);
            ball = new DiagonalBall(diagonalLoc, radius, diagonalVel, "#585858");
        }
        else if(kind.equals("sizechange")){ // Create a ball that will change size when collision
            ball = new SizechangeBall(loc, radius, vel, "#FE2EF7");
        }
        else if(kind.equals("colorchange")){ // Create a ball that will change color when collision happened
            ball = new ColorchangeBall(loc, radius, vel, "#E6E6E6");
        }
        else if(kind.equals("straight")){ // Create a ball has default movement
            ball = new StraightBall(loc, radius, vel, "#DF7401");
        }
        else{  // For those are not defined ball type, return null ball
            // Null ball has no velocity and color black
            ball = new NullBall(loc, radius, new Point(0,0), "#000000");
        }

        addObserver(ball);
        return ball;
    }

    public void clear(){
        deleteObservers();
    }
}
