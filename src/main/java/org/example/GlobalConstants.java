package org.example;

import java.nio.file.Path;
import java.util.logging.Logger;

public class GlobalConstants {

    public static final String ERROR = "Error with input data";
    public static final Logger LOGGER = Logger.getLogger(GlobalConstants.class.getName());
    public static final String SIMULATOR = "Simulator";
    public static final String COMMA = ",";
    public static final String COMMA_SPACE = ", ";
    public static final Path PATH_TO_READING_FILE = Path.of("src", "main", "resources", "games.csv");
    public static final Path PATH_TO_GAME_GENRES_FILE = Path.of("src", "main", "resources", "game_genres.txt");
    public static final Path PATH_TO_SIMULATORS_GAME_FILE = Path.of("src", "main", "resources", "simulator_games.csv");
    public static final Path PATH_TO_GAME_PUBLISHERS_FILE = Path.of("src", "main", "resources", "game_publishers.csv");
}
