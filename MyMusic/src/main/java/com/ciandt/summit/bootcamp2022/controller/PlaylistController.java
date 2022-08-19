package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.dto.MusicDto;
import com.ciandt.summit.bootcamp2022.service.PlaylistService;
import com.ciandt.summit.bootcamp2022.service.PlaylistServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/playlist")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @PutMapping("/{playlistId}/musicas")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addMusicToPlaylist(@PathVariable String playlistId, @RequestBody MusicDto musicDto) {
        playlistService.saveMusicToPlaylist(playlistId, musicDto);
    }

}
