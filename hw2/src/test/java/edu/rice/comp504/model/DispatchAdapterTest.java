package edu.rice.comp504.model;

import junit.framework.TestCase;

public class DispatchAdapterTest extends TestCase {

    public void testLoadBall(){
        DispatchAdapter da = new DispatchAdapter();
        /**
        * requesting a ball type with the dispatch adapter loadBall method creates the expected ball type
        * */
        // 1. Check for the straight moving ball
        ABall sBall = da.loadBall("straight");
        assertEquals("load straight ball type test", "StraightBall", sBall.getName());
        // 2. Check for the horizontal moving ball
        ABall hBall = da.loadBall("horizontal");
        assertEquals("load horizontal ball type test", "HorizontalBall", hBall.getName());
        // 3. Check for the vertical moving ball
        ABall vBall = da.loadBall("vertical");
        assertEquals("load vertical ball type test", "VerticalBall", vBall.getName());
        // 4. Check for the rotating ball
        ABall rBall = da.loadBall("rotating");
        assertEquals("load rotating ball type test", "RotatingBall", rBall.getName());
        // 5. Check for the diagonal moving ball
        ABall dBall = da.loadBall("diagonal");
        assertEquals("load diagonal moving ball", "DiagonalBall", dBall.getName());
        // 6. Check for the size changing ball
        ABall siBall = da.loadBall("sizechange");
        assertEquals("load size change ball", "SizechangeBall", siBall.getName());
        // 7. Check for the color changing ball
        ABall cBall = da.loadBall("colorchange");
        assertEquals("load color change ball", "ColorchangeBall", cBall.getName());

        /**
        * requesting an unknown ball type creates a NullBall
        * */
        // Check for the null type ball
        ABall nBall = da.loadBall("unknown");
        assertEquals("load unknown ball type test", "NullBall", nBall.getName());

        incrementBall();
        clearBall();
    }

    /**
     * adding a ball to the BallWorld increases the number of balls by 1
     * */
    public void incrementBall(){
        DispatchAdapter ad = new DispatchAdapter();
        int num = ad.countObservers();
        ad.loadBall("horizontal");
        num++;
        ad.loadBall("vertical");
        num++;
        ad.loadBall("straight");
        num++;

        assertEquals("check for ball number", num, ad.countObservers());
    }

    /**
    * clearing the BallWorld sets the number of balls to 0
    * */
    public void clearBall(){
        DispatchAdapter ad = new DispatchAdapter();
        ad.loadBall("horizontal");
        ad.loadBall("vertical");
        ad.loadBall("straight");
        ad.clear();
        assertEquals("clearing ball world", 0, ad.countObservers());
    }
}
