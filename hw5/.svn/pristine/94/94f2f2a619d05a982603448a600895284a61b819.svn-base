package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.paintobj.APaintObject;
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
        if(context.getSwitcherBall().equals("true")){
            // Current ball is a switcher ball
            if(typeList.contains(context.getType())){
                // If shape selector contains current context shape
                // Then we make a change to the shape
                context.getStrategy().setStrategy(strategy);
            }
        }
    }
}
