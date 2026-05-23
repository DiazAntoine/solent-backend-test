package com.diaz.mowitnow.model;

/**
 * Represents a mower with its current position, orientation and possible actions.
 */
public final class Mower {

    private Position position;
    private Orientation orientation;

    public Mower(Position position, Orientation orientation) {
        this.position = position;
        this.orientation = orientation;
    }

    public Position getPosition() {
        return position;
    }


    public Orientation getOrientation() {
        return orientation;
    }

    /**
     * Rotates the mower 90° to the left (counter-clockwise).
     */
    public void turnLeft() {
        orientation = orientation.turnLeft();
    }

    /**
     * Rotates the mower 90° to the right (clockwise).
     */
    public void turnRight() {
        orientation = orientation.turnRight();
    }

    /**
     * Give the resulting Position of a forward movement
     */
    public Position nextForwardPosition() {
        return position.move(orientation);
    }

    /**
     * Move the mower to the given Position.
     * @param position The Position to move to
     */
    public void moveTo(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return position.x() + " " + position.y() + " " + orientation + " ";
    }
}
