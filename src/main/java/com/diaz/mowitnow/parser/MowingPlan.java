package com.diaz.mowitnow.parser;

import com.diaz.mowitnow.model.Action;
import com.diaz.mowitnow.model.Position;
import com.diaz.mowitnow.model.Lawn;
import com.diaz.mowitnow.model.Orientation;

import java.util.List;

/**
 * Represents a mowing scenario with a defined Lawn, positions for mowers and their instructions.
 */
public record MowingPlan(Lawn lawn, List<MowerProgram> programs) {
    /**
     * Represents a mowing programm for a mower with its starting Position and orientation, and actions to execute.
     */
    public record MowerProgram(Position position, Orientation orientation, List<Action> actions) {
    }
}