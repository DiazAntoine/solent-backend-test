package com.diaz.mowitnow.model;

/**
 * Represents a rectangular lawn.
 * The lower-left corner is assumed to be (0, 0).
 * maxX and maxY defines the upper-right corner.
 */
public record Lawn(int maxX, int maxY) {

    /**
     * Returns true if the given Position is within the Lawn boundaries.
     * @param position The Position to test
     */
    public boolean isWithinBounds(Position position) {
        return position.x() >= 0
                && position.y() >= 0
                && position.x() <= maxX
                && position.y() <= maxY;
    }
}
