package com.diaz.mowitnow.parser;

import com.diaz.mowitnow.exception.InvalidInputException;
import com.diaz.mowitnow.model.Action;
import com.diaz.mowitnow.model.Coordinates;
import com.diaz.mowitnow.model.Lawn;
import com.diaz.mowitnow.model.Orientation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Parses an input file and builds a {@link MowingPlan} containing the lawn, position and actions for mowers.
 *
 * <p>Expected format:
 * <pre>
 *   5 5
 *   1 2 N
 *   GAGAGAGAA
 *   3 3 E
 *   AADAADADDA
 * </pre>
 */
public class InputParser {

    /**
     * Parses the given file path into a {@link MowingPlan}
     *
     * @param path path to the input file
     * @return a {@link MowingPlan} ready for simulation
     * @throws InvalidInputException if the file format is invalid
     * @throws IOException           if the file cannot be read
     */
    public MowingPlan parseInput (Path path) throws IOException, InvalidInputException {
        List<String> lines = Files.readAllLines(path);

        //Remove empty lines and useless blank spaces
        List<String> meaningfulLines = lines.stream()
                .map(String::trim)
                .filter(line -> !line.isBlank())
                .toList();

        if (meaningfulLines.isEmpty()) {
            throw new InvalidInputException("Input file is empty");
        }
        if ((meaningfulLines.size() - 1) % 2 != 0) {
            throw new InvalidInputException("Each mower must be described by exactly two lines");
        }

        Lawn lawn = parseLawn(meaningfulLines.get(0));

        List<MowingPlan.MowerProgram> mowerPrograms = parseMowers(meaningfulLines, lawn);

        return new MowingPlan(lawn, List.copyOf(mowerPrograms));
    }

    /**
     * Parses the line for the Lawn boundaries.
     * @param line the text line to parse
     * @return The created Lawn
     * @throws InvalidInputException if the file format is invalid
     */
    private Lawn parseLawn(String line) throws InvalidInputException {
        String[] parts = line.split("\\s+");
        if (parts.length != 2) {
            throw new InvalidInputException("Lawn line must contain exactly two coordinates : x y");
        }
        int x = parseInt(parts[0]);
        int y = parseInt(parts[1]);
        if (x < 0 || y < 0) {
            throw new InvalidInputException("Coordinates of the lawn must be positive or zero");
        }
        return new Lawn(new Coordinates(x, y));
    }

    /**
     * Parses the lines for the position and actions of mowers.
     *
     * <p>Reads lines in pairs. First parses the coordinates and orientation of a mower,
     * then its actions, and store them into a MowerProgram. </p>
     *
     * @param meaningfulLines the lines to parse
     * @param lawn The already parsed Lawn
     * @return MowerProgram wich represent the position and actions of mowers.
     * @throws InvalidInputException if the file format is invalid
     */
    private List<MowingPlan.MowerProgram> parseMowers (List<String> meaningfulLines, Lawn lawn) throws InvalidInputException {
        List<MowingPlan.MowerProgram> mowerPrograms = new ArrayList<>();

        for (int index = 1; index < meaningfulLines.size(); index += 2) {
            String[] parts = meaningfulLines.get(index).split("\\s+");
            if (parts.length != 3) {
                throw new InvalidInputException("Mower position line must match: x y orientation");
            }
            Orientation orientation = createOrientation(parts[2]);
            Coordinates coordinates = new Coordinates(parseInt(parts[0]), parseInt(parts[1]));

            if (!lawn.isWithinBounds(coordinates)) {
                throw new InvalidInputException("Initial mower position is outside the lawn: " + coordinates);
            }

            List<Action> actions = parseActions(meaningfulLines.get(index + 1));
            mowerPrograms.add(new MowingPlan.MowerProgram(coordinates, orientation, actions));
        }

        return mowerPrograms;
    }

    /**
     * Create an Orientation from a String character
     * @param orientationRaw The String character representing the orientation
     * @return The created Orientation
     * @throws InvalidInputException if the file format is invalid
     */
    private Orientation createOrientation (String orientationRaw) throws InvalidInputException {
        try {
            return Orientation.valueOf(orientationRaw);
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException("Unknown orientation for mower: " + orientationRaw);
        }
    }

    /**
     * Parses the line for the mower's actions
     * @param line The line to parse
     * @return The List<Action> created
     * @throws InvalidInputException if the file format is invalid
     */
    private List<Action> parseActions(String line) throws InvalidInputException {
        List<Action> actions = new ArrayList<>();

        //Split the String character into an array
        String[] actionRawSplitted = line.chars()
                .mapToObj(c -> String.valueOf((char) c))
                .toArray(String[]::new);

        for (String actionRaw : actionRawSplitted) {
            try {
                actions.add(Action.valueOf(actionRaw));
            } catch (IllegalArgumentException e) {
                throw new InvalidInputException("Unknown action for mower: " + actionRaw);
            }
        }

        return actions;
    }

    /**
     * Method used to parse a number in string format into a Integer
     * @param raw The String character representing the number
     * @return The int parsed
     * @throws InvalidInputException if the file format is invalid
     */
    private int parseInt(String raw) throws InvalidInputException {
        try {
            return Integer.parseInt(raw);
        } catch (NumberFormatException exception) {
            throw new InvalidInputException("Invalid coordinate: " + raw);
        }
    }
}
