package com.diaz.mowitnow.service;

import com.diaz.mowitnow.model.Action;
import com.diaz.mowitnow.model.Position;
import com.diaz.mowitnow.model.Lawn;
import com.diaz.mowitnow.model.Mower;
import com.diaz.mowitnow.model.Orientation;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MowingActionExecutorTest {

    private final MowingActionExecutor executor = new MowingActionExecutor(new Lawn(5, 5));

    @Test
    void should_turn_mower_left() {
        Mower mower = new Mower(new Position(1, 2), Orientation.N);

        executor.execute(mower, Action.G);

        assertEquals(1, mower.getPosition().x());
        assertEquals(2, mower.getPosition().y());
        assertEquals(Orientation.W, mower.getOrientation());
    }

    @Test
    void should_turn_mower_right() {
        Mower mower = new Mower(new Position(1, 2), Orientation.N);

        executor.execute(mower, Action.D);

        assertEquals(1, mower.getPosition().x());
        assertEquals(2, mower.getPosition().y());
        assertEquals(Orientation.E, mower.getOrientation());
    }

    @Test
    void should_move_mower_forward_when_next_position_is_inside_lawn() {
        Mower mower = new Mower(new Position(1, 2), Orientation.N);

        executor.execute(mower, Action.A);

        assertEquals(1, mower.getPosition().x());
        assertEquals(3, mower.getPosition().y());
        assertEquals(Orientation.N, mower.getOrientation());
    }

    @Test
    void should_ignore_forward_action_when_next_position_is_outside_lawn() {
        Mower mower = new Mower(new Position(5, 5), Orientation.N);

        executor.execute(mower, Action.A);

        assertEquals(5, mower.getPosition().x());
        assertEquals(5, mower.getPosition().y());
        assertEquals(Orientation.N, mower.getOrientation());
    }

    @Test
    void should_execute_full_sequence_for_first_sample_mower() {
        Mower result = executor.execute(
                new Position(1, 2),
                Orientation.N,
                List.of(Action.G, Action.A, Action.G, Action.A, Action.G, Action.A, Action.G, Action.A, Action.A)
        );

        assertEquals(1, result.getPosition().x());
        assertEquals(3, result.getPosition().y());
        assertEquals(Orientation.N, result.getOrientation());
    }

    @Test
    void should_execute_full_sequence_for_second_sample_mower() {
        Mower result = executor.execute(
                new Position(3, 3),
                Orientation.E,
                List.of(Action.A, Action.A, Action.D, Action.A, Action.A, Action.D, Action.A, Action.D, Action.D, Action.A)
        );

        assertEquals(5, result.getPosition().x());
        assertEquals(1, result.getPosition().y());
        assertEquals(Orientation.E, result.getOrientation());
    }
}
