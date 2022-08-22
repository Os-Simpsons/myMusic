package com.ciandt.summit.bootcamp2022.services;

import com.ciandt.summit.bootcamp2022.entity.Artist;
import com.ciandt.summit.bootcamp2022.repositories.ArtistRepository;
import com.ciandt.summit.bootcamp2022.services.exceptions.ResourceNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {

    @Autowired
    private ArtistRepository artistRepository;
    private static Logger logger = LogManager.getLogger(ArtistServiceImpl.class);

    //TODO - Implementar metodo getArtistByName

    @Override
    public Artist getArtistById(String id) {
        Artist artists = new Artist();
        try {
            artists = artistRepository.getById(id);
        } catch (ResourceNotFoundException e) {
            if (artists.getId().isEmpty()) {
                logger.error("this Id not exist in database");
                throw new ResourceNotFoundException("this Id not exist in database");
            }
        }
        return artists;
    }

    //TODO - Ajustar padrão do getAllArtist
    @Override
    public List<Artist> getAllArtist() {
        List<Artist> artists = artistRepository.findAll();
        if (artists.isEmpty()) {
            logger.error("this Id not exist in database");
            throw new ResourceNotFoundException("this Id not exist in database");
        }
        return artists;
    }
}
