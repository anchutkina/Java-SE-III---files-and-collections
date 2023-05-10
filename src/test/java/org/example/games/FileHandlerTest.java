package org.example.games;

import org.example.GlobalConstants;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {
    //not working yet!!
    FileHandler fileHandler = new FileHandler();

    @Test
    void testShouldGetGamesFromFile() {
        //given
        List<List<String>> expectedAttributesList = Arrays.asList(
                Arrays.asList("game 1", "date 1", "developer 1", "publisher 1", "genre 1"),
                Arrays.asList("game 2", "date 2", "developer 2", "publisher 2", "genre 2.1, genre 2.2, genre 2.3"),
                Arrays.asList("game 3", "date 3", "developer 3", "publisher 3", "genre 3")
        );

        //when
        Path testFilePath = Path.of("src", "test", "resources", "test_games.csv");
        List<List<String>> actualAttributesList = FileHandler.getGamesFromFile();

        //then
        assertEquals(expectedAttributesList, actualAttributesList);
        assertNotNull(actualAttributesList);
        assertFalse(actualAttributesList.isEmpty());
    }

    @Test
    void saveGenresToFile() {

    }

    @Test
    void saveSimulationGamesToFile() {
    }

    @Test
    void savePublishersToFile() {
    }
}