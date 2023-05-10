package org.example;

import org.example.games.FileHandler;

public class Main {

    public static void main(String[] args) {
        FileHandler fileHandler = new FileHandler();
        fileHandler.saveGenresToFile();
        fileHandler.saveSimulationGamesToFile();
        fileHandler.savePublishersToFile();
    }

}
