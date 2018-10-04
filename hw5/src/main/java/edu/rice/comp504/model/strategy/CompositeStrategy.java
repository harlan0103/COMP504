package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.paintobj.CompositeObject;

/**
 * The CompositeStrategy uses the Composite Design Pattern to update the ball.   As a result, it calls
 * the updateState method on each child
 */
public class CompositeStrategy implements IUpdateStrategy {
    private IUpdateStrategy[] children;

    /**
     * Constructor
     */
    public CompositeStrategy(IUpdateStrategy[] children) {
        this.children = children;
    }

    /**
     * Get the ball name
     * @return ball name
     */
    public String getName() {
        return "composite";
    }

    @Override
    public IUpdateStrategy getStrategy(){
        return null;
    }

    @Override
    public void setStrategy(IUpdateStrategy strategy){}
    /**
     * Update the ball state in the ball world
     * @param context The ball to update
     */
    public void updateState(APaintObject context) {
        if(context.getType().equals("CompositeObject")){
            // This is a composite object
            APaintObject[] arr = ((CompositeObject) context).getChildren();
            for(APaintObject child : arr){
                for(IUpdateStrategy strategy : children){
                    strategy.updateState(child);
                }
            }
        }
        else{
            for(IUpdateStrategy strategy : children){
                strategy.updateState(context);
            }
        }
    }
}
