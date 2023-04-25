package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {
    private static List<Game> gameStorage = new ArrayList<>();
    public static void main(String[] args) {
        gameStorage = getGameList();
        saveGenresToFile();
    }

    private static List<Game> getGameList() {
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

        private static void saveGenresToFile() {
            Path pathToFile = Path.of("src", "main", "resources", "game_genres.txt");
            try {
                FileWriter genresFile = new FileWriter(pathToFile.toFile());
                BufferedWriter genresWriter = new BufferedWriter(genresFile);
                for(String genres : getGenresList()) {
                    genresWriter.write(genres + ", ");
                }
                genresWriter.close();
                System.out.println("File game_genres.txt is successfully written.");

            } catch(IOException |RuntimeException f) {
                f.printStackTrace();
            }
        }

        private static Set<String> getGenresList(){
            //list of all game genres from the input file, sorted alphabetically and containing only unique values.
            Set<String> individualGenre = new TreeSet<>();
            for(Game game : gameStorage) {
                List<String> genres = game.getGenres();
                if(!genres.isEmpty()) {
                    genres.remove(0);
                } else {
                    System.out.println("There are not any genres to work with.");
                }

                individualGenre.addAll(genres);
            }
            return individualGenre;
        }



    }
