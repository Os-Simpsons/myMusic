package com.ciandt.summit.bootcamp2022.services;

import com.ciandt.summit.bootcamp2022.dto.UsernameDto;
import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.repositories.MusicRepository;
import com.ciandt.summit.bootcamp2022.utils.TokenService;
import com.ciandt.summit.bootcamp2022.utils.exceptions.InvalidLogDataException;
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

    @Autowired
    private TokenService tokenService;

    @Override
    public List<Music> getMusics(String name, UsernameDto usernameDto) {
        try {
            tokenService.validateToken(usernameDto);
            List<Music> music = musicRepository.getAllMusicArtist(name);
            logger.info("Music/Artist found");
            return music;

        } catch (
                InvalidLogDataException e) {
            logger.error("Invalid Token name");
            throw new InvalidLogDataException(e.getMessage());
        }
    }}