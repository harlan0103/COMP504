package edu.rice.comp504.model;

import edu.rice.comp504.model.HorizontalBall;
import edu.rice.comp504.model.NullBall;
import junit.framework.TestCase;

import java.awt.*;

public class HorizontalBallTest extends TestCase {
    /**
     * An update(for each ball type) moves the ball to the expected location
     * */
    public void testHorizontal(){
        Point loc = new Point(100,100);
        Point vel = new Point(10,10);
        int radius = 10;
        HorizontalBall ball = new HorizontalBall(loc, radius, vel, "#000000");
        // Update a new location and check if ball has moved
        int expectLocX = loc.x + vel.x;
        int expectLocY = loc.y;
        // Update location
        ball.update(null, null);
        // Get the new location of ball
        Point newLoc = ball.getLocation();
        assertEquals("Check for the X", newLoc.x, expectLocX);
        assertEquals("Check for the Y", newLoc.y, expectLocY);
    }
}