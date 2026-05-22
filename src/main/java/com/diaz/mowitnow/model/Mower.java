package com.diaz.mowitnow.model;

/**
 * Represents a mower with its current position, orientation and possible actions.
 */
public final class Mower {

    private Coordinates coordinates;
    private Orientation orientation;

    public Mower(Coordinates coordinates, Orientation orientation) {
        this.coordinates = coordinates;
        this.orientation = orientation;
    }

    public Coordinates getCoordinates() {
        return coordinates;
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
     * Give the resulting coordinates of a forward movement
     */
    public Coordinates nextForwardPosition() {
        return coordinates.move(orientation);
    }

    /**
     * Move the mower to the given coodinates.
     * @param coordinates The coordinates to move to
     */
    public void moveTo(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return coordinates.x() + " " + coordinates.y() + " " + orientation + " ";
    }
}
