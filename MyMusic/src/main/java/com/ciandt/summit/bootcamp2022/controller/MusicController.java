package com.ciandt.summit.bootcamp2022.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/music")
public class MusicController {
        public ResponseEntity<String> get() {
            return ResponseEntity.ok("67f5976c-eb1e-404e-8220-2c2a8a23be47");
        }

        //TODO - Implementar
        // @GetMapping
        // public ResponseEntity<List<Artist>> getAllArtists(@QueryParam("Musicas")){    }
    }