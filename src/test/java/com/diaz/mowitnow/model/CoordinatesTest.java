package com.diaz.mowitnow.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CoordinatesTest {

    @Test
    void should_move_one_cell_to_the_north() {
        Coordinates coordinates = new Coordinates(1, 2);

        Coordinates result = coordinates.move(Orientation.N);

        assertEquals(new Coordinates(1, 3), result);
    }

    @Test
    void should_move_one_cell_to_the_east() {
        Coordinates coordinates = new Coordinates(1, 2);

        Coordinates result = coordinates.move(Orientation.E);

        assertEquals(new Coordinates(2, 2), result);
    }

    @Test
    void should_move_one_cell_to_the_south() {
        Coordinates coordinates = new Coordinates(1, 2);

        Coordinates result = coordinates.move(Orientation.S);

        assertEquals(new Coordinates(1, 1), result);
    }

    @Test
    void should_move_one_cell_to_the_west() {
        Coordinates coordinates = new Coordinates(1, 2);

        Coordinates result = coordinates.move(Orientation.W);

        assertEquals(new Coordinates(0, 2), result);
    }
}
