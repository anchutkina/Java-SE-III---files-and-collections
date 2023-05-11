package org.example.games;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameMapperTest {

    GameMapper gameMapper;

    @BeforeEach
    void setUp() {
        gameMapper = new GameMapper();
    }

    @Test
    void testShouldMapGameToObject() {
        List<Game> expectedGame = new ArrayList<>();
        expectedGame.add(new Game("Game 1", "2002", List.of("CD Projekt RED"), List.of("Disney Interactive"), List.of("Puzzle", "Strategy", "Adventure")));
        expectedGame.add(new Game("Game 2", "1997", List.of("EA Canada"), List.of("EA Sports"), List.of("Sport")));

        List<Game> actualGame = gameMapper.mapAttributesToGameObject(Path.of("src", "test", "resources", "test_game_mapper.csv"));

        assertEquals(expectedGame.size(), actualGame.size());
        assertEquals(expectedGame.get(0).getName(), actualGame.get(0).getName());
        assertEquals(expectedGame.get(0).getReleaseDate(), actualGame.get(0).getReleaseDate());
        assertEquals(expectedGame.get(0).getDevelopers(), actualGame.get(0).getDevelopers());
        assertEquals(expectedGame.get(0).getPublishers(), actualGame.get(0).getPublishers());
        assertEquals(expectedGame.get(0).getGenres(), actualGame.get(0).getGenres());
        assertEquals(expectedGame.get(1).getName(), actualGame.get(1).getName());
        assertEquals(expectedGame.get(1).getReleaseDate(), actualGame.get(1).getReleaseDate());
        assertEquals(expectedGame.get(1).getDevelopers(), actualGame.get(1).getDevelopers());
        assertEquals(expectedGame.get(1).getPublishers(), actualGame.get(1).getPublishers());
        assertEquals(expectedGame.get(1).getGenres(), actualGame.get(1).getGenres());

        assertNotNull(actualGame);
        assertFalse(actualGame.isEmpty());
    }

}