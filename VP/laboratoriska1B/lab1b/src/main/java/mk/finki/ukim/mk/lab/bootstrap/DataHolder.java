package mk.finki.ukim.mk.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Artist> artistList = new ArrayList<>();
    public static List<Song> songList = new ArrayList<>();

    @PostConstruct
    public void init() {
        artistList.add(new Artist(11L, "Bobi", "Majstorot", "Mnogu spremni majstorski pesni"));
        artistList.add(new Artist(22L, "Mekica", "Mekisonski", "Mekicite od strezhevo"));
        artistList.add(new Artist(44L, "Dedoto", "Dedovski", "Star covek dobri pesni"));
        artistList.add(new Artist(612L, "Macka", "Pejakovski", "Brz i besen"));
        artistList.add(new Artist(327L, "Pete", "Petkinson", "Ednostavno pete"));

        songList.add(new Song("PacX", "Pacman", "Death Metal", 1998));
        songList.add(new Song("ZZX", "What is this", "Turbofolk", 1964));
        songList.add(new Song("TestPesna", "TestTitle", "Metal", 2005));
        songList.add(new Song("EDM", "Electro House", "Techno", 2021));
        songList.add(new Song("Test2Pesna", "Test2Title", "Neshto Neshto", 2015));
    }
}
