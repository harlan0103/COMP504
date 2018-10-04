package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.paintobj.CompositeObject;

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
        // If the type is composite type then find all children
        if(context.getType().equals("CompositeObject")){
            APaintObject[] arr = ((CompositeObject) context).getChildren();
            for(APaintObject child: arr){
                child.collision(this.dims);
                child.getStrategy().updateState(child);
            }
        }
        else{
            context.collision(this.dims);
            context.getStrategy().updateState(context);
        }
    }
}
