package com.diaz.mowitnow.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MowerTest {

    @Test
    void should_turn_left_without_changing_position() {
        Mower mower = new Mower(new Position(1, 2), Orientation.N);

        mower.turnLeft();

        assertEquals(1, mower.getPosition().x());
        assertEquals(2, mower.getPosition().y());
        assertEquals(Orientation.W, mower.getOrientation());
    }

    @Test
    void should_turn_right_without_changing_position() {
        Mower mower = new Mower(new Position(1, 2), Orientation.N);

        mower.turnRight();

        assertEquals(1, mower.getPosition().x());
        assertEquals(2, mower.getPosition().y());
        assertEquals(Orientation.E, mower.getOrientation());
    }

    @Test
    void should_compute_next_forward_position_without_moving() {
        Mower mower = new Mower(new Position(1, 2), Orientation.N);

        Position result = mower.nextForwardPosition();

        assertEquals(new Position(1, 3), result);

        assertEquals(1, mower.getPosition().x());
        assertEquals(2, mower.getPosition().y());
        assertEquals(Orientation.N, mower.getOrientation());
    }

    @Test
    void should_move_to_given_position() {
        Mower mower = new Mower(new Position(1, 2), Orientation.N);

        mower.moveTo(new Position(1, 3));

        assertEquals(1, mower.getPosition().x());
        assertEquals(3, mower.getPosition().y());
        assertEquals(Orientation.N, mower.getOrientation());
    }

    @Test
    void should_print_current_state() {
        Mower mower = new Mower(new Position(1, 3), Orientation.N);

        assertEquals("1 3 N ", mower.toString());
    }
}
