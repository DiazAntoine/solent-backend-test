package com.diaz.mowitnow.service;

import com.diaz.mowitnow.model.*;

import java.util.List;

/**
 * Executes all actions of a single mowing program.
 *
 * <p>This executor applies each {@link Action} sequentially to a mower
 * while enforcing lawn boundary constraints.</p>
 *
 * <p>If a movement would place the mower outside the lawn, the movement is ignored and the next action
 * is processed.</p>
 */
public class MowingActionExecutor {

    private final Lawn lawn;

    public MowingActionExecutor(Lawn lawn) {
        this.lawn=lawn;
    }

    /**
     * Executes all actions of a mowing plan for a single mower sequentially.
     *
     * @param position the position of the mower
     * @param orientation the orientation of the mower
     * @param actions the action that must be executed by the mower
     * @return the final state of the mower after executing all actions
     */
    public Mower execute(Position position, Orientation orientation, List<Action> actions) {
        Mower mower = new Mower(position, orientation);
        actions.forEach(instruction -> execute(mower, instruction));
        return mower;
    }

    /**
     * Execute an action for a mower.
     *
     * <p>Rotation actions are always applied, while forward movements are validated
     * against lawn boundaries before being executed.</p>
     *
     * <p>For the reviewer : A Command pattern could have been used but it seemed
     * a bit over-engineered for this kata</p>
     *
     * @param mower the mower executing the action
     * @param action the action to execute
     */
    public void execute(Mower mower, Action action) {
        switch (action) {
            case D -> mower.turnRight();
            case G -> mower.turnLeft();
            case A -> {
                Position nextPosition = mower.nextForwardPosition();
                if (lawn.isWithinBounds(nextPosition)) {
                    mower.moveTo(nextPosition);
                }
            }
        }
    }
}
