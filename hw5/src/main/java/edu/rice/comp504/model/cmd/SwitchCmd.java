package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.paintobj.CompositeObject;
import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.util.ArrayList;

public class SwitchCmd implements IPaintObjCmd {
    private IUpdateStrategy strategy;
    private ArrayList<String> typeList;

    public SwitchCmd(IUpdateStrategy strategy, ArrayList<String> typeList){
        this.strategy = strategy;
        this.typeList = typeList;
    }

    /*
     * MoveShapeCms controls shape moving
     * */
    @Override
    public void execute(APaintObject context){
        if(context.getType().equals("CompositeObject")){
            // This is a composite object
            APaintObject[] arr = ((CompositeObject) context).getChildren();
            for(APaintObject child : arr){
                //For each child, execute strategy switch
                executeStrategySwitch(child);
            }
        }
        else{
            executeStrategySwitch(context);
        }
    }

    public void executeStrategySwitch(APaintObject context){
        if(context.getStrategy().getName().equals("SwitcherStrategy")){
            // Check if the current ball is a SwitcherShape
            if(typeList.contains(context.getType())){
                // If shape selector contains current context shape
                // Then we make a change to the shape
                context.getStrategy().setStrategy(strategy);
            }
        }
    }
}
