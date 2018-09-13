package edu.rice.comp504.model;

import edu.rice.comp504.model.NullBall;
import junit.framework.TestCase;

import java.awt.*;

public class NullBallTest extends TestCase {
    /**
    * An update to the NullBall should not affect the ball location
    * */
    public void testNull(){
        Point loc = new Point(100,100);
        // We give NullBall a velocity to check if it will change the location
        Point vel = new Point(20,20);
        int radius = 10;
        NullBall ball = new NullBall(loc, radius, vel, "#000000" );
        // Update a new location and check if ball has moved
        ball.updateBallLoc();
        Point newLoc = ball.getLocation();
        assertEquals("Check for the X", loc.x, newLoc.x);
        assertEquals("Check for the Y", loc.y, newLoc.y);
    }
}
