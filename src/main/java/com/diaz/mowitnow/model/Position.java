package com.diaz.mowitnow.model;

/**
 * Represents a position on the rectangular grid.
 */
public record Position(int x, int y) {

    /**
     * Creates a new Position following a movement of one coordinate along the orientation.
     * @param orientation The orientation in wich to move
     */
    public Position move(Orientation orientation) {
        return new Position(x + orientation.deltaX(), y + orientation.deltaY());
    }
}