package com.ciandt.summit.bootcamp2022.repositories;

import com.ciandt.summit.bootcamp2022.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
    public interface ArtistRepository extends JpaRepository<Artist, String> {
        @Query(value = "SELECT DISTINCT A FROM Artistas A INNER JOIN Musicas M ON A.Id =  M.ArtistasId")
        public List<Artist> getAllArtist();
    }



