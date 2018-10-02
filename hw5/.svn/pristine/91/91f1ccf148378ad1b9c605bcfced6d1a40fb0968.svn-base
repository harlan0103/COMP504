package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.paintobj.APaintObject;

public class MoveShapeCmd implements IPaintObjCmd {

    /*
    * MoveShapeCms controls shape moving
    * */
    @Override
    public void execute(APaintObject context){
        context.getStrategy().updateState(context);
    }
}
