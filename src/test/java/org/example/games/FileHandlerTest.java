package org.example.games;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {
    FileHandler testFileHandle;
    GameMapper gameMapper;

    @BeforeEach
    void setUp() {
        testFileHandle = new FileHandler();
        gameMapper = new GameMapper();
    }

    @Test
    void testShouldGetGamesFromFile() {
        //given
        List<List<String>> expectedAttributesList = Arrays.asList(
                Arrays.asList("titles", "released" ,"developers", "publishers", "genres"),
                Arrays.asList("Adrift", "2015", "Three One Zero", "505 Games", "Simulator"),
                Arrays.asList("101 Dalmatians", "1999", "DreamForge Intertainment", "Disney Interactive", "\"Puzzle, Strategy, Adventure\""),
                Arrays.asList("Actua Pool", "2002", "Gremlin Interactive", "Disney Interactive", "Sport")
        );

        //when
        List<List<String>> actualAttributesList = FileHandler.getGamesFromFile(Path.of("src", "test", "resources", "test_games.csv"));

        //then
        assertEquals(expectedAttributesList, actualAttributesList);
        assertNotNull(actualAttributesList);
        assertFalse(actualAttributesList.isEmpty());
    }

    @Test
    void testShouldSaveGenresToFile() throws IOException {
        //given
        Path testExpectedGenresPath = Path.of("src", "test", "resources", "test_expected_genres.txt");
        TreeSet<String> expectedGenresSet = new TreeSet<>(Files.readAllLines(testExpectedGenresPath));

        //when
        Path testGamesPath = Path.of("src", "test", "resources", "test_games.csv");
        Path testActualGenresFilePath = Path.of("src", "test", "resources", "test_game_genres.txt");
        testFileHandle.saveGenresToFile(testGamesPath,testActualGenresFilePath);
        TreeSet<String> actualGenresSet = new TreeSet<>(Files.readAllLines(testActualGenresFilePath));

        //then
        assertEquals(expectedGenresSet, actualGenresSet);
        assertNotNull(actualGenresSet);
        assertFalse(actualGenresSet.isEmpty());
    }

    @Test
    void testShouldSaveSimulatorGamesToFile() throws IOException {
        //given
        Path testExpectedSimulatorGameFilePath = Path.of("src", "test", "resources", "test_expected_simulator.csv");
        List<String> expectedSimulatorGames = Files.readAllLines(testExpectedSimulatorGameFilePath);

        //when
        Path testGamesPath = Path.of("src", "test", "resources", "test_games.csv");
        Path testActualSimulatorGameFilePath = Path.of("src", "test", "resources", "test_simulator_games.csv");
        testFileHandle.saveSimulatorGamesToFile(testGamesPath,testActualSimulatorGameFilePath);
        List<String> actualSimulatorGames = Files.readAllLines(testActualSimulatorGameFilePath);

        //then
        assertEquals(expectedSimulatorGames, actualSimulatorGames);
        assertNotNull(actualSimulatorGames);
        assertFalse(actualSimulatorGames.isEmpty());

    }

    @Test
    void savePublishersToFile() throws IOException {
        //given
        Path testExpectedPublishersFilePath = Path.of("src", "test", "resources", "test_expected_publishers.csv");
        List<String> expectedSimulatorGames = Files.readAllLines(testExpectedPublishersFilePath);

        //when
        Path testGamesPath = Path.of("src", "test", "resources", "test_games.csv");
        Path testActualPublishersFilePath = Path.of("src", "test", "resources", "test_game_publishers.csv");
        testFileHandle.savePublishersToFile(testGamesPath,testActualPublishersFilePath);
        List<String> actualSimulatorGames = Files.readAllLines(testActualPublishersFilePath);

        //then
        assertEquals(expectedSimulatorGames, actualSimulatorGames);
        assertNotNull(actualSimulatorGames);
        assertFalse(actualSimulatorGames.isEmpty());
    }
}