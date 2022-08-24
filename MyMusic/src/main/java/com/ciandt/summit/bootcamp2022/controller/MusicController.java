package com.ciandt.summit.bootcamp2022.controller;


import com.ciandt.summit.bootcamp2022.dto.Data;
import com.ciandt.summit.bootcamp2022.dto.UsernameDto;
import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.services.MusicService;
import com.ciandt.summit.bootcamp2022.utils.exceptions.InvalidLogDataException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;



@RestController
@RequestMapping("/v1/music")
public class MusicController {
    @Autowired
    MusicService musicService;

    @GetMapping
    public ResponseEntity<List<Music>> getAllForms
            (@RequestParam(value = "name", required = false) String name
            ,@RequestHeader(value = "name") String nome
            ,@RequestHeader(value = "token") String token) {
        UsernameDto usernameDto = new UsernameDto(new Data(nome,token));
        List<Music> music = musicService.getMusics(name, usernameDto);

        return ResponseEntity.ok(music);
    }


}