package com.ciandt.summit.bootcamp2022.services;

import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.repositories.MusicRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicServiceImpl implements MusicService {

    @Autowired
    private MusicRepository musicRepository;
    private static Logger logger = LogManager.getLogger(MusicServiceImpl.class);


    @Override
    public List<Music> buscarPorArtist(String name) {
        return musicRepository.getAllMusicArtist(name);
    }
}