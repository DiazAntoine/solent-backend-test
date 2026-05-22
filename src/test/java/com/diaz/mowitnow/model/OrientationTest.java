package com.diaz.mowitnow.model;

import com.diaz.mowitnow.model.Orientation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrientationTest {

    @Test
    void should_turn_right_clockwise() {
        assertEquals(Orientation.E, Orientation.N.turnRight());
        assertEquals(Orientation.S, Orientation.E.turnRight());
        assertEquals(Orientation.W, Orientation.S.turnRight());
        assertEquals(Orientation.N, Orientation.W.turnRight());
    }

    @Test
    void should_turn_left_counter_clockwise() {
        assertEquals(Orientation.W, Orientation.N.turnLeft());
        assertEquals(Orientation.S, Orientation.W.turnLeft());
        assertEquals(Orientation.E, Orientation.S.turnLeft());
        assertEquals(Orientation.N, Orientation.E.turnLeft());
    }

    @Test
    void should_expose_expected_movement_delta() {
        assertEquals(0, Orientation.N.deltaX());
        assertEquals(1, Orientation.N.deltaY());
        assertEquals(1, Orientation.E.deltaX());
        assertEquals(0, Orientation.E.deltaY());
        assertEquals(0, Orientation.S.deltaX());
        assertEquals(-1, Orientation.S.deltaY());
        assertEquals(-1, Orientation.W.deltaX());
        assertEquals(0, Orientation.W.deltaY());
    }
}
