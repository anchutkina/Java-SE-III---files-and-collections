package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {
    private static List<Game> gameStorage = new ArrayList<>();
    private static final String COMMA = ",";
    private static final String COMMA_SPACE = ", ";

    public static void main(String[] args) {
        gameStorage = getGameList();
        saveGenresToFile();
        saveSimulatorGameToFile();
    }

    private static List<Game> getGameList() {
        try {
            Path pathToFile = Path.of("src", "main", "resources", "games.csv");
            BufferedReader reader = Files.newBufferedReader(pathToFile);
            String line = reader.readLine();
            while (line != null) {
                List<String> attributes = Arrays.asList(line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)"));
                Game game = getOneGame(attributes);
                gameStorage.add(game);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return gameStorage;
    }


    private static Game getOneGame(List<String> attributes) {
            try {
                String name = attributes.get(0);
                String date = attributes.get(1);

                List<String> developers = new ArrayList<>();
                String[] developerArray = attributes.get(2).split(COMMA_SPACE);
                for(String developer : developerArray) {
                    developers.add(developer.replace("\"", ""));
                }

                List<String> publishers = new ArrayList<>();
                String[] publisherArray = attributes.get(3).split(COMMA_SPACE);
                for(String publisher: publisherArray){
                    publishers.add(publisher.replace("\"", ""));
                }

                List<String> genres = new ArrayList<>();
                String[] genreArray = attributes.get(4).split(COMMA_SPACE);
                for(String genre: genreArray) {
                    genres.add(genre.replace("\"", ""));
                }
                return new Game(name, date, developers, publishers, genres);
            } catch (RuntimeException r) {
                r.printStackTrace();
                return null;
            }
        }

        private static void saveGenresToFile() {
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

            try {
                Path pathToFile = Path.of("src", "main", "resources", "game_genres.txt");
                FileWriter genresWriter = new FileWriter(pathToFile.toFile());
                for(String genres : individualGenre) {
                    genresWriter.write(genres + COMMA);
                }
                genresWriter.close();
                System.out.println("File game_genres.txt was written successfully.");

            } catch(IOException e) {
                e.printStackTrace();
            }
        }


        private static void saveSimulatorGameToFile() {

                List<Game> simulatorGame = new ArrayList<>();
                for(Game game : gameStorage) {
                    if(game.getGenres().contains("Simulator")) {
                        simulatorGame.add(game);
                    }
                }
                simulatorGame.sort(new Comparator<Game>() {
                    @Override
                    public int compare(Game game1, Game game2) {
                        return game1.getReleaseDate().compareTo(game2.getReleaseDate());
                    }
                });

            try {
                Path pathToFile = Path.of("src", "main", "resources", "simulator_games.csv");
                FileWriter writer = new FileWriter(pathToFile.toFile());
                for(Game game: simulatorGame) {
                    writer.write(game.getName() + COMMA + game.getReleaseDate() + "\n");
                }

                writer.close();
                System.out.println("File simulator_games.csv was written successfully.");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }





    }
