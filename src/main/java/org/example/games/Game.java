package org.example.games;


import java.util.List;

public class Game {
    private String name;
    private String releaseDate;
    private List<String> developer;
    private List<String> publisher;
    private List<String> genre;

    public Game(String name, String releaseDate, List<String> developer, List<String> publisher, List<String> genre) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.developer = developer;
        this.publisher = publisher;
        this.genre = genre;
    }

    public Game() {
    }

    public String getName() {
        return name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public List<String> getDeveloper() {
        return developer;
    }

    public List<String> getPublisher() {
        return publisher;
    }

    public List<String> getGenre() {
        return genre;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setDeveloper(List<String> developer) {
        this.developer = developer;
    }

    public void setPublisher(List<String> publisher) {
        this.publisher = publisher;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }




    @Override
    public String toString() {
        return "Game{" +
                "name='" + name + '\'' +
                ", releaseDate=" + releaseDate +
                ", developer='" + developer.toString() + '\'' +
                ", publisher='" + publisher.toString() + '\'' +
                ", genres=" + genre.toString() +
                '}';
    }

}
