package com.ciandt.summit.bootcamp2022.services;

import com.ciandt.summit.bootcamp2022.entities.Artist;
import com.ciandt.summit.bootcamp2022.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ArtistServiceImpl implements ArtistService{

    @Autowired
    private ArtistRepository artistRepository;

    @Override
    public Artist getArtistById(Artist artist) {
        return null;
    }
}
