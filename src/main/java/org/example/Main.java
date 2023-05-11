package org.example;

import org.example.games.FileHandler;


public class Main {

    public static void main(String[] args) {
        FileHandler fileHandler = new FileHandler();

        fileHandler.saveGenresToFile(GlobalConstants.PATH_TO_READING_FILE, GlobalConstants.PATH_TO_GAME_GENRES_FILE);

        fileHandler.saveSimulatorGamesToFile(GlobalConstants.PATH_TO_READING_FILE, GlobalConstants.PATH_TO_SIMULATORS_GAME_FILE);

        fileHandler.savePublishersToFile(GlobalConstants.PATH_TO_READING_FILE, GlobalConstants.PATH_TO_GAME_PUBLISHERS_FILE);
    }

}
