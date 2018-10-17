package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.paint.Ball;

import java.awt.*;

public class IBallMoveCmd implements IBallCmd{
    private Point dims;

    public IBallMoveCmd(Point dims){
        this.dims = dims;
    }
    /*
     * MoveShapeCms controls shape moving
     * */
    @Override
    public void execute(Ball context){
        context.wallCollision(this.dims);
        context.getUpdateStrategy().updateState(context);
    }
}
