package edu.rice.comp504.model;

import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.paintobj.CompositeObject;
import edu.rice.comp504.model.strategy.IUpdateStrategy;
import edu.rice.comp504.model.strategy.StraightStrategy;
import junit.framework.TestCase;

import java.awt.*;
import java.util.Random;

public class DispatcherAdapterTest extends TestCase {

    public void testLoadPaintObj(){
        DispatcherAdapter dis = new DispatcherAdapter();
        String[] nullShape = {"Ball", "Rectangle", "Fish", "Triangle", "Rectangle", "Diamond", "Pentagon", "Hexagon", "Octagon"};
        dis.setCanvasDims(new Point(800, 800));
        APaintObject newShape;
        APaintObject[] arr;

        /** #1
        * Requesting a fish paint object with the dispatch adapter loadPaintObj method creates
        * the expected image paint object
        * */
        newShape = dis.loadPaintObj("StraightStrategy", "Fish", "false");
        assertEquals("Request a fish object", "Fish", newShape.getType());

        /** #2
        * Requesting multiple paint objects with the dispatch adapter loadPaintObj method creates
        * the expected composite paint object
        * */
        newShape = dis.loadPaintObj("StraightStrategy", "Triangle Diamond Rectangle Octagon", "false");
        assertEquals("chekc type is composite shape", "CompositeObject", newShape.getType());
        arr = ((CompositeObject) newShape).getChildren();
        for(int i = 0; i < arr.length; i++){
            assertEquals("check for each shape strategy", "StraightStrategy", arr[i].getStrategy().getName());
        }

        /** #3
        * Requesting an unknown paint object strategy creates a composite
        * Paint object with one pain object of each type with a NullStrategy
        * */
        newShape = dis.loadPaintObj("unknown", "unknown", "false");
        assertEquals("check type is composite shape", "CompositeObject", newShape.getType());
        arr = ((CompositeObject) newShape).getChildren();
        for(int i = 0; i < arr.length; i++){
            assertEquals("check for each type has null strategy", "Null", arr[i].getStrategy().getName());
            //String[] nullShape = {"Ball", "Rectangle", "Fish", "Triangle", "Rectangle", "Diamond", "Pentagon", "Hexagon", "Octagon"};
            assertEquals("check for each type name is correct", nullShape[i], arr[i].getType());
        }

        /** #4
        * Requesting a paint object with no strategies selected creates a composite paint object
        * with one paint object of each type with a NullStrategy
        * */
        newShape = dis.loadPaintObj("", "Triangle", "false");
        assertEquals("check type is composite shape", "CompositeObject", newShape.getType());
        arr = ((CompositeObject) newShape).getChildren();
        for(int i = 0; i < arr.length; i++){
            assertEquals("check for each type has null strategy", "Null", arr[i].getStrategy().getName());
            //String[] nullShape = {"Ball", "Rectangle", "Fish", "Triangle", "Rectangle", "Diamond", "Pentagon", "Hexagon", "Octagon"};
            assertEquals("check for each type name is correct", nullShape[i], arr[i].getType());
        }

        /** #5
        * Requesting a paint object with no paint object type selected creates a composite paint object
        * with one paint object of each type with a NullStrategy
        * */
        newShape = dis.loadPaintObj("StraightStrategy", "", "false");
        assertEquals("check type is composite shape", "CompositeObject", newShape.getType());
        arr = ((CompositeObject) newShape).getChildren();
        for(int i = 0; i < arr.length; i++){
            assertEquals("check for each type has null strategy", "Null", arr[i].getStrategy().getName());
            //String[] nullShape = {"Ball", "Rectangle", "Fish", "Triangle", "Rectangle", "Diamond", "Pentagon", "Hexagon", "Octagon"};
            assertEquals("check for each type name is correct", nullShape[i], arr[i].getType());
        }
    }

    public void testSwitchStrategy() {
        DispatcherAdapter dis = new DispatcherAdapter();
        dis.setCanvasDims(new Point(800, 800));
        APaintObject newShape;
        APaintObject[] arr;
        /** #6
         * Requesting switcher paint objects to switch strategies with no paint object types selected
         * Should not cause any switcher paint objects to switch strategies
         * */
        newShape = dis.loadPaintObj("StraightStrategy", "Triangle Rectangle Hexagon", "true");
        arr = ((CompositeObject) newShape).getChildren();
        for (int i = 0; i < arr.length; i++) {
            // Check shapes are switcher shape
            assertEquals("check shapes is switcher shape", "SwitcherStrategy", arr[i].getStrategy().getName());
            assertEquals("check strategy remains same", "StraightStrategy", arr[i].getStrategy().getStrategy().getName());
        }
        // With no type selected
        dis.switchStrategy("ColorChangeStrategy", "");
        APaintObject[] arrNew = ((CompositeObject) newShape).getChildren();
        for (int i = 0; i < arr.length; i++) {
            // Check shapes are switcher shape
            assertEquals("check strategy remains same", "StraightStrategy", arrNew[i].getStrategy().getStrategy().getName());
        }

        /** #7
         * Requesting switcher paint objects to switch strategies with one paint object type selected
         * should only cause switcher paint object of that type to switch strategies when there are no groups
         * */
        newShape = dis.loadPaintObj("StraightStrategy", "Triangle", "true");
        APaintObject test1, test2;
        test1 = dis.loadPaintObj("ColorChangeStrategy", "Diamond", "true");
        test2 = dis.loadPaintObj("SizeChangeStrategy", "Ball", "true");
        // Change the ball strategy
        dis.switchStrategy("FlickerStrategy", "Ball");
        assertEquals("check for strategy changes", "StraightStrategy", newShape.getStrategy().getStrategy().getName());
        assertEquals("check for strategy changes", "ColorChangeStrategy", test1.getStrategy().getStrategy().getName());
        assertEquals("check for strategy changes", "FlickerStrategy", test2.getStrategy().getStrategy().getName());

        /** #8
        * Requesting switcher paint object to switch strategies with one paint object type selected should
        * cause switcher objects of other types to switch strategies when they are in the same group
        * as the selected paint object type
        * */
        newShape = dis.loadPaintObj("StraightStrategy", "Triangle Hexagon Pentagon Rectangle", "true");
        // Change to the hexagon
        dis.switchStrategy("ColorChangeStrategy", "Hexagon");
        APaintObject[] testArr = ((CompositeObject) newShape).getChildren();
        String[] name = {"Triangle", "Hexagon", "Pentagon", "Rectangle"};
        for(int i = 0; i < testArr.length; i++){
            assertEquals("check for group strategy change", "ColorChangeStrategy", testArr[i].getStrategy().getStrategy().getName());
            assertEquals("check for shape name", name[i], testArr[i].getType());
        }
    }
}
