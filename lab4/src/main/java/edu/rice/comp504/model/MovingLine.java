package edu.rice.comp504.model;

import edu.rice.comp504.model.cmd.ILineCmd;
import edu.rice.comp504.model.strategy.CompositeStrategy;
import edu.rice.comp504.model.strategy.HorizontalStrategy;
import edu.rice.comp504.model.strategy.IUpdateStrategy;
import edu.rice.comp504.model.strategy.VerticalStrategy;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Represents a line that moves
 */
public class MovingLine implements Observer {
    private Point startLine, endLine, vel;
    private IUpdateStrategy strategy;

    /**
     * Constructor
     */
    public MovingLine() {
       init();
    }


    /**
     * Get the line velocity
     * @return The line velocity
     */
    public Point getVelocity() {
        return vel;
    }

    /**
     * Get the line strategy
     * @return The line strategy
     */
    public IUpdateStrategy getStrategy() {
        return strategy;
    }

    /**
     * Set the new strategy
     * @param strategy The new strategy.
     */
    public void setStrategy(IUpdateStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Get the line left end location
     * @return The start of the line
     */
    public Point getStartLine() {
        return startLine;
    }

    /**
     * Get the line right end location
     * @return The end of the line
     */
    public Point getEndLine() {
        return endLine;
    }

    /**
     * Set the new line location
     * @param velX  The velocity in the x direction
     * @param velY  The velocity in the y direction
     */
    public void nextLocation(int velX, int velY) {
        this.startLine.x += velX;
        this.startLine.y += velY;
        this.endLine.x += velX;
        this.endLine.y += velY;
    }

    /**
     * Generate a random number
     * @param base  The mininum value
     * @param limit The maximum number from the base
     * @return A randomly number
     */
    private int getRnd(int base, int limit) {
        return (int)Math.floor(Math.random() * limit + base);
    }

    /**
     * initialize the moving line
     */
    public void init() {
        this.vel = new Point(getRnd(5, 10), getRnd(5, 10));
        this.startLine = new Point(0, 0);
        this.endLine = new Point(200, 100);

        //randomly select initial strategy
        int sel = (int) Math.floor(Math.random()*3);

        switch(sel) {
            case 0:
                this.strategy = HorizontalStrategy.makeStrategy();
                break;
            case 1:
                this.strategy = VerticalStrategy.makeStrategy();
                break;
            default:
                this.strategy = new CompositeStrategy();
        }

    }

    /**
     * Update the line location
     */
    public void update(Observable obs, Object o) {
        //TODO: Implement me
        ((ILineCmd) o).execute(this);
    }

}