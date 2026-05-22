package com.diaz.mowitnow.parser;

import com.diaz.mowitnow.exception.InvalidInputException;
import com.diaz.mowitnow.model.Action;
import com.diaz.mowitnow.model.Coordinates;
import com.diaz.mowitnow.model.Orientation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InputParserTest {

    private final InputParser parser = new InputParser();

    @TempDir
    Path tempDir;

    @Test
    void should_reject_empty_input_file() throws Exception {
        Path file = writeInput("   \n\n");

        InvalidInputException exception = assertThrows(InvalidInputException.class, () -> parser.parseInput(file));

        assertEquals("Input file is empty", exception.getMessage());
    }

    @Test
    void should_ignore_blank_lines_and_extra_spaces() throws Exception {
        Path file = writeInput("""
                   5   5

                   1   2   N
                   GAGAGAGAA
                """);

        MowingPlan result = parser.parseInput(file);

        assertEquals(new Coordinates(5, 5), result.lawn().upperRightCorner());
        assertEquals(1, result.programs().size());
        assertEquals(new Coordinates(1, 2), result.programs().get(0).coordinates());
        assertEquals(Orientation.N, result.programs().get(0).orientation());
    }


    @Test
    void should_reject_file_with_incomplete_mower_program() throws Exception {
        Path file = writeInput("""
                5 5
                1 2 N
                """);

        InvalidInputException exception = assertThrows(InvalidInputException.class, () -> parser.parseInput(file));

        assertEquals("Each mower must be described by exactly two lines", exception.getMessage());
    }

    @Test
    void should_reject_invalid_lawn_line_format() throws Exception {
        Path file = writeInput("""
                5 5 5
                1 2 N
                GAG
                """);

        InvalidInputException exception = assertThrows(InvalidInputException.class, () -> parser.parseInput(file));

        assertEquals("Lawn line must contain exactly two coordinates : x y", exception.getMessage());
    }

    @Test
    void should_reject_negative_lawn_coordinates() throws Exception {
        Path file = writeInput("""
                -1 5
                1 2 N
                GAG
                """);

        InvalidInputException exception = assertThrows(InvalidInputException.class, () -> parser.parseInput(file));

        assertEquals("Coordinates of the lawn must be positive or zero", exception.getMessage());
    }

    @Test
    void should_reject_non_numeric_lawn_coordinates() throws Exception {
        Path file = writeInput("""
                X 5
                1 2 N
                GAG
                """);

        InvalidInputException exception = assertThrows(InvalidInputException.class, () -> parser.parseInput(file));

        assertEquals("Invalid coordinate: X", exception.getMessage());
    }

    @Test
    void should_reject_invalid_mower_position_format() throws Exception {
        Path file = writeInput("""
                5 5
                1 N
                GAG
                """);

        InvalidInputException exception = assertThrows(InvalidInputException.class, () -> parser.parseInput(file));

        assertEquals("Mower position line must match: x y orientation", exception.getMessage());
    }

    @Test
    void should_reject_unknown_mower_orientation() throws Exception {
        Path file = writeInput("""
                5 5
                1 2 X
                GAG
                """);

        InvalidInputException exception = assertThrows(InvalidInputException.class, () -> parser.parseInput(file));

        assertEquals("Unknown orientation for mower: X", exception.getMessage());
    }

    @Test
    void should_reject_initial_mower_position_outside_lawn() throws Exception {
        Path file = writeInput("""
                5 5
                6 2 N
                GAG
                """);

        InvalidInputException exception = assertThrows(InvalidInputException.class, () -> parser.parseInput(file));

        assertEquals("Initial mower position is outside the lawn: Coordinates[x=6, y=2]", exception.getMessage());
    }

    @Test
    void should_reject_unknown_action() throws Exception {
        Path file = writeInput("""
                5 5
                1 2 N
                GAX
                """);

        InvalidInputException exception = assertThrows(InvalidInputException.class, () -> parser.parseInput(file));

        assertEquals("Unknown action for mower: X", exception.getMessage());
    }

    @Test
    void should_parse_valid_input_file() throws Exception {
        Path file = writeInput("""
                5 5
                1 2 N
                GAGAGAGAA
                3 3 E
                AADAADADDA
                """);

        MowingPlan result = parser.parseInput(file);

        assertEquals(new Coordinates(5, 5), result.lawn().upperRightCorner());
        assertEquals(2, result.programs().size());

        MowingPlan.MowerProgram firstProgram = result.programs().get(0);
        assertEquals(new Coordinates(1, 2), firstProgram.coordinates());
        assertEquals(Orientation.N, firstProgram.orientation());
        assertEquals(List.of(Action.G, Action.A, Action.G, Action.A, Action.G, Action.A, Action.G, Action.A, Action.A), firstProgram.actions());

        MowingPlan.MowerProgram secondProgram = result.programs().get(1);
        assertEquals(new Coordinates(3, 3), secondProgram.coordinates());
        assertEquals(Orientation.E, secondProgram.orientation());
        assertEquals(List.of(Action.A, Action.A, Action.D, Action.A, Action.A, Action.D, Action.A, Action.D, Action.D, Action.A), secondProgram.actions());
    }

    private Path writeInput(String content) throws IOException {
        Path file = tempDir.resolve("input.txt");
        Files.writeString(file, content);
        return file;
    }
}
