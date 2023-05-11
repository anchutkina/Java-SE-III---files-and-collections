package org.example.games;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private static final String NAME = "Sims";
    private static final String DATE = "2001";
    private static final List<String> DEVELOPER = List.of("Dynamix", "Visual Arts");
    private static final List<String> PUBLISHER = List.of("KID", "Cyberfront", "SDR Project");
    private static final List<String> GENRE = List.of("Puzzle", "Strategy", "Adventure");

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game(NAME, DATE, DEVELOPER,PUBLISHER,GENRE);
    }

    @Test
    void testShouldReturnGameName() {
        assertEquals(NAME, game.getName());
    }

    @Test
    void testShouldReturnGameReleaseDate() {
        assertEquals(DATE, game.getReleaseDate());
    }

    @Test
    void testShouldReturnGameDeveloper() {
        assertEquals(DEVELOPER, game.getDevelopers());
    }

    @Test
    void testShouldReturnGamePublisher() {
        assertEquals(PUBLISHER, game.getPublishers());
    }

    @Test
    void testShouldReturnGameGenre() {
        assertEquals(GENRE, game.getGenres());
    }
}