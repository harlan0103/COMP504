package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.DispatchAdapter;
import edu.rice.comp504.model.paint.Ball;

import java.awt.*;

public class IBallMoveCmd implements IBallCmd{
    private Point dims;
    private DispatchAdapter adapter;

    public IBallMoveCmd(Point dims, DispatchAdapter adapter){
        this.adapter = adapter;
        this.dims = dims;
    }
    /*
     * MoveShapeCms controls shape moving
     * */
    @Override
    public void execute(Ball context){
        context.wallCollision(this.dims);
        context.getUpdateStrategy().updateState(context);

        adapter.setChange();
        adapter.notifyObservers(new IBallCmd() {
            @Override
            public void execute(Ball dest) {
                if(context != dest){
                    boolean ballInteract = context.ballCollision(dest);
                    if(ballInteract == true){
                        context.getInteractStrategy().interact(context, dest, adapter);
                    }
                }
            }
        });
    }
}
