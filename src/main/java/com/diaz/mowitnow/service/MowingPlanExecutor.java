package com.diaz.mowitnow.service;

import com.diaz.mowitnow.model.Mower;
import com.diaz.mowitnow.parser.MowingPlan;

import java.util.List;

/**
 * Executes all mowing plans sequentially for a given mowing program.
 *
 * <p>This executor is responsible for orchestrating the execution of each
 * {@link MowingPlan.MowerProgram}. Each mower is executed one after another in the
 * order defined by the program.</p>
 */
public class MowingPlanExecutor {

    /**
     * Executes all mowing programs contained in the given MowingPlan.
     * Mowers are executed sequentially.
     *
     * @param mowingPlan the mowing plan to execute
     * @return the final state of all executed mowers
     */
    public List<Mower> executeMowingPlan (MowingPlan mowingPlan) {
        MowingActionExecutor mowingActionexecutor = new MowingActionExecutor(mowingPlan.lawn());
        return mowingPlan.programs().stream()
                .map(program -> mowingActionexecutor.execute(program.position(), program.orientation(), program.actions()))
                .toList();
    }
}
