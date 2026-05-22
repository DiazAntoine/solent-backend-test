package com.diaz.mowitnow.model;

/**
 * Represents a position on the rectangular grid.
 */
public record Coordinates(int x, int y) {

    /**
     * Creates new coordinates following a movement of one coordinate along the orientation.
     * @param orientation The orientation in wich to move
     */
    public Coordinates move(Orientation orientation) {
        return new Coordinates(x + orientation.deltaX(), y + orientation.deltaY());
    }
}