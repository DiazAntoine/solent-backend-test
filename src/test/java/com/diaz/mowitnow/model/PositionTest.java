package com.diaz.mowitnow.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PositionTest {

    @Test
    void should_move_one_cell_to_the_north() {
        Position position = new Position(1, 2);

        Position result = position.move(Orientation.N);

        assertEquals(new Position(1, 3), result);
    }

    @Test
    void should_move_one_cell_to_the_east() {
        Position position = new Position(1, 2);

        Position result = position.move(Orientation.E);

        assertEquals(new Position(2, 2), result);
    }

    @Test
    void should_move_one_cell_to_the_south() {
        Position position = new Position(1, 2);

        Position result = position.move(Orientation.S);

        assertEquals(new Position(1, 1), result);
    }

    @Test
    void should_move_one_cell_to_the_west() {
        Position position = new Position(1, 2);

        Position result = position.move(Orientation.W);

        assertEquals(new Position(0, 2), result);
    }
}
