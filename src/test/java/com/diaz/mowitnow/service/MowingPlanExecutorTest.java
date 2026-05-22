package com.diaz.mowitnow.service;

import com.diaz.mowitnow.model.Action;
import com.diaz.mowitnow.model.Coordinates;
import com.diaz.mowitnow.model.Lawn;
import com.diaz.mowitnow.model.Mower;
import com.diaz.mowitnow.model.Orientation;
import com.diaz.mowitnow.parser.MowingPlan;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MowingPlanExecutorTest {

    @Test
    void should_execute_all_mower_programs_sequentially() {
        MowingPlan mowingPlan = new MowingPlan(
                new Lawn(new Coordinates(5, 5)),
                List.of(
                        new MowingPlan.MowerProgram(
                                new Coordinates(1, 2),
                                Orientation.N,
                                List.of(Action.G, Action.A, Action.G, Action.A, Action.G, Action.A, Action.G, Action.A, Action.A)
                        ),
                        new MowingPlan.MowerProgram(
                                new Coordinates(3, 3),
                                Orientation.E,
                                List.of(Action.A, Action.A, Action.D, Action.A, Action.A, Action.D, Action.A, Action.D, Action.D, Action.A)
                        )
                )
        );

        List<Mower> results = new MowingPlanExecutor().executeMowingPlan(mowingPlan);

        assertEquals(2, results.size());

        assertEquals(1, results.get(0).getCoordinates().x());
        assertEquals(3, results.get(0).getCoordinates().y());
        assertEquals(Orientation.N, results.get(0).getOrientation());

        assertEquals(5, results.get(1).getCoordinates().x());
        assertEquals(1, results.get(1).getCoordinates().y());
        assertEquals(Orientation.E, results.get(1).getOrientation());

    }
}
