package edu.rice.comp504.model;

import edu.rice.comp504.model.paint.Ball;
import junit.framework.TestCase;

import java.awt.*;

public class DispatchAdapterTest extends TestCase {
    public void testAdapter(){
        DispatchAdapter dis = new DispatchAdapter();
        dis.setCanvasDims(new Point(800, 800));

        /**
         * Requesting a ball with no strategy using the loadBall method creates a ball with NullStrategy
         * */
        Ball newBall = dis.loadBall("", "", "false");
        assertEquals("shoule be null strategy", "Null", newBall.getUpdateStrategy().getName());

        /**
         * A ball-to-ball collision will appropriately change the direction of both balls involved in the collision
         * */
        Ball collideBallX = dis.loadBall("StraightStrategy", "", "false");
        Ball collideBallY = dis.loadBall("StraightStrategy", "", "false");
        // We set two balls are in the same x-axis
        collideBallX.setLocation(new Point(20,40));
        collideBallY.setLocation(new Point(40,40));
        // We set two balls have the same radius, and they will collide each other in next update
        collideBallX.setRadius(8);
        collideBallY.setRadius(8);
        // We set the velocity of two balls
        collideBallX.setVelocity(new Point(5, 0));
        collideBallY.setVelocity(new Point(-5, 0));
        dis.updateBallWorld();

        assertEquals("ballX change direction", -5, collideBallX.getVelocity().x);
        assertEquals("ballY change direction", 5, collideBallY.getVelocity().x);

        /**
         * each interaction ball strategy affects the other ball involved in the collision as expected
         * */

        /**
         * 1.Test for eat interaction
         * */
        dis.deleteObservers();
        Ball eatBallX = dis.loadBall("StraightStrategy", "EatInteract ", "false");
        Ball eatBallY = dis.loadBall("StraightStrategy", "EatInteract ", "false");
        eatBallX.setLocation(new Point(20, 40));
        eatBallY.setLocation(new Point(40, 40));
        // We set eatBallX has larger radius than eatBallY
        eatBallX.setRadius(6);
        eatBallY.setRadius(3);
        eatBallX.setVelocity(new Point(8,0));
        eatBallY.setVelocity(new Point(-8,0));
        dis.updateBallWorld();

        assertEquals("eatBallY should be eaten", 1, dis.countObservers());

        /**
         * 2.Test for destroy interaction
         * */
        dis.deleteObservers();
        Ball destroyBallX = dis.loadBall("StraightStrategy", "DestroyInteract ", "false");
        Ball destroyBallY = dis.loadBall("StraightStrategy", "DestroyInteract ", "false");
        destroyBallX.setLocation(new Point(20, 40));
        destroyBallY.setLocation(new Point(40, 40));
        // We set eatBallX has larger radius than eatBallY
        destroyBallX.setRadius(6);
        destroyBallY.setRadius(3);
        destroyBallX.setVelocity(new Point(8,0));
        destroyBallY.setVelocity(new Point(-8,0));
        dis.updateBallWorld();

        assertEquals("both ball should be destroyed", 0, dis.countObservers());

        /**
         * 3.Test for change color interaction
         * */
        dis.deleteObservers();
        Ball colorChangeBallX = dis.loadBall("StraightStrategy", "ChangeColorInteract ", "false");
        Ball colorChangeBallY = dis.loadBall("StraightStrategy", "ChangeColorInteract ", "false");
        colorChangeBallX.setLocation(new Point(20, 40));
        colorChangeBallY.setLocation(new Point(40, 40));
        // We set eatBallX has larger radius than eatBallY
        colorChangeBallX.setRadius(6);
        colorChangeBallY.setRadius(3);
        colorChangeBallX.setVelocity(new Point(8,0));
        colorChangeBallY.setVelocity(new Point(-8,0));
        colorChangeBallX.setColor("#000000");
        colorChangeBallY.setColor("#000000");
        dis.updateBallWorld();

        //System.out.println(collideBallX.getColor());
        assertEquals("Both ball's color should be changed", collideBallX.getColor(), collideBallY.getColor());
        assertNotSame("Changed color should not be default color we set", "#000000", collideBallX.getColor());
        assertNotSame("Changed color should not be default color we set", "#000000", collideBallY.getColor());

        /**
         * 4.Test for swap radius interaction
         * */
        dis.deleteObservers();
        Ball RadiusBallX = dis.loadBall("StraightStrategy", "SwapRadiusInteract ", "false");
        Ball RadiusBallY = dis.loadBall("StraightStrategy", "SwapRadiusInteract ", "false");
        RadiusBallX.setLocation(new Point(20, 40));
        RadiusBallY.setLocation(new Point(40, 40));
        // We set eatBallX has larger radius than eatBallY
        RadiusBallX.setRadius(6);
        RadiusBallY.setRadius(3);
        RadiusBallX.setVelocity(new Point(8,0));
        RadiusBallY.setVelocity(new Point(-8,0));
        dis.updateBallWorld();

        assertEquals("Ball's radius should be swapped", 3, RadiusBallX.getRadius());
        assertEquals("Ball's radius should be swapped", 6, RadiusBallY.getRadius());

        /**
         * 5.Test for swap color interaction
         * */
        dis.deleteObservers();
        Ball ColorBallX = dis.loadBall("StraightStrategy", "SwapColorInteract ", "false");
        Ball ColorBallY = dis.loadBall("StraightStrategy", "SwapColorInteract ", "false");
        ColorBallX.setLocation(new Point(20, 40));
        ColorBallY.setLocation(new Point(40, 40));
        // We set eatBallX has larger radius than eatBallY
        ColorBallX.setRadius(6);
        ColorBallY.setRadius(3);
        ColorBallX.setVelocity(new Point(8,0));
        ColorBallY.setVelocity(new Point(-8,0));
        ColorBallX.setColor("#000000");
        ColorBallY.setColor("#111111");
        dis.updateBallWorld();

        assertEquals("Ball's color should be swapped", "#111111", ColorBallX.getColor());
        assertEquals("Ball's color should be swapped", "#000000", ColorBallY.getColor());

        /**
         * 6.Test for stick interaction
         * */
        dis.deleteObservers();
        Ball StickBallX = dis.loadBall("StraightStrategy", "StickInteract ", "false");
        Ball StickBallY = dis.loadBall("StraightStrategy", "StickInteract ", "false");
        StickBallX.setLocation(new Point(20, 40));
        StickBallY.setLocation(new Point(40, 40));
        // We set eatBallX has larger radius than eatBallY
        StickBallX.setRadius(6);
        StickBallY.setRadius(3);
        StickBallX.setVelocity(new Point(8,0));
        StickBallY.setVelocity(new Point(-8,0));
        dis.updateBallWorld();

        assertEquals("Both balls should not move and stick together", new Point(0,0), StickBallX.getVelocity());
        assertEquals("Both balls should not move and stick together", new Point(0,0), StickBallY.getVelocity());

        /**
         * 7.Test for update strategy swap interaction
         * */
        dis.deleteObservers();
        Ball SwapStrategyX = dis.loadBall("StraightStrategy", "SwapUpdateInteract ", "false");
        Ball SwapStrategyY = dis.loadBall("ColorChangeStrategy", "SwapUpdateInteract ", "false");
        SwapStrategyX.setLocation(new Point(20, 40));
        SwapStrategyY.setLocation(new Point(40, 40));
        // We set eatBallX has larger radius than eatBallY
        SwapStrategyX.setRadius(6);
        SwapStrategyY.setRadius(3);
        SwapStrategyX.setVelocity(new Point(8,0));
        SwapStrategyY.setVelocity(new Point(-8,0));
        dis.updateBallWorld();

        assertEquals("Both balls should not move and stick together", "ColorChangeStrategy", SwapStrategyX.getUpdateStrategy().getName());
        assertEquals("Both balls should not move and stick together", "StraightStrategy", SwapStrategyY.getUpdateStrategy().getName());
    }


}
