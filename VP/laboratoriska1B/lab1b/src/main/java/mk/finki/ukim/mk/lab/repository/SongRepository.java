package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SongRepository {

    public List<Song> findAll() {
        return DataHolder.songList;
    }

    public Song findByTrackId(String trackId) {
        return DataHolder.songList.stream().filter(s -> s.getTrackId().equals(trackId)).findFirst().orElse(null);
    }

    public Artist addArtistToSong(Song song, Artist artist) {
        for (Song s : DataHolder.songList) {
            if (s.getTrackId().equals(song.getTrackId())) {
                s.addPerformer(artist);
                return artist;
            }
        }
        return null;
    }

}
