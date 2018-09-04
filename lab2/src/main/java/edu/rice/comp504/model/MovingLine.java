package edu.rice.comp504.model;

/**
 * Represents a line that moves
 */
public class MovingLine {
    private int startX, startY, endX, endY, velX;

    /**
     * MovingLine constructor
     * @param velX velocity in the x direction
     */
    public MovingLine(int velX) {
        this.velX = velX;
        resetPos();
    }

    /**
     * Update the line position
     */
    public void update() {
        //TODO: Update the line position in the x direction only
        startX += velX;
        endX += velX;
    }

    /**
     * Reset the line position
     */
    public void resetPos() {
        startX = 0;
        startY = 0;
        endX = 200;
        endY = 100;
    }
}
