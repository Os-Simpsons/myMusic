package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.entity.Artist;
import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
import java.util.List;

@RestController
@RequestMapping("/api/v1/music")
public class MusicController {
        public ResponseEntity<String> get() {
            return ResponseEntity.ok("67f5976c-eb1e-404e-8220-2c2a8a23be47");
        }

        /*
        @Autowired
        ArtistService artistService;


         @GetMapping
         public ResponseEntity<List<Artist>> getAllArtists(@QueryParam("music") Music music, @QueryParam("artist") Artist artist){
             List<Artist> artistList = ar
             return new ResponseEntity<List<Artist>>(artistList , HttpStatus.OK);
         }*/
    }