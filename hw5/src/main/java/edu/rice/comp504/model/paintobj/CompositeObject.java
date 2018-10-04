package edu.rice.comp504.model.paintobj;

import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.awt.*;

public class CompositeObject extends APaintObject {
    // Create an array of APaintObjects
    private APaintObject[] children;

    public CompositeObject(IUpdateStrategy strategy, APaintObject[] children){
        super(new Point(0,0), new Point(0,0), "#FF3030", "CompositeObject", strategy);
        this.children = children;
        changeColor(this.children);
    }

    public void changeColor(APaintObject[] children){
        for(APaintObject ap: children){
            if(strategy.getName().equals("Null")){
                ap.setColor("#000000");
            }
            else{
                ap.setColor("#FF3030");
            }
        }
    }


    @Override
    public void rotate(double angle){
    }

    @Override
    public void collision(Point dims){

    }

    /*
     * @setSize
     * Set a new radius for the ABall
     * */
    @Override
    public void setSize(int newSize){
    }

    @Override
    public int getSize(){
        return 0;
    }

    /*
     * @nextLocation is override the method in APaintObject
     * change the location of the object to the next point
     * */
    @Override
    public void nextLocation(int velX, int velY) {

    }

    /*
    * Return a children array contains all sub shapes
    * */
    public APaintObject[] getChildren(){
        return this.children;
    }
}
