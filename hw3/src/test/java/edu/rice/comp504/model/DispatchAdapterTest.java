package edu.rice.comp504.model;

import edu.rice.comp504.model.ball.Ball;
import junit.framework.TestCase;

import java.awt.*;
import java.util.Random;

public class DispatchAdapterTest extends TestCase {

    public void testLoadBall(){

        /*
        * Request a single ball strategy with the dispatch adapter loadBall
        * method creates the expected ball strategy
        * */
        DispatchAdapter dis = new DispatchAdapter();
        dis.setCanvasDims(new Point(800, 800));
        Ball newBall;
        //#Straight
        newBall = dis.loadBall("strategy=+StraightStrategy", false);
        assertEquals("strategy test", "StraightStrategy", newBall.getStrategy().getName());
        //#Rotate
        newBall = dis.loadBall("strategy=+RotationStrategy", false);
        assertEquals("strategy test", "RotationStrategy", newBall.getStrategy().getName());
        //#ColorChange
        newBall = dis.loadBall("strategy=+ColorChangeStrategy", false);
        assertEquals("strategy test", "ColorChangeStrategy", newBall.getStrategy().getName());
        //#SizeChange
        newBall = dis.loadBall("strategy=+SizeChangeStrategy", false);
        assertEquals("strategy test", "SizeChangeStrategy", newBall.getStrategy().getName());
        //#SpeedChange
        newBall = dis.loadBall("strategy=+SpeedChangeStrategy", false);
        assertEquals("strategy test", "SpeedChangeStrategy", newBall.getStrategy().getName());
        //#Flicker
        newBall = dis.loadBall("strategy=+FlickerStrategy", false);
        assertEquals("strategy test", "FlickerStrategy", newBall.getStrategy().getName());
        //#loop
        newBall = dis.loadBall("strategy=+LoopStrategy", false);
        assertEquals("strategy test", "LoopStrategy", newBall.getStrategy().getName());

        /*
        * Request an unknown ball strategy creates a ball with a NullStrategy
        * */
        newBall = dis.loadBall("strategy=+Unknown", false);
        assertEquals("strategy test", "Null", newBall.getStrategy().getName());
    }

    /*
    * switcher balls should be able to switch strategies
    * */
    public void testSwitchStrategy(){
        Random random = new Random();
        String[] strategy = {"StraightStrategy", "RotationStrategy", "ColorChangeStrategy", "SizeChangeStrategy", "SpeedChangeStrategy", "FlickerStrategy", "LoopStrategy"};
        DispatchAdapter dis = new DispatchAdapter();
        dis.setCanvasDims(new Point(800, 800));

        for(int i = 0; i < 10; i++){
            String currentStrategy = strategy[random.nextInt(strategy.length)];
            Ball newball = dis.loadBall("strategy=+" + currentStrategy, true);
            String newStrategy = strategy[random.nextInt(strategy.length)];
            dis.switchStrategy("strategy=+" + newStrategy);
            assertEquals("switch test", newStrategy, newball.getStrategy().getName());
        }
    }
}
