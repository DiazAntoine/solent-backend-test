package com.diaz.mowitnow.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LawnTest {

    private final Lawn lawn = new Lawn(new Coordinates(5, 5));

    @Test
    void should_accept_coordinates_inside_lawn() {
        assertTrue(lawn.isWithinBounds(new Coordinates(0, 0)));
        assertTrue(lawn.isWithinBounds(new Coordinates(3, 4)));
        assertTrue(lawn.isWithinBounds(new Coordinates(5, 5)));
    }

    @Test
    void should_reject_coordinates_outside_lawn() {
        assertFalse(lawn.isWithinBounds(new Coordinates(-1, 0)));
        assertFalse(lawn.isWithinBounds(new Coordinates(0, -1)));
        assertFalse(lawn.isWithinBounds(new Coordinates(6, 5)));
        assertFalse(lawn.isWithinBounds(new Coordinates(5, 6)));
    }
}
