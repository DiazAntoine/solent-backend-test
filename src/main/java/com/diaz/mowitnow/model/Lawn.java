package com.diaz.mowitnow.model;

/**
 * Represents a rectangular lawn.
 * The lower-left corner is assumed to be (0, 0).
 * The upper-right corner defines the bounds.
 */
public record Lawn(Coordinates upperRightCorner) {

    /**
     * Returns true if the given coordinates is within the Lawn boundaries.
     * @param coordinates The coordinates to test
     */
    public boolean isWithinBounds(Coordinates coordinates) {
        return coordinates.x() >= 0
                && coordinates.y() >= 0
                && coordinates.x() <= upperRightCorner.x()
                && coordinates.y() <= upperRightCorner.y();
    }
}
