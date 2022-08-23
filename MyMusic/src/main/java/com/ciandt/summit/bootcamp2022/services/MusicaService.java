package com.ciandt.summit.bootcamp2022.services;

import com.ciandt.summit.bootcamp2022.entity.Music;

import java.util.List;

public interface MusicaService {

    Music getArtistById(String id);

    List<Music> getAllMusics();
}
