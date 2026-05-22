package com.diaz.mowitnow;

import com.diaz.mowitnow.exception.InvalidInputException;
import com.diaz.mowitnow.model.Mower;
import com.diaz.mowitnow.parser.InputParser;
import com.diaz.mowitnow.parser.MowingPlan;
import com.diaz.mowitnow.service.MowingPlanExecutor;

import java.nio.file.Paths;
import java.util.List;

/**
 * Entry point of the MowItNow application.
 *
 * <p>This application reads a mowing plan from an input file,
 * parses its content, executes all mowing programs sequentially
 * and prints the final position of each mower.</p>
 *
 * <p>The input file path must be provided as a program argument.</p>
 */
public class MowItNowApplication {

    /**
     * Starts the application.
     *
     * <p>This method loads the input file, parses the mowing plan,
     * executes all mowing programs and prints the final mower positions
     * to the standard output.</p>
     *
     * @param args application arguments containing the input file path
     */
    public static void main(String[] args) {

        try {

            if (args.length != 1) {
                throw new InvalidInputException("Usage: java -jar solent-backend-test.jar <input-file>");
            }

            InputParser inputParser = new InputParser();
            MowingPlan mowingPlan = inputParser.parseInput(Paths.get(args[0])); //Parses the input file

            MowingPlanExecutor service = new MowingPlanExecutor();
            List<Mower> results = service.executeMowingPlan(mowingPlan); //Execute the MowingPlan

            results.forEach(System.out::print); //Prints the final position of each mower

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }
}
