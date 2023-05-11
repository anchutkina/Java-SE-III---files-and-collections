package org.example.games;


import java.util.List;

public class Game {
    private String name;
    private String releaseDate;
    private List<String> developers;
    private List<String> publishers;
    private List<String> genres;

    public Game(String name, String releaseDate, List<String> developers, List<String> publishers, List<String> genres) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.developers = developers;
        this.publishers = publishers;
        this.genres = genres;
    }

    public Game() {
    }

    public String getName() {
        return name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public List<String> getDevelopers() {
        return developers;
    }

    public List<String> getPublishers() {
        return publishers;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setDevelopers(List<String> developers) {
        this.developers = developers;
    }

    public void setPublishers(List<String> publishers) {
        this.publishers = publishers;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }




    @Override
    public String toString() {
        return "Game{" +
                "name='" + name + '\'' +
                ", releaseDate=" + releaseDate +
                ", developer='" + developers.toString() + '\'' +
                ", publisher='" + publishers.toString() + '\'' +
                ", genres=" + genres.toString() +
                '}';
    }

}
