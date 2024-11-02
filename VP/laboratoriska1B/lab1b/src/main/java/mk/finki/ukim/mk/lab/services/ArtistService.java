package mk.finki.ukim.mk.lab.services;

import mk.finki.ukim.mk.lab.model.Artist;

import java.util.List;

public interface ArtistService {
    List<Artist> listArtists();
    Artist findById(Long id);
}
