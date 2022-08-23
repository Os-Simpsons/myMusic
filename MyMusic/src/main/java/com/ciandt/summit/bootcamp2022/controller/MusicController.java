package com.ciandt.summit.bootcamp2022.controller;


import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.services.MusicService;
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
    public ResponseEntity<List<Music>> getAllForms(@RequestParam(value = "name", required = false) String name) {
        List<Music> music = musicService.buscarPorArtist(name);

        return ResponseEntity.ok(music);
    }


}