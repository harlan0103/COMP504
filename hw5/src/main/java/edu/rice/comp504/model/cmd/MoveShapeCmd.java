package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.paintobj.APaintObject;

import java.awt.*;

public class MoveShapeCmd implements IPaintObjCmd {
    private Point dims;

    public MoveShapeCmd(Point dims){
        this.dims = dims;
    }
    /*
    * MoveShapeCms controls shape moving
    * */
    @Override
    public void execute(APaintObject context){
        context.collision(this.dims);
        context.getStrategy().updateState(context);
    }
}
