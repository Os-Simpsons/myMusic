package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.dto.Data;
import com.ciandt.summit.bootcamp2022.dto.MusicDto;
import com.ciandt.summit.bootcamp2022.dto.UsernameDto;
import com.ciandt.summit.bootcamp2022.services.PlaylistService;
import com.ciandt.summit.bootcamp2022.utils.exceptions.InvalidLogDataException;
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
    public void addMusicToPlaylist(@PathVariable String playlistId
            , @RequestBody MusicDto musicDto
            ,@RequestHeader(value = "name") String nome
            ,@RequestHeader(value = "token") String token) throws InvalidLogDataException {
        UsernameDto usernameDto = new UsernameDto(new Data(nome,token));
        playlistService.saveMusicToPlaylist(playlistId, musicDto, usernameDto);
    }

}
