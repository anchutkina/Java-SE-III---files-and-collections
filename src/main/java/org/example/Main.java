package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Game> gameStorage = getGameList();
        for(Game game : gameStorage) {
            System.out.println(game);
        }
    }

    private static List<Game> getGameList() {
        List<Game> gameStorage = new ArrayList<>();
        Path pathToFile = Path.of("src", "main", "resources", "games.csv");
        try {

            BufferedReader reader = Files.newBufferedReader(pathToFile);
            String line = reader.readLine();
            while (line != null) {
                List<String> attributes = Arrays.asList(line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)"));
                Game game = getOneGame(attributes);
                gameStorage.add(game);
                line = reader.readLine();
            }
        } catch (IOException | RuntimeException f) {
            f.printStackTrace();
        }

        return gameStorage;
    }


    private static Game getOneGame(List<String> attributes) {
            try {
                String name = attributes.get(0);
                String date = attributes.get(1);

                List<String> developers = new ArrayList<>();
                String[] developerArray = attributes.get(2).split(", ");
                for(String developer : developerArray) {
                    developers.add(developer.replace("\"", ""));
                }

                List<String> publishers = new ArrayList<>();
                String[] publisherArray = attributes.get(3).split(", ");
                for(String publisher: publisherArray){
                    publishers.add(publisher.replace("\"", ""));
                }

                List<String> genres = new ArrayList<>();
                String[] genreArray = attributes.get(4).split(", ");
                for(String genre: genreArray) {
                    genres.add(genre.replace("\"", ""));
                }
                Game game = new Game(name, date, developers, publishers, genres);
                return game;
            } catch (RuntimeException r) {
                r.printStackTrace();
                return null;
            }
        }


    }
