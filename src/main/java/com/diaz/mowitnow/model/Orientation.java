package com.diaz.mowitnow.model;

/**
 * Cardinal orientation with order : N=0, E=1, S=2, W=3 (clockwise).
 *
 * <p>Each orientation is associated with movement offset corresponding
 * to the mower moving forward on the grid.</p>
 *
 */
public enum Orientation {
    N(0, 1), E(1, 0), S(0, -1), W(-1, 0);

    /**
     * Horizontal movement offset associated with the orientation.
     */
    private final int deltaX;
    /**
     * Vertical movement offset associated with the orientation.
     */
    private final int deltaY;

    Orientation(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public int deltaX() {
        return deltaX;
    }

    public int deltaY() {
        return deltaY;
    }

    /**
     * Rotates 90° to the right (clockwise).
     */
    public Orientation turnRight() {
        return values()[(ordinal() + 1) % values().length];
    }

    /**
     * Rotates 90° to the left (counter-clockwise).
     */
    public Orientation turnLeft() {
        return values()[(ordinal() + values().length - 1) % values().length];
    }
}
