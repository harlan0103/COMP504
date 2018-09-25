package edu.rice.comp504.model;

import edu.rice.comp504.model.cmd.MoveCommand;
import edu.rice.comp504.model.cmd.SwitchCommand;

import java.util.Observable;

/**
 * The dispatch adapter is responsible for ensuring that all the lines in the LineWorld are properly updated
 */
public class DispatchAdapter extends Observable {

    /**
     * constructor
     */
    public DispatchAdapter() {

    }

    /**
     * Return a new line with a random initial strategy
     * @return A new line
     */
    public MovingLine loadLine() {
        //TODO: Implement me
        MovingLine newLine = new MovingLine();
        addObserver(newLine);
        return newLine;
    }

    /**
     * Update the lines in LineWorld
     */
    public void updateLineWorld() {
        //TODO: Implement me
        if(countObservers() != 0){
            setChanged();
            MoveCommand m = new MoveCommand();
            notifyObservers(m);
        }
    }

    /**
     * Switch the line strategy
     */
    public void switchStrategy() {
        //TODO: Implement me
        //System.out.println("switchStrategy");
        setChanged();
        SwitchCommand s = new SwitchCommand();
        notifyObservers(s);
    }
}
