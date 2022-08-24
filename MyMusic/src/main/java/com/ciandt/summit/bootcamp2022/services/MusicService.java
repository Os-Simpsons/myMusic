package com.ciandt.summit.bootcamp2022.services;

import com.ciandt.summit.bootcamp2022.dto.UsernameDto;
import com.ciandt.summit.bootcamp2022.entity.Artist;
import com.ciandt.summit.bootcamp2022.entity.Music;

import java.util.List;

public interface MusicService {

        List<Music> buscarPorArtist(String name, UsernameDto usernameDto);


}



