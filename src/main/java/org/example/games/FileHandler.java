package org.example.games;

import org.example.GlobalConstants;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class FileHandler {
    private static final String SIMULATOR = "Simulator";
    GameMapper gameMapper = new GameMapper();

    public static List<List<String>> getGamesFromFile() {
        Path pathToFile = Path.of("src", "main", "resources", "games.csv");
        List<List<String>> attributesList = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(pathToFile)) {
            String line = reader.readLine();
            while (line != null) {
                List<String> attributes = Arrays.asList(line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)"));
                attributesList.add(attributes);
                line = reader.readLine();
            }
        } catch (IOException e) {
            GlobalConstants.LOGGER.warning(GlobalConstants.ERROR);
        }
        return attributesList;

    }

    public void saveGenresToFile() {
        Path pathToFile = Path.of("src", "main", "resources", "game_genres.txt");
        try (FileWriter genresWriter = new FileWriter(pathToFile.toFile())){
            for(String genre : getUniqueGame()) {
                if(!genre.equals(getUniqueGame().last())) {
                    genresWriter.write(genre + GlobalConstants.COMMA);
                } else {
                    genresWriter.write(genre);
                }
            }
            GlobalConstants.LOGGER.info("File game_genres.txt was written successfully.");

        } catch(IOException e) {
            GlobalConstants.LOGGER.warning(GlobalConstants.ERROR);
        }
    }

    private TreeSet<String> getUniqueGame(){
        TreeSet<String> uniqueGenre = new TreeSet<>();
        for(Game game : gameMapper.mapGameToObject()) {
            List<String> genres = game.getGenre();
            if(!genres.isEmpty()) {
                genres.remove(0);
            } else {
                GlobalConstants.LOGGER.warning("There are not any genres to work with.");
            }

            uniqueGenre.addAll(genres);
        }
        return uniqueGenre;
    }

    public void saveSimulationGamesToFile() {
        Path pathToFile = Path.of("src", "main", "resources", "simulator_games.csv");
        try (FileWriter writer = new FileWriter(pathToFile.toFile())){

            writer.write("Name,Released Date\n");
            for(Game game: getSimulatorGames()) {
                writer.write(game.getName() + GlobalConstants.COMMA + game.getReleaseDate() + "\n");
            }

            GlobalConstants.LOGGER.info("File simulator_games.csv was written successfully.");

        } catch (IOException e) {
            GlobalConstants.LOGGER.warning(GlobalConstants.ERROR);
        }
    }

    private List<Game> getSimulatorGames() {
        List<Game> simulatorGames = new ArrayList<>();
        for(Game game : gameMapper.mapGameToObject()) {
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
        return simulatorGames;
    }

    public void savePublishersToFile() {
        Path pathToFile = Path.of("src", "main", "resources", "game_publishers.csv");
        try (BufferedWriter writer = Files.newBufferedWriter(pathToFile)){
            writer.write("Publisher,Count_of_games\n");
            for(Map.Entry<String,Integer> publisher : getUniquePublishers()) {
                String publisherName = publisher.getKey();
                if(publisherName.isEmpty()) {
                    publisherName = "Publishers without a name";
                }
                writer.write(publisherName + GlobalConstants.COMMA + publisher.getValue() + "\n");
            }

            GlobalConstants.LOGGER.info("File game_publishers.csv was written successfully.");

        } catch (IOException e) {
            GlobalConstants.LOGGER.warning(GlobalConstants.ERROR);
        }
    }

    private List<Map.Entry<String,Integer>> getUniquePublishers() {
        Map<String, Integer> publisherToGameNumber = new HashMap<>();
        for(Game game: gameMapper.mapGameToObject()) {
            for(String publisher : game.getPublisher()) {
                publisherToGameNumber.put(publisher, publisherToGameNumber.getOrDefault(publisher, 0) + 1);
            }
        }

        List<Map.Entry<String,Integer>> publishers = new ArrayList<>(publisherToGameNumber.entrySet());
        publishers.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        return publishers;
    }

}
