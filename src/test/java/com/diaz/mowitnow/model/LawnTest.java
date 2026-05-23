package com.diaz.mowitnow.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LawnTest {

    private final Lawn lawn = new Lawn(5, 5);

    @Test
    void should_accept_position_inside_lawn() {
        assertTrue(lawn.isWithinBounds(new Position(0, 0)));
        assertTrue(lawn.isWithinBounds(new Position(3, 4)));
        assertTrue(lawn.isWithinBounds(new Position(5, 5)));
    }

    @Test
    void should_reject_position_outside_lawn() {
        assertFalse(lawn.isWithinBounds(new Position(-1, 0)));
        assertFalse(lawn.isWithinBounds(new Position(0, -1)));
        assertFalse(lawn.isWithinBounds(new Position(6, 5)));
        assertFalse(lawn.isWithinBounds(new Position(5, 6)));
    }
}
