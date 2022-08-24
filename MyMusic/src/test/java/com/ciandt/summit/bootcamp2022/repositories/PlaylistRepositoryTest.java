package com.ciandt.summit.bootcamp2022.repositories;

import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.entity.Playlist;
import com.ciandt.summit.bootcamp2022.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.List;


@DataJpaTest
@AutoConfigureTestDatabase
public class PlaylistRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    PlaylistRepository playlistRepository;

    @Test
    public void shouldStorePlayslist() {
        List<Music> musicList = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        Playlist playlist = playlistRepository.save(new Playlist(musicList,userList));
        Assertions.assertEquals(playlist,playlistRepository.getById(playlist.getId()));
    }



}
