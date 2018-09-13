package edu.rice.comp504.model;

import edu.rice.comp504.model.RotatingBall;
import junit.framework.TestCase;

import java.awt.*;

public class RotatingBallTest extends TestCase {
    /**
     * An update(for each ball type) moves the ball to the expected location
     * */
    public void testVertical(){
        Point loc = new Point(100,100);
        Point vel = new Point(10,10);
        double angle = 50;
        int radius = 10;
        RotatingBall ball = new RotatingBall(loc, radius, vel, "#000000",50);
        // Update a new location and check if ball has moved
        int expectLocX = loc.x + vel.x;
        int expectLocY = loc.y + vel.y;
        // Update location
        ball.update(null, null);
        // Get the new location of ball
        Point newLoc = ball.getLocation();
        assertEquals("Check for the X", newLoc.x, expectLocX);
        assertEquals("Check for the Y", newLoc.y, expectLocY);
    }
}
