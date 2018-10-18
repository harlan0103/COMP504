package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.DispatchAdapter;
import edu.rice.comp504.model.paint.Ball;
import org.omg.PortableInterceptor.DISCARDING;

/**
 * IdestroyInteract strategy is the destroy interact strategy
 * When two balls collide, both balls will be destroyed
 * */
public class IdestroyInteract implements IInteractStrategy{
    private static IInteractStrategy singleton;

    /**
     * Constructor
     * */
    public IdestroyInteract(){

    }

    /**
     * Make singleton
     * */
    public static IInteractStrategy makeStrategy(){
        if(singleton == null){
            singleton = new IdestroyInteract();
        }
        return singleton;
    }

    @Override
    public String getName(){
        return "destroyInteract";
    }

    @Override
    public void interact(Ball src, Ball dest, DispatchAdapter adapter){
        adapter.deleteObserver(src);
        adapter.deleteObserver(dest);
    }


    @Override
    public void setStrategy(IInteractStrategy strategy){
    }

    @Override
    public IInteractStrategy getStrategy(){
        return null;
    }
}
