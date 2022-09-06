package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.dto.Data;
import com.ciandt.summit.bootcamp2022.dto.MusicDto;
import com.ciandt.summit.bootcamp2022.dto.UserDTO;
import com.ciandt.summit.bootcamp2022.dto.UsernameDto;
import com.ciandt.summit.bootcamp2022.services.PlaylistService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PathParam;

@RestController
@RequestMapping(value = "/playlist")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;
    @ApiOperation(value = "This endpoint add musica in a playlist.")
    @PutMapping("/{playlistId}/musicas")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addMusicToPlaylist(@PathVariable String playlistId
            , @RequestBody MusicDto musicDto
            , @RequestHeader(value = "name") String nome
            , @RequestHeader(value = "token") String token) {
        UsernameDto usernameDto = new UsernameDto(new Data(nome, token));
        playlistService.saveMusicToPlaylist(playlistId, musicDto, usernameDto);
    }

    @ApiOperation(value = "this endpoint exclude a music by Id.")
    @DeleteMapping("/{playlistId}/musicas/{musicaId}")
    public ResponseEntity<String> deleteMusicFromPlaylist(@PathVariable String playlistId
            , @PathVariable String musicaId
            , @RequestHeader(value = "name") String nome
            , @RequestHeader(value = "token") String token) {
        UsernameDto usernameDto = new UsernameDto(new Data(nome, token));
        playlistService.deleteMusicFromPlaylist(playlistId, musicaId, usernameDto);
        return ResponseEntity.ok().body("Music was deleted!");
    }

    @ApiOperation(value = "This endpoint add musics in a playlist after verificate the type of the user.")
    @PutMapping("/{playlistId}/{userId}/music")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<String> addMusicWithUserVerification(
            @PathVariable String playlistId,
            @PathVariable String userId,
            @RequestBody MusicDto musicDto,
            @RequestHeader(value = "name") String nome,
            @RequestHeader(value = "token") String token
    ){
        UsernameDto usernameDto = new UsernameDto(new Data(nome, token));
        playlistService.saveMusicToPlaylistCheckingUserTpe(playlistId, userId, musicDto, usernameDto);
        return ResponseEntity.ok().body("Music added!");
    }
}
