package edu.rice.comp504.model.ball;

import edu.rice.comp504.model.DispatchAdapter;
import edu.rice.comp504.model.strategy.*;
import junit.framework.TestCase;

import java.awt.*;

public class BallTest extends TestCase {

    public void testUpdate(){
        // Create a ball
        Ball newBall;

        /*
        * an update(for each ball strategy) moves the ball to the expected location
        * */

        DispatchAdapter dis = new DispatchAdapter();
        // Straight Ball
        newBall = new Ball(new Point(100,100), 50, new Point(20, 20), "#0DF8FF", new StraightStrategy(), new Point(800,800), false);
        newBall.update(dis, null);
        assertEquals("straightStrategy", 120, newBall.getLocation().x);
        assertEquals("straightStrategy", 100, newBall.getLocation().y);

        // Rotate Ball
        newBall = new Ball(new Point(100,100), 50, new Point(20, 20), "#0DF8FF", new RotateStrategy(), new Point(800,800), false);
        newBall.update(dis, null);
        assertEquals("RotateStrategy", 120, newBall.getLocation().x);
        assertEquals("RotateStrategy", 120, newBall.getLocation().y);

        // SizeChangeBall
        newBall = new Ball(new Point(100,100), 50, new Point(20, 20), "#0DF8FF", new SizeChangeStrategy(), new Point(800,800), false);
        newBall.update(dis, null);
        assertEquals("SizeChangeBall", 120, newBall.getLocation().x);
        assertEquals("SizeChangeBall", 120, newBall.getLocation().y);

        // ColorChangeBall
        newBall = new Ball(new Point(100,100), 50, new Point(20, 20), "#0DF8FF", new ColorChangeStrategy(), new Point(800,800), false);
        newBall.update(dis, null);
        assertEquals("ColorChangeBall", 120, newBall.getLocation().x);
        assertEquals("ColorChangeBall", 120, newBall.getLocation().y);

        // FlickerStrategy
        newBall = new Ball(new Point(100,100), 50, new Point(20, 20), "#0DF8FF", new FlickerStrategy(), new Point(800,800), false);
        newBall.update(dis, null);
        assertEquals("ColorChangeBall", "#000000", newBall.getColor());

        // SpeedChangeStrategy
        newBall = new Ball(new Point(100,100), 50, new Point(20, 20), "#0DF8FF", new SpeedChangeStrategy(), new Point(800,800), false);
        newBall.update(dis, null);
        assertEquals("SpeedChangeBall", 120, newBall.getLocation().x);
        assertEquals("SpeedChangeBall", 120, newBall.getLocation().y);

        // LoopStrategy
        newBall = new Ball(new Point(100,100), 50, new Point(20, 20), "#0DF8FF", new LoopStrategy(), new Point(800,800), false);
        newBall.update(dis, null);
        assertEquals("LoopBall", 100, newBall.getLocation().x);
        assertEquals("LoopBall", 120, newBall.getLocation().y);

        /*
        * an update to the ball with a NullStrategy should not affect the ball location
        * */
        // NullStrategy
        newBall = new Ball(new Point(100,100), 50, new Point(20, 20), "#0DF8FF", new NullStrategy(), new Point(800,800), false);
        newBall.update(dis, null);
        assertEquals("NullStrategy", 100, newBall.getLocation().x);
        assertEquals("NullStrategy", 100, newBall.getLocation().y);
        assertEquals("NullStrategy", "#000000", newBall.getColor());
    }
}
