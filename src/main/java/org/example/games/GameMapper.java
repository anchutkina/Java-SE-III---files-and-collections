package org.example.games;

import org.example.GlobalConstants;

import java.util.ArrayList;
import java.util.List;

public class GameMapper {


    public List<Game> mapGameToObject() {
        List<Game> gameStorage = new ArrayList<>();
        try {
            List<List<String>> attributesList = FileHandler.getGamesFromFile();
            for(List<String> attributes : attributesList) {

                String name = attributes.get(0);
                String date = attributes.get(1);

                List<String> developers = new ArrayList<>();
                String[] developerArray = attributes.get(2).split(GlobalConstants.COMMA_SPACE);
                for (String developer : developerArray) {
                    developers.add(developer.replace("\"", ""));
                }

                List<String> publishers = new ArrayList<>();
                String[] publisherArray = attributes.get(3).split(GlobalConstants.COMMA_SPACE);
                for (String publisher : publisherArray) {
                    publishers.add(publisher.replace("\"", ""));
                }

                List<String> genres = new ArrayList<>();
                String[] genreArray = attributes.get(4).split(GlobalConstants.COMMA_SPACE);
                for (String genre : genreArray) {
                    genres.add(genre.replace("\"", ""));
                }
                Game game = new Game(name, date, developers, publishers, genres);
                gameStorage.add(game);
            }
            return gameStorage;
        } catch (RuntimeException r) {
            GlobalConstants.LOGGER.warning(GlobalConstants.ERROR);
            return null;
        }
    }



}

