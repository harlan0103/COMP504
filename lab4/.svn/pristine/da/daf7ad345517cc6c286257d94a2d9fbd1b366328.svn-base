package edu.rice.comp504.model.cmd;

//import com.sun.scenario.animation.shared.SingleLoopClipEnvelope;
import edu.rice.comp504.model.MovingLine;
import edu.rice.comp504.model.strategy.CompositeStrategy;
import edu.rice.comp504.model.strategy.HorizontalStrategy;
import edu.rice.comp504.model.strategy.VerticalStrategy;

public class SwitchCommand implements ILineCmd {

    @Override
    public void execute(MovingLine context){
        String strategyName = context.getStrategy().getName();
        System.out.println(strategyName);
        switch (strategyName){
            case "horizontal":
                context.setStrategy(VerticalStrategy.makeStrategy());
                break;
            case "vertical":
                context.setStrategy(new CompositeStrategy());
                break;
            case "composite":
                context.setStrategy(HorizontalStrategy.makeStrategy());
                break;
        }
    }
}
