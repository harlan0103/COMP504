package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.MovingLine;

public class MoveCommand implements ILineCmd {

    @Override
    public void execute(MovingLine context){
        if(context.getStrategy().getName().equals("composite")){
            return;
        }
        context.getStrategy().updateState(context);
    }
}
