package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.dto.MusicDto;
import com.ciandt.summit.bootcamp2022.dto.UsernameDto;
import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.entity.Playlist;
import com.ciandt.summit.bootcamp2022.repositories.MusicRepository;
import com.ciandt.summit.bootcamp2022.repositories.PlaylistRepository;
import com.ciandt.summit.bootcamp2022.service.exceptions.MusicAlreadyExistException;
import com.ciandt.summit.bootcamp2022.service.exceptions.ResourceNotFoundException;
import com.ciandt.summit.bootcamp2022.utils.TokenService;
import com.ciandt.summit.bootcamp2022.utils.exceptions.InvalidLogDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class PlaylistServiceImpl implements PlaylistService{

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private MusicRepository musicRepository;

    @Autowired
    private TokenService tokenService;


    @Transactional
    @Override
    public void saveMusicToPlaylist(String id, MusicDto musicDto, UsernameDto usernameDto) {
        try {
            tokenService.createToken(usernameDto);
            //tokenService.validateToken(usernameDto);
            System.out.println(usernameDto.getName());
            //System.out.println(usernameDto.getToken());
            Playlist playlist = playlistRepository.getById(id);
            Music music = musicRepository.getById(musicDto.getId());
            for (Music musicFind : playlist.getMusicList()) {
                if (musicFind.getId() == music.getId()){
                    throw new MusicAlreadyExistException("Music Already exist in this playlist.");
                }
            }
            playlist.getMusicList().add(music);
            music.getPlaylist().add(playlist);
            playlistRepository.save(playlist);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("this Id not exist in database");
        } catch (InvalidLogDataException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }
}
