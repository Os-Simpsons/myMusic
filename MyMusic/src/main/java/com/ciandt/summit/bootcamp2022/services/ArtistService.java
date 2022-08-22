package com.ciandt.summit.bootcamp2022.services;

import com.ciandt.summit.bootcamp2022.entity.Artist;
import java.util.List;

public interface ArtistService {
    Artist getArtistById(String id);

    List<Artist> getAllArtist();
}
