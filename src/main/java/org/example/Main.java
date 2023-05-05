package org.example;

import org.example.games.Game;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {
    private static List<Game> gameStorage = new ArrayList<>();
    private static final String COMMA = ",";
    private static final String COMMA_SPACE = ", ";
    private static final String SIMULATOR = "Simulator";

    public static void main(String[] args) {
        gameStorage = getGameList();
        saveGenresToFile();
        saveSimulatorGameToFile();
        savePublishersToFile();
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
            TreeSet<String> uniqueGenre = new TreeSet<>();
            for(Game game : gameStorage) {
                List<String> genres = game.getGenre();
                if(!genres.isEmpty()) {
                    genres.remove(0);
                } else {
                    System.out.println("There are not any genres to work with.");
                }

                uniqueGenre.addAll(genres);
            }


            try {
                Path pathToFile = Path.of("src", "main", "resources", "game_genres.txt");
                FileWriter genresWriter = new FileWriter(pathToFile.toFile());
                for(String genre : uniqueGenre) {
                    if(!genre.equals(uniqueGenre.last())) {
                        genresWriter.write(genre + COMMA);
                    } else {
                        genresWriter.write(genre);
                    }
                }
                genresWriter.close();
                System.out.println("File game_genres.txt was written successfully.");

            } catch(IOException e) {
                e.printStackTrace();
            }
        }


        private static void saveSimulatorGameToFile() {

                List<Game> simulatorGames = new ArrayList<>();
                for(Game game : gameStorage) {
                    if(game.getGenre().contains(SIMULATOR)) {
                        simulatorGames.add(game);
                    }
                }
                simulatorGames.sort(new Comparator<Game>() {
                    @Override
                    public int compare(Game game1, Game game2) {
                        return game1.getReleaseDate().compareTo(game2.getReleaseDate());
                    }
                });

            try {
                Path pathToFile = Path.of("src", "main", "resources", "simulator_games.csv");
                FileWriter writer = new FileWriter(pathToFile.toFile());
                writer.write("Name,Released Date\n");
                for(Game game: simulatorGames) {
                    writer.write(game.getName() + COMMA + game.getReleaseDate() + "\n");
                }

                writer.close();
                System.out.println("File simulator_games.csv was written successfully.");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private static void savePublishersToFile() {
            Map<String, Integer> publisherToGameNumber = new HashMap<>();
            for(Game game: gameStorage) {
                for(String publisher : game.getPublisher()) {
                    publisherToGameNumber.put(publisher, publisherToGameNumber.getOrDefault(publisher, 0) + 1);
                }
            }

            List<Map.Entry<String,Integer>> publishers = new ArrayList<>(publisherToGameNumber.entrySet());
            publishers.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

            try {
                Path pathToFile = Path.of("src", "main", "resources", "game_publishers.csv");
                BufferedWriter writer = Files.newBufferedWriter(pathToFile);
                writer.write("Publisher,Count_of_games\n");
                for(Map.Entry<String,Integer> publisher : publishers) {
                    String publisherName = publisher.getKey();
                    if(publisherName.isEmpty()) {
                        publisherName = "Publishers without a name";
                    }
                    writer.write(publisherName + COMMA + publisher.getValue() + "\n");
                }

                writer.close();
                System.out.println("File game_publishers.csv was written successfully.");

            } catch (IOException e) {
                e.printStackTrace();
            }



        }



    }
