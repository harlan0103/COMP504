package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.paintobj.Ball;
import edu.rice.comp504.model.paintobj.Wall;
import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.awt.*;
import java.util.LinkedList;

/**
 * The UpdateStateCmd will possibly update either the paintobj location or attribute (color)
 */
public class UpdateStateCmd implements IPaintObjCmd {
    private Point dims;
    private LinkedList<Wall> iWalls;

    /**
     * The constructor
     * @param dims  The dispatch adapter
     * @param iWalls The canvas inner walls
     *
     */
    public UpdateStateCmd(Point dims, LinkedList<Wall> iWalls) {
        this.dims = dims;
        this.iWalls = iWalls;
    }

    /**
     * Update the state of the paintobj
     * @param context  The paintobj.
     */
    public void execute(APaintObject context) {
        //TODO define collision behavior with inner walls here. This command may possibly override a strategy.
        context.collision(this.dims);
        if(context.getType().equals("ball")){ // If it is a ball

            for(int i = 0; i < this.iWalls.size(); i++){
                int locationX = context.getLocation().x;    // Get the location x of the context
                int locationY = context.getLocation().y;    // Get the location y of the context
                int ballRadius = ((Ball) context).getRadius();
                int wallLocX = this.iWalls.get(i).getLocation().x;  // Get the wall location
                int wallLocY = this.iWalls.get(i).getLocation().y;
                int wallLength = this.iWalls.get(i).getLength();
                if((locationX + ballRadius >= wallLocX && locationX + ballRadius - context.getVelocity().x < wallLocX
                        && locationY + ballRadius >= wallLocY
                        && locationY - ballRadius <= (wallLocY + wallLength))){   // Ball will actually hit the wall
                    return;
                }
                else if((locationX - ballRadius <= wallLocX && locationX - ballRadius - context.getVelocity().x > wallLocX
                        && locationY + ballRadius >= wallLocY
                        && locationY - ballRadius <= (wallLocY + wallLength))){
                    return;
                }
            }
        }
        IUpdateStrategy strategy = context.getStrategy();
        strategy.updateState(context);
    }

}
