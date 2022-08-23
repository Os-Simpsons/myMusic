package com.ciandt.summit.bootcamp2022.repositories;

import com.ciandt.summit.bootcamp2022.entity.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MusicRepository extends JpaRepository<Music, String> {
    @Query(value = "SELECT DISTINCT M FROM Musicas M INNER JOIN Artistas A ON M.ArtistaId =  A.Id")
    public List<Music> getAllMusics();

}
