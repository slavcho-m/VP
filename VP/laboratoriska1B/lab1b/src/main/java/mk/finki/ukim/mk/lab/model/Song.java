package mk.finki.ukim.mk.lab.model;

import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Data
public class Song {
    private String trackId;
    private String title;
    private String genre;
    private int releaseYear;
    List<Artist> performers;

    public Song() {}

    public Song(String trackId, String title, String genre, int releaseYear) {
        this.trackId = trackId;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;

        performers = new ArrayList<>();
    }

    public void addPerformer(Artist performer) {
        performers.add(performer);
    }
}
