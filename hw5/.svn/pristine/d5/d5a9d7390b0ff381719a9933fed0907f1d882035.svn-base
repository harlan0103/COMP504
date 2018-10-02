package edu.rice.comp504.model;

import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.strategy.IUpdateStrategy;
import edu.rice.comp504.model.strategy.StraightStrategy;
import junit.framework.TestCase;

import java.awt.*;
import java.util.Random;

public class DispatcherAdapterTest extends TestCase {

    public void testLoadPaintObj(){
        /**
        * Requesting a single shape strategy with the dispatch adapter loadPaintObj method creates
        * the expected shape strategy
        * */

        DispatcherAdapter dis = new DispatcherAdapter();
        dis.setCanvasDims(new Point(800, 800));
        APaintObject newShape;
        // ball shape with StraightStrategy
        newShape = dis.loadPaintObj("StraightStrategy","Ball", "false");
        assertEquals("straight ball", "StraightStrategy", newShape.getStrategy().getName());
        assertEquals("straight ball", "Ball", newShape.getType());
        // rectangle shape with ColorChangeStrategy
        newShape = dis.loadPaintObj("ColorChangeStrategy", "Rectangle", "false");
        assertEquals("colorChange rectangle", "ColorChangeStrategy", newShape.getStrategy().getName());
        assertEquals("colorChange rectangle", "Rectangle", newShape.getType());

        /**
        * Requesting multiple strategies with the dispatch adapter loadPaintObj method creates
        * the expected shape strategy
        * */

        newShape = dis.loadPaintObj("StraightStrategy ColorChangeStrategy", "Ball", "false");
        assertEquals("multiple strategies selected", "composite", newShape.getStrategy().getName());


        /**
        * Requesting an unknown shape strategy creates one shape of each type with NullStrategy
        * */

        // Unknown ball
        newShape = dis.loadPaintObj("Unknown", "Ball", "false");
        assertEquals("unknown ball", "Null", newShape.getStrategy().getName());
        assertEquals("unknown ball", "Ball", newShape.getType());
        // Unknown rectangle
        newShape = dis.loadPaintObj("Unknown", "Rectangle", "false");
        assertEquals("unknown rectangle", "Null", newShape.getStrategy().getName());
        assertEquals("unknown rectangle", "Rectangle", newShape.getType());

        /**
        * Requesting a shape with no strategies selected creates one shape of
        * each type with a NullStrategy
        * */

        // No strategy selected ball
        newShape = dis.loadPaintObj("", "Ball", "false");
        assertEquals("no strategy selected ball","Null", newShape.getStrategy().getName());
        assertEquals("no strategy selected ball","Ball", newShape.getType());
        // No strategy selected rectangle
        newShape = dis.loadPaintObj("", "Rectangle", "false");
        assertEquals("no strategy selected rectangle","Null", newShape.getStrategy().getName());
        assertEquals("no strategy selected rectangle","Rectangle", newShape.getType());

        /**
        * Requesting a shape with no shape type selected creates one shape of each type with a NullStrategy
        * */

        // No shape selected
        newShape = dis.loadPaintObj("StraightStrategy", "", "false");
        assertEquals("no shape selected", "Null", newShape.getStrategy().getName());

    }

    public void testSwitchStrategy(){
        DispatcherAdapter dis = new DispatcherAdapter();
        dis.setCanvasDims(new Point(800, 800));

        /**
        * Requesting switcher shapes to switch strategies with no shape types selected
        * should not cause any switcher shapes to switch strategies
        * */

        // Switch a strategy to a ball with no shape selected
        APaintObject newShape;
        newShape = dis.loadPaintObj("StraightStrategy", "Ball", "true");
        dis.switchStrategy("", "LoopStrategy");
        assertEquals("no shape selected", "StraightStrategy", newShape.getStrategy().getStrategy().getName());

        /**
         * Requesting switcher shapes to switch strategies with one shape type selected should only cause
         * switcher shapes of that type to switch strategies
         * */

        APaintObject test1, test2, test3;
        // Create 3 objects, 2 balls and 1 rectangle
        // balls will change the strategy and rectangle will not
        test1 = dis.loadPaintObj("StraightStrategy", "Ball", "true");
        test2 = dis.loadPaintObj("ColorChangeStrategy", "Ball", "true");
        test3 = dis.loadPaintObj("RotateStrategy", "Rectangle", "true");
        dis.switchStrategy("SizeChangeStrategy", "Ball");
        assertEquals("ball change", "SizeChangeStrategy", test1.getStrategy().getStrategy().getName());
        assertEquals("ball change", "SizeChangeStrategy", test2.getStrategy().getStrategy().getName());
        assertEquals("rectangle not change", "RotateStrategy", test3.getStrategy().getStrategy().getName());

        /**
        * Requesting switcher shapes to switch strategies with all shape types selected
        * should cause switcher shapes of all types to switch strategies
        * */

        test1 = dis.loadPaintObj("StraightStrategy", "Ball", "true");
        test2 = dis.loadPaintObj("ColorChangeStrategy", "Ball", "true");
        test3 = dis.loadPaintObj("RotateStrategy", "Rectangle", "true");
        dis.switchStrategy("SizeChangeStrategy", "Ball Rectangle");
        assertEquals("ball change", "SizeChangeStrategy", test1.getStrategy().getStrategy().getName());
        assertEquals("ball change", "SizeChangeStrategy", test2.getStrategy().getStrategy().getName());
        assertEquals("rectangle not change", "SizeChangeStrategy", test3.getStrategy().getStrategy().getName());
    }

}
